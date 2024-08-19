package com.nateplays.my_neoforge_mod.item;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyNeoForgeMod.MODID);

    public static final DeferredItem<Item> EARTH_CRYSTAL = ITEMS.register("earth_crystal",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_CHUNK = ITEMS.register("machalite_chunk",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NULBERRY = ITEMS.register("nulberry",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().alwaysEdible().fast().nutrition(1).saturationModifier(0.5f).build()
            )));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
