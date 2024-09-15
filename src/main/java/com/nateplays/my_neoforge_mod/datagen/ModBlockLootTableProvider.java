package com.nateplays.my_neoforge_mod.datagen;

import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.block.NulberryBushBlock;
import com.nateplays.my_neoforge_mod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(ModBlocks.MACHALITE_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        add(ModBlocks.EARTH_CRYSTAL_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.EARTH_CRYSTAL_ORE.get(), ModItems.EARTH_CRYSTAL.get(), 1.0F, 3.0F));
        add(ModBlocks.MACHALITE_ORE.get(),
            block -> createOreDrop(ModBlocks.MACHALITE_ORE.get(), ModItems.MALACHITE_CHUNK.get()));

        add(ModBlocks.NULBERRY_BUSH.get(),
            block -> this.applyExplosionDecay(block, LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .when(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.NULBERRY_BUSH.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NulberryBushBlock.AGE, 3))
                        )
                        .add(LootItem.lootTableItem(ModItems.NULBERRY.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE)))
                )
                .withPool(
                    LootPool.lootPool()
                        .when(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.NULBERRY_BUSH.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NulberryBushBlock.AGE, 2))
                        )
                        .add(LootItem.lootTableItem(ModItems.NULBERRY.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE)))
                )));
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                //.apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(ModEnchantments.MINING_MASTER.get())))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
