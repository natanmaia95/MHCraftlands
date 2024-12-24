package com.nateplays.mhcraftlands.common.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MHMobEffect extends MobEffect {
    public MHMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public MHMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }
}
