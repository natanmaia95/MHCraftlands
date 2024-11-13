package com.nateplays.my_neoforge_mod.datagen;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.item.ModItems;
import com.nateplays.my_neoforge_mod.item.armor.ModArmorItems;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> MACHALITE_SMELTABLES = List.of(ModItems.MALACHITE_CHUNK, ModBlocks.MACHALITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MACHALITE_BLOCK.get())
                .pattern("III").pattern("III").pattern("III")
                .define('I', ModItems.MALACHITE_INGOT.get())
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MALACHITE_INGOT.get(), 9)
                .requires(ModBlocks.MACHALITE_BLOCK)
                .unlockedBy("has_machalite_block", has(ModBlocks.MACHALITE_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MALACHITE_INGOT.get(), 9)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, MyNeoForgeMod.MODID+":machalite_ingot_from_magic_block");

        oreSmelting(recipeOutput, MACHALITE_SMELTABLES, RecipeCategory.MISC, ModItems.MALACHITE_INGOT.get(), 0.25f, 200, "machalite");
        oreBlasting(recipeOutput, MACHALITE_SMELTABLES, RecipeCategory.MISC, ModItems.MALACHITE_INGOT.get(), 0.25f, 100, "machalite");

        stairBuilder(ModBlocks.MACHALITE_STAIRS.get(), Ingredient.of(ModBlocks.MACHALITE_BLOCK)).group("machalite")
                .unlockedBy("has_machalite_block", has(ModBlocks.MACHALITE_BLOCK)).save(recipeOutput);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MACHALITE_SLAB, Ingredient.of(ModBlocks.MACHALITE_BLOCK)).group("machalite")
                .unlockedBy("has_machalite_block", has(ModBlocks.MACHALITE_BLOCK)).save(recipeOutput);

        buttonBuilder(ModBlocks.MACHALITE_BUTTON.get(), Ingredient.of(ModItems.MALACHITE_INGOT.get())).group("machalite")
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.MACHALITE_PRESSURE_PLATE.get(), ModItems.MALACHITE_INGOT.get());

        fenceBuilder(ModBlocks.MACHALITE_FENCE.get(), Ingredient.of(ModItems.MALACHITE_INGOT.get())).group("machalite")
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.MACHALITE_FENCE_GATE.get(), Ingredient.of(ModItems.MALACHITE_INGOT.get())).group("machalite")
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MACHALITE_WALL.get(), ModItems.MALACHITE_INGOT.get());

        doorBuilder(ModBlocks.MACHALITE_DOOR.get(), Ingredient.of(ModItems.MALACHITE_INGOT.get())).group("machalite")
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.MACHALITE_TRAPDOOR.get(), Ingredient.of(ModItems.MALACHITE_INGOT.get())).group("machalite")
                .unlockedBy("has_machalite_ingot", has(ModItems.MALACHITE_INGOT.get())).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUMMON_FELYNE_VOUCHER)
                .define('F', ItemTags.FISHES).define('E', Items.EMERALD_BLOCK).define('P', Items.PAPER)
                .pattern("FEF").pattern("EPE").pattern("FEF")
                .unlockedBy("has_paper", has(Items.PAPER)).unlockedBy("has_emerald", has(Items.EMERALD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DISMISS_BUDDY_VOUCHER)
                .define('F', ItemTags.FISHES).define('E', Items.PAPER).define('P', Items.PAPER)
                .pattern("FEF").pattern("EPE").pattern("FEF").unlockedBy("has_paper", has(Items.PAPER)).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_WOOD)
                .requires(ItemTags.LOGS).requires(Items.STICK).unlockedBy("has_logs", has(ItemTags.LOGS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_BONE)
                .requires(Items.BONE).requires(Items.STICK).unlockedBy("has_bone", has(Items.BONE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_ORE)
                .requires(ItemTags.IRON_ORES).requires(Items.STICK).unlockedBy("has_iron_ores", has(ItemTags.IRON_ORES)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_ORE)
                .requires(ItemTags.COPPER_ORES).requires(ItemTags.COPPER_ORES).requires(Items.STICK).unlockedBy("has_copper_ores", has(ItemTags.COPPER_ORES))
                .save(recipeOutput, MyNeoForgeMod.MODID+":ore_scrap_from_copper_ores");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_FUR)
                .requires(ItemTags.WOOL).requires(Items.STICK).unlockedBy("has_wool", has(ItemTags.WOOL)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_FUR)
                .requires(Items.LEATHER).requires(Items.STICK).unlockedBy("has_leather", has(Items.LEATHER)).save(recipeOutput, MyNeoForgeMod.MODID+":fur_scrap_from_leather");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_HUMBLE)
                .requires(ItemTags.VILLAGER_PLANTABLE_SEEDS).requires(ItemTags.VILLAGER_PLANTABLE_SEEDS).requires(Items.STICK)
                .unlockedBy("has_villager_plantable_seeds", has(ItemTags.VILLAGER_PLANTABLE_SEEDS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_HUMBLE)
                .requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)).requires(Items.STICK)
                .unlockedBy("has_red_mushroom", has(Items.RED_MUSHROOM)).unlockedBy("has_brown_mushroom", has(Items.BROWN_MUSHROOM))
                .save(recipeOutput, MyNeoForgeMod.MODID+":humble_scrap_from_mushrooms");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_SINISTER, 2)
                .requires(Items.ENDER_PEARL).requires(Items.STICK).unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCRAP_SINISTER)
                .requires(Items.SPIDER_EYE).requires(Items.STICK).unlockedBy("has_spider_eye", has(Items.SPIDER_EYE))
                .save(recipeOutput, MyNeoForgeMod.MODID+":sinister_scrap_from_spider_eye");

        palicoArmor(recipeOutput, "f_acorn_armor", ModArmorItems.F_ACORN_HELM.get(), ModItems.SCRAP_WOOD, Items.OAK_SAPLING);
        palicoArmor(recipeOutput, "f_acorn_armor", ModArmorItems.F_ACORN_MAIL.get(), ModItems.SCRAP_WOOD, Items.OAK_SAPLING);
        palicoArmor(recipeOutput, "f_kamura_armor", ModArmorItems.F_KAMURA_HELM.get(), ModItems.SCRAP_FUR, ItemTags.WOOL_CARPETS);
        palicoArmor(recipeOutput, "f_kamura_armor", ModArmorItems.F_KAMURA_MAIL.get(), ModItems.SCRAP_FUR, ItemTags.WOOL_CARPETS);
        palicoArmor(recipeOutput, "f_bone_armor", ModArmorItems.F_BONE_HELM.get(), ModItems.SCRAP_BONE, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_bone_armor", ModArmorItems.F_BONE_MAIL.get(), ModItems.SCRAP_BONE, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_alloy_armor", ModArmorItems.F_ALLOY_HELM.get(), ModItems.SCRAP_ORE, Items.IRON_INGOT);
        palicoArmor(recipeOutput, "f_alloy_armor", ModArmorItems.F_ALLOY_MAIL.get(), ModItems.SCRAP_ORE, Items.IRON_INGOT);
        palicoArmor(recipeOutput, "f_red_armor", ModArmorItems.F_RED_HELM.get(), ModItems.SCRAP_FUR, Items.RED_WOOL);
        palicoArmor(recipeOutput, "f_red_armor", ModArmorItems.F_RED_MAIL.get(), ModItems.SCRAP_FUR, Items.RED_WOOL);
        palicoArmor(recipeOutput, "f_mosgharl_armor", ModArmorItems.F_MOSGHARL_HELM.get(), ModItems.SCRAP_HUMBLE, Ingredient.EMPTY);
        palicoArmor(recipeOutput, "f_mosgharl_armor", ModArmorItems.F_MOSGHARL_MAIL.get(), ModItems.SCRAP_HUMBLE, Items.CARVED_PUMPKIN);
        palicoArmor(recipeOutput, "f_frankie_armor", ModArmorItems.F_FRANKIE_HELM.get(), ModItems.SCRAP_SINISTER, Items.ROTTEN_FLESH);
        palicoArmor(recipeOutput, "f_frankie_armor", ModArmorItems.F_FRANKIE_MAIL.get(), ModItems.SCRAP_SINISTER, Items.ROTTEN_FLESH);
        palicoArmor(recipeOutput, "f_ghost_armor", ModArmorItems.F_GHOST_HELM.get(), ModItems.SCRAP_SINISTER, Items.CARVED_PUMPKIN);
        palicoArmor(recipeOutput, "f_ghost_armor", ModArmorItems.F_GHOST_MAIL.get(), ModItems.SCRAP_SINISTER, Items.WHITE_BANNER);
    }


    protected static void palicoArmor(RecipeOutput recipeOutput, String group, PetHuntingArmorItem<?,?> result,
                                      ItemLike firstMaterial, @Nullable TagKey<Item> secondMaterialTag) {
        palicoArmor(recipeOutput, group, result, firstMaterial, secondMaterialTag != null ? Ingredient.of(secondMaterialTag) : null);
    }

    protected static void palicoArmor(RecipeOutput recipeOutput, String group, PetHuntingArmorItem<?,?> result,
                                      ItemLike firstMaterial, @Nullable ItemLike secondMaterial) {
        palicoArmor(recipeOutput, group, result, firstMaterial, secondMaterial != null ? Ingredient.of(secondMaterial) : null);
    }

    protected static void palicoArmor(RecipeOutput recipeOutput, String group, PetHuntingArmorItem<?,?> result,
                                      ItemLike firstMaterial, @Nullable Ingredient secondMaterial) {

        boolean noSecondMaterial = secondMaterial == null || secondMaterial.isEmpty();

        String pattern1, pattern2;
        if (result.getEquipmentSlot() == EquipmentSlot.HEAD) {
            pattern1 = " A ";
            pattern2 = noSecondMaterial ? "A A" : "ABA";
        } else { //if (result.getEquipmentSlot() == EquipmentSlot.CHEST) {
            pattern1 = noSecondMaterial ? "A A" : "ABA";
            pattern2 = " A ";
        }

        ShapedRecipeBuilder recipeBuilder = ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).group(group)
                .pattern(pattern1).pattern(pattern2)
                .define('A', firstMaterial)
                .unlockedBy("has_" + firstMaterial.asItem().toString(), has(firstMaterial));

        System.out.println("MAT" + firstMaterial.asItem().toString());

        if (!noSecondMaterial) {
            recipeBuilder = recipeBuilder.define('B', secondMaterial);
//                    .unlockedBy("has_" + secondMaterial.asItem().toString(), has(secondMaterial));
        }

        recipeBuilder.save(recipeOutput);
    }


    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
                                      ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new,
                ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
                                      ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new,
                ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput recipeOutput,
            RecipeSerializer<T> serializer,
            AbstractCookingRecipe.Factory<T> recipeFactory,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String suffix
    ) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MyNeoForgeMod.MODID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }
}
