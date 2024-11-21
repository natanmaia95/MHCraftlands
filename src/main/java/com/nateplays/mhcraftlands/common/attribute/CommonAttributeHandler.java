package com.nateplays.mhcraftlands.common.attribute;

import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class CommonAttributeHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void livingEntityUseItemTick(LivingEntityUseItemEvent.Tick event) {
        handleAttributeEatingSpeed(event);
    }

    public static void handleAttributeEatingSpeed(LivingEntityUseItemEvent.Tick event) {
        LivingEntity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        if (entity instanceof Player player) {
            if (player.level().isClientSide()) return;
            double eatingSpeed = player.getAttributeValue(ModAttributes.EATING_SPEED);
            int adjustedDuration = itemStack.getUseDuration(entity) - (int) (itemStack.getUseDuration(entity) / eatingSpeed);
            //make use animation start ahead
            if (event.getDuration() == itemStack.getUseDuration(entity)) {
                if (!itemStack.useOnRelease()) {
                    event.setDuration((int) (itemStack.getUseDuration(entity) / eatingSpeed));
                }
            }
        }
    }

}
