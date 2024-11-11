package com.nateplays.my_neoforge_mod.entity.pets.event;

import com.mojang.logging.LogUtils;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.effect.HuntingBuddyKOEffect;
import com.nateplays.my_neoforge_mod.effect.ModEffects;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.entity.pets.HuntingBuddyEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.slf4j.Logger;

import java.util.List;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class HuntingBuddyDeathHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void livingDeathEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level();
        if (!level.isClientSide() && livingEntity instanceof HuntingBuddyEntity buddyEntity) {
            if (buddyEntity.isTame()) {
                event.setCanceled(true);
                level.playLocalSound(buddyEntity,
                        SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.NEUTRAL, 1.0f, 1.0f);

                buddyEntity.setHealth(0.1f);
//                buddyEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 4));
                buddyEntity.addEffect(new MobEffectInstance(ModEffects.HUNTING_BUDDY_KO, HuntingBuddyKOEffect.DURATION));
            }
        }
    }

    @SubscribeEvent
    public static void livingDamageEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof HuntingBuddyEntity buddyEntity) {
            if (buddyEntity.hasEffect(ModEffects.HUNTING_BUDDY_KO)) {
                event.setCanceled(true);
            }
        }
    }

}
