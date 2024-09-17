package com.nateplays.my_neoforge_mod.event;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class EventBusEvents {

    @SubscribeEvent
    public static void livingDeathEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!livingEntity.level().isClientSide()) {
            int radius = 32;

            ServerLevel serverLevel = (ServerLevel) livingEntity.level();
            int experienceReward = livingEntity.getExperienceReward(serverLevel, null);
            Vec3 position = livingEntity.position();
            AABB aabb = new AABB(position.add(radius, radius, radius), position.add(-radius, -radius, -radius));

            List<LivingEntity> entitiesAroundDeath = serverLevel.getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity entity : entitiesAroundDeath) {
                if (entity instanceof ILevelableEntity levelableEntity) {
                    levelableEntity.gainExp(experienceReward);
                }
            }
        }
    }
}
