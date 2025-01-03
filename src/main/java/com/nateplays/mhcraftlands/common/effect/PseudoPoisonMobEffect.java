package com.nateplays.mhcraftlands.common.effect;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.awt.*;

public class PseudoPoisonMobEffect extends MHMobEffect {
    public PseudoPoisonMobEffect() {
        super(MobEffectCategory.HARMFUL, Color.decode("#8f32a8").getRGB());
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        if ((duration+1) % 40 == 0) return true;
        return super.shouldApplyEffectTickThisTick(duration, amplifier);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
//        float newHealth = livingEntity.getHealth() - (1+amplifier);
        if (livingEntity.getHealth() > 1+amplifier) {
            livingEntity.hurt(livingEntity.damageSources().cramming(), 1+amplifier);
//            livingEntity.setHealth(newHealth);
            livingEntity.hurtDuration = 0;
            return true;
        } else return false;
    }
}
