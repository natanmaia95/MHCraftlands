package com.nateplays.my_neoforge_mod.attribute;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MyNeoForgeMod.MODID);

    public static final DeferredHolder<Attribute, Attribute> EATING_SPEED = ATTRIBUTES.register("player.eating_speed",
            () -> new RangedAttribute("attribute.my_neoforge_mod.player.eating_speed", 1.0, 0.0, 1024.0).setSyncable(true));

    private static <T extends Attribute> DeferredHolder<Attribute, Attribute> registerAttribute(String name, Supplier<Attribute> attr) {
        return ATTRIBUTES.register(name, attr);
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
