package com.nateplays.my_neoforge_mod.datagen;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MyNeoForgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.MACHALITE_BLOCK.get()).add(ModBlocks.MACHALITE_ORE.get())
                    .add(ModBlocks.EARTH_CRYSTAL_ORE.get()).add(ModBlocks.MAGIC_BLOCK.get());

            tag(BlockTags.NEEDS_STONE_TOOL)
                    .add(ModBlocks.MACHALITE_BLOCK.get()).add(ModBlocks.MACHALITE_ORE.get());

            tag(BlockTags.FENCES)
                    .add(ModBlocks.MACHALITE_FENCE.get());
            tag(BlockTags.FENCE_GATES)
                    .add(ModBlocks.MACHALITE_FENCE_GATE.get());
            tag(BlockTags.WALLS)
                    .add(ModBlocks.MACHALITE_WALL.get());

        tag(ModTags.Blocks.NEEDS_MACHALITE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_MACHALITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_MACHALITE_TOOL);


        tag(ModTags.Blocks.PALICO_HARVESTABLE_PLANTS)
                .addTag(BlockTags.SMALL_FLOWERS)
                .addTag(BlockTags.COAL_ORES).addTag(BlockTags.IRON_ORES)
                .add(Blocks.SWEET_BERRY_BUSH);
    }
}
