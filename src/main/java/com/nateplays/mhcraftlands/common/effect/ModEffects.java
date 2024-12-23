package com.nateplays.mhcraftlands.common.effect;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MHMod.MOD_ID);



//    public static final Holder<MobEffect> HUNTING_BUDDY_KO = MOB_EFFECTS.register("hunting_buddy_ko",
//            () -> new HuntingBuddyKOEffect(MobEffectCategory.BENEFICIAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
