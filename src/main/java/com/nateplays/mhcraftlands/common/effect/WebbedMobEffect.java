package com.nateplays.mhcraftlands.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class WebbedMobEffect extends MHMobEffect {
    public WebbedMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Vec3 movementMultiplier = new Vec3(0.5, 0.5, 0.5);
        livingEntity.makeStuckInBlock(livingEntity.getInBlockState(), movementMultiplier);
        return true;
    }
}
