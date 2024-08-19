package com.nateplays.my_neoforge_mod.block;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MyNeoForgeMod.MODID);



    public static final DeferredBlock<Block> EARTH_CRYSTAL_ORE = registerBlock("earth_crystal_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(1f,2f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> MACHALITE_ORE = registerBlock("machalite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(4f,2f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> NULBERRY_BUSH = registerBlock("nulberry_bush", NulberryBushBlock::new);



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
