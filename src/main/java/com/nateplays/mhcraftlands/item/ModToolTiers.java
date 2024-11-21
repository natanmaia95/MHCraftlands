package com.nateplays.mhcraftlands.item;

import com.nateplays.mhcraftlands.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.function.Supplier;

public class ModToolTiers {
    public static final Tier MACHALITE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_MACHALITE_TOOL,
            128, 3, 2, 0,
            () -> Ingredient.of(ModItems.MALACHITE_INGOT.get())
    );
}
