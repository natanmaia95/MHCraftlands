package com.nateplays.mhcraftlands.common.event;

import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.slf4j.Logger;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class DamageFormulaHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void livingDamageEvent(LivingIncomingDamageEvent event) {

        DamageSource damageSource = event.getSource();
        damageFormulaRawStep(event, damageSource);
//        damageFormulaMotionValueStep(event);
        damageFormulaElementalStep(event);


        damageFormulaDefenseStep(event);
    }

    public static boolean damageFormulaRawStep(LivingIncomingDamageEvent event, DamageSource damageSource) {
        ItemStack weaponStack = damageSource.getWeaponItem();
        if (weaponStack != null && weaponStack.getItem() instanceof HuntingWeaponItem) {
            HuntingWeaponItem weaponItem = (HuntingWeaponItem) weaponStack.getItem();




            //finish by applying the "motion value", the actual strength of the swing
            float mvMultiplier = weaponItem.getAttackDamageMultiplier();
            event.setAmount(event.getAmount() * mvMultiplier);
//            LogUtils.getLogger().debug("multiplier:" + String.valueOf(mvMultiplier));
        }

        return true;
    }

    public static boolean damageFormulaElementalStep(LivingIncomingDamageEvent event) {
        DamageSource damageSource = event.getSource();
        ItemStack weaponStack = damageSource.getWeaponItem();
        Entity entity = damageSource.getEntity();
        LivingEntity targetEntity = event.getEntity();
        if (weaponStack != null && weaponStack.getItem() instanceof HuntingWeaponItem weaponItem) {
            double finalElementalDamage = 0.0;

            if (entity instanceof LivingEntity livingEntity) {
                double elementalDamage = 0.0;

                for (DeferredHolder<Attribute, Attribute> elemDamageAttrHolder : ModAttributes.allElemDamages()) {
                    LOGGER.debug(elemDamageAttrHolder.toString());
                    double tempDamage = 0.0;
                    //attack
                    AttributeInstance elemDamageAttrInstance = livingEntity.getAttribute(elemDamageAttrHolder);
                    if (elemDamageAttrInstance != null) tempDamage = elemDamageAttrInstance.getValue();
                    if (tempDamage == 0) continue;
//                    LOGGER.debug("element base damage: %f".formatted(tempDamage));
                    //defense
                    Holder<Attribute> resistanceHolder = ModAttributes.ELEMENT_DAMAGE_TO_RESISTANCE.get(elemDamageAttrHolder);
                    AttributeInstance weaknessAttrInstance = targetEntity.getAttribute(resistanceHolder);
                    double damageRate = 100.0;
                    if (weaknessAttrInstance != null) {
                        damageRate = Math.max(0, 100.0 - weaknessAttrInstance.getValue());
//                        LOGGER.debug("element weakness: %f".formatted(weaknessAttrInstance.getValue()));
                    }
                    tempDamage *= damageRate/100.0;
                    //apply
                    elementalDamage += tempDamage;
                }

                if (livingEntity instanceof Player player) { //half elem damage if spamming attack
                    if (player.getAttackStrengthScale(0) <= 0.8f) elementalDamage *= 0.5;
                }
                //TODO: check element crits, etc
                finalElementalDamage += elementalDamage;
            }

            event.setAmount(event.getAmount() + (float) finalElementalDamage);
//            LOGGER.debug("element damage: %f".formatted(finalElementalDamage));
        }

        return true;
    }

    public static boolean damageFormulaDefenseStep(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();
        float damageAmount = event.getAmount();
        if (!livingEntity.getAttributes().hasAttribute(ModAttributes.DEFENSE)) return true;
        double defense = livingEntity.getAttributeValue(ModAttributes.DEFENSE);
        if (defense == 0) return true;
        double defenseReduction = 8.0 / (8.0 + defense);
        double damageAfterDefense = damageAmount * defenseReduction;

//        event.addReductionModifier(DamageContainer.Reduction.ARMOR);

        event.setAmount((float)damageAfterDefense);
        return true;
    }


//    @SubscribeEvent
//    public static void criticalHitEvent(CriticalHitEvent event) {
//        //cancel vanilla crit events
//        if (event.isVanillaCritical()) {
////            event.setDamageMultiplier(1.0f);
////            event.setCriticalHit(false);
//        }
//        //roll affinity
////        event.setDamageMultiplier(1.25f);
////        if (event.getTarget().getRandom().nextFloat() <= 0.5) {
////            event.setCriticalHit(true);
////        }
//    }
}
