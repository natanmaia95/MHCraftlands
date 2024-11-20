package com.nateplays.mhcraftlands.attribute;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MHMod.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> EATING_SPEED = ATTRIBUTES.register("player.eating_speed",
            () -> new RangedAttribute("attribute.mhcraftlands.player.eating_speed", 1.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DEFENSE = ATTRIBUTES.register("defense",
            () -> new RangedAttribute("attribute.mhcraftlands.defense", 0.0, 0.0, 1024.0).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> FIRE_DAMAGE = ATTRIBUTES.register("fire_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.fire_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WATER_DAMAGE = ATTRIBUTES.register("water_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.water_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));


    private static <T extends Attribute> DeferredHolder<Attribute, Attribute> registerAttribute(String name, Supplier<Attribute> attr) {
        return ATTRIBUTES.register(name, attr);
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
