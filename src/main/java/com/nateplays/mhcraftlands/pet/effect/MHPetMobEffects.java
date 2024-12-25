package com.nateplays.mhcraftlands.pet.effect;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.effect.MHMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;

public class MHPetMobEffects {
    public static final DeferredRegister<MobEffect> PET_MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MHMod.MOD_ID);



    public static final Holder<MobEffect> FURBIDDEN_ACORN_AURA = PET_MOB_EFFECTS.register("furbidden_acorn_aura",
            () -> new FurbiddenAcornMobEffect().addAttributeModifier(
                        Attributes.MAX_HEALTH,
                        ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.furbidden_acorn_aura"),
                0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );



    public static void register(IEventBus eventBus) {
        PET_MOB_EFFECTS.register(eventBus);
    }
}
