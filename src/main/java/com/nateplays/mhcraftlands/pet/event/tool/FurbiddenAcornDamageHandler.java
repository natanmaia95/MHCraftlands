package com.nateplays.mhcraftlands.pet.event.tool;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.effect.MHPetMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class FurbiddenAcornDamageHandler {

    @SubscribeEvent
    public static void entityIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity e = event.getEntity();
        if (e.hasEffect(MHPetMobEffects.FURBIDDEN_ACORN_AURA)) {
            if (e.getRandom().nextFloat() < 0.8f) {
                event.setAmount(0);
                event.setCanceled(true);

                //Player movement only happens on client; entity movement only happens on server
                //so place this on the outside of ServerLevel check.
                Vec3 lookDirection = e.getLookAngle();
                Vec3 horLookDir = new Vec3(lookDirection.x, 0, lookDirection.z).normalize();
                if (e.getRandom().nextFloat() < 0.5f) horLookDir = horLookDir.yRot(Mth.HALF_PI);
                else horLookDir = horLookDir.yRot(-Mth.HALF_PI);
                Vec3 knockback = horLookDir.scale(0.5);
                e.setDeltaMovement(knockback.x, 0.3, knockback.z);
                e.hurtMarked = true;


                if (e.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.POOF,
                            e.getX(), e.getY()+0.5, e.getZ(),
                            5, 0.5,0,0.5,0.1);
                    serverLevel.playSound(null, e.blockPosition(), SoundEvents.WIND_CHARGE_THROW, SoundSource.NEUTRAL);
                }
            }
        }
    }
}
