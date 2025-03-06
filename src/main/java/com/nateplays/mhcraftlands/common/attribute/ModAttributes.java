package com.nateplays.mhcraftlands.common.attribute;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;
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

    public static final DeferredHolder<Attribute, Attribute> FIRE_WEAKNESS = ATTRIBUTES.register("fire_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.fire_elemental_weakness", 1.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WATER_WEAKNESS = ATTRIBUTES.register("water_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.water_elemental_weakness", 1.0, 0.0, 1024.0).setSyncable(true));

    public static final Map<DeferredHolder<Attribute, Attribute>, DeferredHolder<Attribute, Attribute>> ELEMENT_DAMAGE_TO_WEAKNESS = Map.of(
            FIRE_DAMAGE, FIRE_WEAKNESS, WATER_DAMAGE, WATER_WEAKNESS
    );

    private static <T extends Attribute> DeferredHolder<Attribute, Attribute> registerAttribute(String name, Supplier<Attribute> attr) {
        return ATTRIBUTES.register(name, attr);
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }



    public static List<DeferredHolder<Attribute, Attribute>> allElemWeaknesses() {
        return List.of(FIRE_WEAKNESS, WATER_WEAKNESS);
    }

    public static List<DeferredHolder<Attribute, Attribute>> allElemDamages() {
        return List.of(FIRE_DAMAGE, WATER_DAMAGE);
    }


}
