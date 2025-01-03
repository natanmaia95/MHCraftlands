package com.nateplays.mhcraftlands.common.effect;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.awt.Color;

public class PseudoParalysisMobEffect extends MHMobEffect {
    public PseudoParalysisMobEffect() {
        super(MobEffectCategory.HARMFUL, Color.orange.getRGB());

    }

    public MHMobEffect withAttributeMods() {
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.null_speed_pseudoparalysis"),
                -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.JUMP_STRENGTH,
                ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.null_jump_pseudoparalysis"),
                -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.null_damage_pseudoparalysis"),
                -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        return this;
    }
}
