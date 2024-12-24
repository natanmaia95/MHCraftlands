package com.nateplays.mhcraftlands.sound;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
        DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MHMod.MOD_ID);






    public static final Supplier<SoundEvent> DISC_HUNTERGOFORTH = registerSoundEvent("music/disc_huntergoforth");
    public static final ResourceKey<JukeboxSong> DISCKEY_HUNTERGOFORTH = createSong("huntergoforth");

    public static final Supplier<SoundEvent> ITEM_HEAL = registerSoundEvent("item/heal");
    public static final Supplier<SoundEvent> ITEM_HORN_1 = registerSoundEvent("item/horn_1");
    public static final Supplier<SoundEvent> ITEM_HORN_2 = registerSoundEvent("item/horn_2");



    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
