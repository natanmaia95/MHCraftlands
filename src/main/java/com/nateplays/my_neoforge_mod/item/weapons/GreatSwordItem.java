package com.nateplays.my_neoforge_mod.item.weapons;

import com.nateplays.my_neoforge_mod.component.ModDataComponents;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreatSwordItem extends HuntingWeaponItem {
    static final Logger LOGGER = LoggerFactory.getLogger(GreatSwordItem.class);

    public GreatSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier) {
        return HuntingWeaponItem.createAttributes(tier, getAttackDamageMultiplier(), -3.4f);
    }

    public static float getAttackDamageMultiplier() {
        return 3.5F;
    }

    public float getUseItemSlowdown(Player player, ItemStack stack) {
        return 0.05F;
    }

    public static float getAdditionalChargeDamageMultiplier(ItemStack stack) {
        int chargeTicks = stack.getOrDefault(ModDataComponents.WEAPON_CHARGE, 0);
        if (chargeTicks >= 70) return 1.0f;
        if (chargeTicks >= 50) return 3.0f; //TODO: make this more powerful if you do successful charge hits
        if (chargeTicks >= 30) return 1.0f;
        if (chargeTicks >= 10) return 0.5f;
        return 0.0f;
    }


    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        stack.set(ModDataComponents.WEAPON_CHARGE, 0);
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getMainHandItem() == stack) {
                if (!level.isClientSide()) {
                    if (livingEntity instanceof Player player && player.getAttackStrengthScale(0) < 0.3F) {
                        stack.set(ModDataComponents.WEAPON_CHARGE, 0);
                    } else if (livingEntity.isShiftKeyDown()) {
                        chargeWeaponTick(stack, level, livingEntity);
//                    int oldCharge = stack.getOrDefault(ModDataComponents.WEAPON_CHARGE, 0);
//                    stack.set(ModDataComponents.WEAPON_CHARGE, oldCharge + 1);
//                    stack.setPopTime(0);
                    } else {
                        stack.set(ModDataComponents.WEAPON_CHARGE, 0);
                    }
                } else {
                    chargeWeaponClientTick(stack, level, livingEntity);
                }
                chargeWeaponVisuals(stack, level, livingEntity);
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public void chargeWeaponTick(ItemStack stack, Level level, LivingEntity livingEntity) {
        int oldCharge = stack.getOrDefault(ModDataComponents.WEAPON_CHARGE, 0);
        int newCharge = oldCharge + 1;
        stack.set(ModDataComponents.WEAPON_CHARGE, newCharge);
    }

    public void chargeWeaponClientTick(ItemStack stack, Level level, LivingEntity livingEntity) {

    }

    public void chargeWeaponVisuals(ItemStack stack, Level level, LivingEntity livingEntity) {
        int newCharge = stack.getOrDefault(ModDataComponents.WEAPON_CHARGE, 0);
        //particles
        double pY = livingEntity.getY() + 1.0;
        double pX = livingEntity.getX();
        double pZ = livingEntity.getZ();

        if (newCharge > 0 && newCharge % 3 == 0) {
            int particleCount = 8;
            double radius = 0.5;
            for (int i = 0; i < particleCount; i++) {
                // Calculate the angle around the circle (in radians)
                double angle = (2 * Math.PI * i) / particleCount;

                // Calculate X and Z position for the particle
                double offsetX = pX + radius * Math.cos(angle);
                double offsetZ = pZ + radius * Math.sin(angle);

                // Spawn the particle at the calculated position (Y can be adjusted if needed)
                level.addParticle(ParticleTypes.ELECTRIC_SPARK, offsetX, livingEntity.getY() + 0.1, offsetZ, 0.0, 1.0, 0.0);
                if (newCharge >= 50 && newCharge < 70) {
                    level.addParticle(ParticleTypes.ENCHANTED_HIT, offsetX, livingEntity.getY() + 0.0, offsetZ, 0.0, 1.0, 0.0);
                }
            }
        }

        if (newCharge == 10) {
            LOGGER.debug("1");
            level.playSound(null, pX, pY, pZ, SoundEvents.TRIDENT_RETURN, SoundSource.NEUTRAL);
            level.addParticle(ParticleTypes.ANGRY_VILLAGER, pX, pY, pZ, 0.0, 0.0, 0.0);
        }
        if (newCharge == 30) {
            LOGGER.debug("2");
            level.playSound(null, pX, pY, pZ, SoundEvents.TRIDENT_RETURN, SoundSource.NEUTRAL);
            level.addParticle(ParticleTypes.ANGRY_VILLAGER, pX, pY, pZ, 0.0, 0.5, 0.0);
        }
        if (newCharge == 50) {
            LOGGER.debug("3");
            level.playSound(null, pX, pY, pZ, SoundEvents.TRIDENT_RETURN, SoundSource.NEUTRAL);
            level.addParticle(ParticleTypes.ANGRY_VILLAGER, pX, pY, pZ, 0.0, 1.0, 0.0);
        }
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    //    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack itemStack = player.getItemInHand(hand);
//        player.startUsingItem(hand);
//        return InteractionResultHolder.consume(itemStack);
//    }


//    @Override
//    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {
//        super.releaseUsing(stack, level, livingEntity, timeCharged);
//    }

//    public UseAnim getUseAnimation(ItemStack stack) {
//        return UseAnim.BOW;
//    }
//
//    public int getUseDuration(ItemStack stack, LivingEntity entity) {
//        return 72000;
//    }
}
