package com.nateplays.mhcraftlands.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

import java.awt.Color;

public class BubbleMobEffect extends MHMobEffect {
    public BubbleMobEffect() {
        super(MobEffectCategory.NEUTRAL, Color.white.getRGB());
    }

    //TODO: broken, fix this
    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Vec3 oldVel = livingEntity.getDeltaMovement();
        Vec3 horVel = oldVel.multiply(1, 0, 1); // only horizontal
//        livingEntity.move(MoverType.SELF, oldVel.scale(1.0));
        if (horVel.length() < 0.01) return false;
        horVel = horVel.normalize().scale(2.0);

//        livingEntity.set(oldVel.scale(0.2));
//        livingEntity.setDeltaMovement(horVel.x, oldVel.y, horVel.z);
//        livingEntity.hurtMarked = true;
//        livingEntity.hasImpulse = true;
        if (livingEntity.level() instanceof ServerLevel) {
            livingEntity.addDeltaMovement(new Vec3(1.0f, 0.2f, 1.0f));
            livingEntity.hasImpulse = true;
        }


        return true;
    }
}
