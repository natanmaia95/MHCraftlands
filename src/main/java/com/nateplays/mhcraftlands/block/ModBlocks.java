package com.nateplays.mhcraftlands.block;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.block.custom.MagicBlock;
import com.nateplays.mhcraftlands.block.custom.MagicLeafBlock;
import com.nateplays.mhcraftlands.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MHMod.MOD_ID);



    public static final DeferredBlock<Block> EARTH_CRYSTAL_ORE = registerBlock("earth_crystal_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(1f,2f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> MACHALITE_ORE = registerBlock("machalite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> MACHALITE_BLOCK = registerBlock("machalite_block",
            () -> new Block(
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<StairBlock> MACHALITE_STAIRS = registerBlock("machalite_stairs",
            () -> new StairBlock(ModBlocks.MACHALITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<SlabBlock> MACHALITE_SLAB = registerBlock("machalite_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<PressurePlateBlock> MACHALITE_PRESSURE_PLATE = registerBlock("machalite_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<ButtonBlock> MACHALITE_BUTTON = registerBlock("machalite_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20,
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops().noCollission()
            ));
    public static final DeferredBlock<FenceBlock> MACHALITE_FENCE = registerBlock("machalite_fence",
            () -> new FenceBlock(
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<FenceGateBlock> MACHALITE_FENCE_GATE = registerBlock("machalite_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA,
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<WallBlock> MACHALITE_WALL = registerBlock("machalite_wall",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<DoorBlock> MACHALITE_DOOR = registerBlock("machalite_door",
            () -> new DoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops().noOcclusion()
            ));
    public static final DeferredBlock<TrapDoorBlock> MACHALITE_TRAPDOOR = registerBlock("machalite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(4f,2f).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> NULBERRY_BUSH = registerBlock("nulberry_bush", NulberryBushBlock::new);

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(
                    BlockBehaviour.Properties.of().strength(1f,2f).sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<MagicLeafBlock> MAGIC_LEAF = registerBlock("magic_leaf",
            () -> new MagicLeafBlock(BlockBehaviour.Properties.of().strength(0.1f).sound(SoundType.GRASS)
                    .lightLevel(state -> state.getValue(MagicLeafBlock.CLICKED) ? 15 : 0)
            ));



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
