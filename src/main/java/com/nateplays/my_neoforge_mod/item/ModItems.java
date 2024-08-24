package com.nateplays.my_neoforge_mod.item;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyNeoForgeMod.MODID);



    public static final DeferredItem<Item> EARTH_CRYSTAL = ITEMS.register("earth_crystal",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_CHUNK = ITEMS.register("machalite_chunk",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_INGOT = ITEMS.register("machalite_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NULBERRY = ITEMS.register("nulberry",
            () -> new NulberryItem(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
