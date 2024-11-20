package com.nateplays.mhcraftlands.item.weapons;


import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class WeaponChargedAttackHandler {

    @SubscribeEvent
    public static void chargedAttackEvent(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity causingEntity) {
            LivingEntity targetEntity = event.getEntity();

            ItemStack sourceStack = causingEntity.getWeaponItem();
            int chargeTicks = 0;
            float extraChargeDamage = 0.0f;
            if (sourceStack.isEmpty()) return;

            if (sourceStack.getItem() instanceof GreatSwordItem) {
                float extraChargeDamageMultiplier = GreatSwordItem.getAdditionalChargeDamageMultiplier(sourceStack);
                extraChargeDamage = event.getAmount() * extraChargeDamageMultiplier;
            }
            //test hammer here

            event.setAmount(event.getAmount() + extraChargeDamage);
        }
    }
}
