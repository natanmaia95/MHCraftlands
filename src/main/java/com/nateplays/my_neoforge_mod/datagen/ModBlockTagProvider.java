package com.nateplays.my_neoforge_mod.datagen;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
    }
}
