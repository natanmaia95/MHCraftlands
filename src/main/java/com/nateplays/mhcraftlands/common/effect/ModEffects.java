package com.nateplays.mhcraftlands.common.effect;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.Color;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MHMod.MOD_ID);



//    public static final Holder<MobEffect> HUNTING_BUDDY_KO = MOB_EFFECTS.register("hunting_buddy_ko",
//            () -> new HuntingBuddyKOEffect(MobEffectCategory.BENEFICIAL));


//    //attack buffs
//    public static final Holder<MobEffect> ATTACK_BOOST_HORN = MOB_EFFECTS.register("attack_boost_horn",
//            () -> new MobEffect(
//
//            )
//            )

    public static final Holder<MobEffect> ATTACK_BOOST_BUFF = MOB_EFFECTS.register("attack_boost_buff",
            () -> new MHMobEffect(MobEffectCategory.BENEFICIAL, Color.RED.getRGB())
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.attack_boost_buff"), 0.1, AttributeModifier.Operation.ADD_VALUE
                    )
    );

    public static final Holder<MobEffect> ATTACK_BOOST_FOOD = MOB_EFFECTS.register("attack_boost_food",
            () -> new MHMobEffect(MobEffectCategory.BENEFICIAL, Color.RED.getRGB())
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.attack_boost_food"), 0.1, AttributeModifier.Operation.ADD_VALUE
                    )
    );

    public static final Holder<MobEffect> ATTACK_BOOST_SKILL = MOB_EFFECTS.register("attack_boost_skill",
            () -> new MHMobEffect(MobEffectCategory.BENEFICIAL, Color.RED.getRGB())
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "effect.attack_boost_skill"), 0.1, AttributeModifier.Operation.ADD_VALUE
                    )

    );





    public static final Holder<MobEffect> STATUS_PSEUDOPARALYSIS = MOB_EFFECTS.register("status_pseudoparalysis",
            () -> new PseudoParalysisMobEffect().withAttributeMods());

    public static final Holder<MobEffect> STATUS_PSEUDOPOISON = MOB_EFFECTS.register("status_pseudopoison",
            () -> new PseudoPoisonMobEffect());

    public static final Holder<MobEffect> STATUS_BUBBLE = MOB_EFFECTS.register("status_bubble",
            () -> new BubbleMobEffect());


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
