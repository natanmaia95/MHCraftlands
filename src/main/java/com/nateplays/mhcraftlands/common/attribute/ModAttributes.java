package com.nateplays.mhcraftlands.common.attribute;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;
import java.util.function.Supplier;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MHMod.MOD_ID);
    private static Map<String, DeferredHolder<Attribute, Attribute>> ATTRIBUTE_NAMES = new HashMap<>();


    public static final DeferredHolder<Attribute, Attribute> EATING_SPEED = registerAttribute("player.eating_speed",
            () -> new RangedAttribute("attribute.mhcraftlands.player.eating_speed", 1.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DEFENSE = registerAttribute("defense",
            () -> new RangedAttribute("attribute.mhcraftlands.defense", 0.0, 0.0, 1024.0).setSyncable(true));



    public static final DeferredHolder<Attribute, Attribute> FIRE_DAMAGE = registerAttribute("fire_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.fire_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WATER_DAMAGE = registerAttribute("water_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.water_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> THUNDER_DAMAGE = registerAttribute("thunder_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.thunder_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> ICE_DAMAGE = registerAttribute("ice_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.ice_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DRAGON_DAMAGE = registerAttribute("dragon_elemental_damage",
            () -> new RangedAttribute("attribute.mhcraftlands.dragon_elemental_damage", 0.0, 0.0, 1024.0).setSyncable(true));

    //TODO: remove element weakness
    public static final DeferredHolder<Attribute, Attribute> FIRE_WEAKNESS = registerAttribute("fire_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.fire_elemental_weakness", 1.0, 0.0, 10.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WATER_WEAKNESS = registerAttribute("water_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.water_elemental_weakness", 1.0, 0.0, 10.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> THUNDER_WEAKNESS = registerAttribute("thunder_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.thunder_elemental_weakness", 1.0, 0.0, 10.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> ICE_WEAKNESS = registerAttribute("ice_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.ice_elemental_weakness", 1.0, 0.0, 10.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DRAGON_WEAKNESS = registerAttribute("dragon_elemental_weakness",
            () -> new RangedAttribute("attribute.mhcraftlands.dragon_elemental_weakness", 1.0, 0.0, 10.0).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> FIRE_RESISTANCE = registerAttribute("fire_elemental_resistance",
            () -> new RangedAttribute("attribute.mhcraftlands.fire_elemental_resistance", 0.0, -100.0, 100.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WATER_RESISTANCE = registerAttribute("water_elemental_resistance",
            () -> new RangedAttribute("attribute.mhcraftlands.water_elemental_resistance", 0.0, -100.0, 100.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> THUNDER_RESISTANCE = registerAttribute("thunder_elemental_resistance",
            () -> new RangedAttribute("attribute.mhcraftlands.thunder_elemental_resistance", 0.0, -100.0, 100.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> ICE_RESISTANCE = registerAttribute("ice_elemental_resistance",
            () -> new RangedAttribute("attribute.mhcraftlands.ice_elemental_resistance", 0.0, -100.0, 100.0).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DRAGON_RESISTANCE = registerAttribute("dragon_elemental_resistance",
            () -> new RangedAttribute("attribute.mhcraftlands.dragon_elemental_resistance", 0.0, -100.0, 100.0).setSyncable(true));



    public static final Map<DeferredHolder<Attribute, Attribute>, DeferredHolder<Attribute, Attribute>> ELEMENT_DAMAGE_TO_WEAKNESS = Map.of(
            FIRE_DAMAGE, FIRE_WEAKNESS, WATER_DAMAGE, WATER_WEAKNESS, THUNDER_DAMAGE, THUNDER_WEAKNESS, ICE_DAMAGE, ICE_WEAKNESS, DRAGON_DAMAGE, DRAGON_WEAKNESS
    );

    public static final Map<DeferredHolder<Attribute, Attribute>, DeferredHolder<Attribute, Attribute>> ELEMENT_DAMAGE_TO_RESISTANCE = Map.of(
            FIRE_DAMAGE, FIRE_RESISTANCE,
            WATER_DAMAGE, WATER_RESISTANCE,
            THUNDER_DAMAGE, THUNDER_RESISTANCE,
            ICE_DAMAGE, ICE_RESISTANCE,
            DRAGON_DAMAGE, DRAGON_RESISTANCE
    );

    public static DeferredHolder<Attribute, Attribute> fromName(String name) {
        return ATTRIBUTE_NAMES.get(name);
    }



    public static Collection<DeferredHolder<Attribute, Attribute>> allElemResistances() {
        return ELEMENT_DAMAGE_TO_RESISTANCE.values();//.stream().toList();
    }

    public static Collection<DeferredHolder<Attribute, Attribute>> allElemWeaknesses() {
        return ELEMENT_DAMAGE_TO_WEAKNESS.values();//.stream().toList();
    }

    public static Set<DeferredHolder<Attribute, Attribute>> allElemDamages() {
        return ELEMENT_DAMAGE_TO_RESISTANCE.keySet();//.stream().toList();
    }




    private static <T extends Attribute> DeferredHolder<Attribute, Attribute> registerAttribute(String name, Supplier<Attribute> attr) {
        DeferredHolder<Attribute, Attribute> result = ATTRIBUTES.register(name, attr);
        ATTRIBUTE_NAMES.put(name, result);
        return result;
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
