package com.nateplays.mhcraftlands.pet.effect;

import com.nateplays.mhcraftlands.common.effect.MHMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.awt.Color;

public class FurbiddenAcornMobEffect extends MHMobEffect {
    public FurbiddenAcornMobEffect() {
        super(MobEffectCategory.BENEFICIAL, Color.magenta.getRGB());
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier); //play sound
        livingEntity.heal(livingEntity.getMaxHealth());
    }

}
