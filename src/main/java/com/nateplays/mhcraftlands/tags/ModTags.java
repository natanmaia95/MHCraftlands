package com.nateplays.mhcraftlands.tags;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_MACHALITE_TOOL = createTag("needs_machalite_tool");
        public static final TagKey<Block> INCORRECT_FOR_MACHALITE_TOOL = createTag("incorrect_for_machalite_tool");

        public static final TagKey<Block> PALICO_HARVESTABLE_PLANTS = createTag("palico_harvestable_plants");
        public static final TagKey<Block> PALICO_HARVESTABLE_ORES = createTag("palico_harvestable_ores");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        public static final TagKey<Item> DYEABLE_HUNTING_ARMORS = createTag("dyeable_hunting_armors");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name));
        }
    }

    public static class Enchantments {


        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name));
        }
    }
}
