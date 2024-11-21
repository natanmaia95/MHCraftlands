package com.nateplays.mhcraftlands.pet.sound;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MHPetSounds {
    public static final DeferredRegister<SoundEvent> PET_SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MHMod.MOD_ID);




    public static final Supplier<SoundEvent> FELYNE_AMBIENT = registerSoundEvent("palico/felyne_ambient");
    public static final Supplier<SoundEvent> FELYNE_HURT = registerSoundEvent("palico/felyne_hurt");
    public static final Supplier<SoundEvent> FELYNE_DEATH = registerSoundEvent("palico/felyne_death");




    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name);
        return PET_SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        PET_SOUND_EVENTS.register(eventBus);
    }
}
