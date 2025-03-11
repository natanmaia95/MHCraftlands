package com.nateplays.mhcraftlands.datagen;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.block.ModBlocks;
import com.nateplays.mhcraftlands.item.ModItems;
import com.nateplays.mhcraftlands.common.armor.ModArmorItems;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.common.weapon.ModWeaponItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
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
import org.jetbrains.annotations.NotNull;

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
                .save(recipeOutput, MHMod.MOD_ID +":machalite_ingot_from_magic_block");

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


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MHPetItems.SUMMON_FELYNE_VOUCHER)
                .define('F', ItemTags.FISHES).define('E', Items.EMERALD_BLOCK).define('P', Items.PAPER)
                .pattern("FEF").pattern("EPE").pattern("FEF")
                .unlockedBy("has_paper", has(Items.PAPER)).unlockedBy("has_emerald", has(Items.EMERALD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MHPetItems.DISMISS_BUDDY_VOUCHER)
                .define('F', ItemTags.FISHES).define('E', Items.PAPER).define('P', Items.PAPER)
                .pattern("FEF").pattern("EPE").pattern("FEF").unlockedBy("has_paper", has(Items.PAPER)).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_WOOD)
                .requires(ItemTags.LOGS).requires(Items.STICK).unlockedBy("has_logs", has(ItemTags.LOGS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_BONE)
                .requires(Items.BONE).requires(Items.STICK).unlockedBy("has_bone", has(Items.BONE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_ORE)
                .requires(Items.RAW_IRON).requires(Items.STICK).unlockedBy("has_raw_iron", has(Items.RAW_IRON)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_ORE)
                .requires(Items.RAW_COPPER).requires(Items.RAW_COPPER).requires(Items.STICK).unlockedBy("has_raw_copper", has(Items.RAW_COPPER))
                .save(recipeOutput, MHMod.MOD_ID +":ore_scrap_from_raw_copper");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_FUR)
                .requires(ItemTags.WOOL).requires(Items.STICK).unlockedBy("has_wool", has(ItemTags.WOOL)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_FUR)
                .requires(Items.LEATHER).requires(Items.STICK).unlockedBy("has_leather", has(Items.LEATHER)).save(recipeOutput, MHMod.MOD_ID +":fur_scrap_from_leather");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_HUMBLE)
                .requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
                .requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
                .requires(Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
                .requires(Items.STICK)
                .unlockedBy("has_red_mushroom", has(Items.RED_MUSHROOM)).unlockedBy("has_brown_mushroom", has(Items.BROWN_MUSHROOM))
                .save(recipeOutput, MHMod.MOD_ID +":humble_scrap_from_mushrooms");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_SINISTER, 2)
                .requires(Items.ENDER_PEARL).requires(Items.STICK).unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_SINISTER)
                .requires(Items.SPIDER_EYE).requires(Items.STICK).unlockedBy("has_spider_eye", has(Items.SPIDER_EYE))
                .save(recipeOutput, MHMod.MOD_ID +":sinister_scrap_from_spider_eye");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MHPetItems.SCRAP_DIAMOND)
                .requires(Items.DIAMOND).requires(Items.STICK).unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);

        palicoArmor(recipeOutput, "f_acorn", MHPetArmorItems.F_ACORN_HELM.get(), MHPetItems.SCRAP_WOOD, Items.OAK_SAPLING);
        palicoArmor(recipeOutput, "f_acorn", MHPetArmorItems.F_ACORN_MAIL.get(), MHPetItems.SCRAP_WOOD, Items.OAK_SAPLING);
        palicoArmor(recipeOutput, "f_melynx", MHPetArmorItems.F_MELYNX_HELM.get(), MHPetItems.SCRAP_FUR, Items.GREEN_CARPET);
        palicoArmor(recipeOutput, "f_melynx", MHPetArmorItems.F_MELYNX_MAIL.get(), MHPetItems.SCRAP_FUR, Items.GREEN_CARPET);
        palicoArmor(recipeOutput, "f_kamura", MHPetArmorItems.F_KAMURA_HELM.get(), MHPetItems.SCRAP_FUR, ItemTags.WOOL_CARPETS);
        palicoArmor(recipeOutput, "f_kamura", MHPetArmorItems.F_KAMURA_MAIL.get(), MHPetItems.SCRAP_FUR, ItemTags.WOOL_CARPETS);
        palicoArmor(recipeOutput, "f_duffel", MHPetArmorItems.F_DUFFEL_HELM.get(), MHPetItems.SCRAP_FUR, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_duffel", MHPetArmorItems.F_DUFFEL_MAIL.get(), MHPetItems.SCRAP_FUR, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_bone", MHPetArmorItems.F_BONE_HELM.get(), MHPetItems.SCRAP_BONE, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_bone", MHPetArmorItems.F_BONE_MAIL.get(), MHPetItems.SCRAP_BONE, Ingredient.of(Items.FEATHER));
        palicoArmor(recipeOutput, "f_alloy", MHPetArmorItems.F_ALLOY_HELM.get(), MHPetItems.SCRAP_ORE, Items.IRON_INGOT);
        palicoArmor(recipeOutput, "f_alloy", MHPetArmorItems.F_ALLOY_MAIL.get(), MHPetItems.SCRAP_ORE, Items.IRON_INGOT);
        palicoArmor(recipeOutput, "f_creeper", MHPetArmorItems.F_CREEPER_HELM.get(), MHPetItems.SCRAP_SINISTER, Items.TNT);
        palicoArmor(recipeOutput, "f_creeper", MHPetArmorItems.F_CREEPER_MAIL.get(), MHPetItems.SCRAP_SINISTER, Items.TNT);
        palicoArmor(recipeOutput, "f_red", MHPetArmorItems.F_RED_HELM.get(), MHPetItems.SCRAP_FUR, Items.RED_WOOL);
        palicoArmor(recipeOutput, "f_red", MHPetArmorItems.F_RED_MAIL.get(), MHPetItems.SCRAP_FUR, Items.RED_WOOL);
        palicoArmor(recipeOutput, "f_mosgharl", MHPetArmorItems.F_MOSGHARL_HELM.get(), MHPetItems.SCRAP_HUMBLE, Ingredient.EMPTY);
        palicoArmor(recipeOutput, "f_mosgharl", MHPetArmorItems.F_MOSGHARL_MAIL.get(), MHPetItems.SCRAP_HUMBLE, Items.CARVED_PUMPKIN);
        palicoArmor(recipeOutput, "f_frankie", MHPetArmorItems.F_FRANKIE_HELM.get(), MHPetItems.SCRAP_SINISTER, Items.ROTTEN_FLESH);
        palicoArmor(recipeOutput, "f_frankie", MHPetArmorItems.F_FRANKIE_MAIL.get(), MHPetItems.SCRAP_SINISTER, Items.ROTTEN_FLESH);
        palicoArmor(recipeOutput, "f_ghost", MHPetArmorItems.F_GHOST_HELM.get(), MHPetItems.SCRAP_SINISTER, Items.CARVED_PUMPKIN);
        palicoArmor(recipeOutput, "f_ghost", MHPetArmorItems.F_GHOST_MAIL.get(), MHPetItems.SCRAP_SINISTER, Items.WHITE_BANNER);
        palicoArmor(recipeOutput, "f_diamond", MHPetArmorItems.F_DIAMOND_HELM.get(), MHPetItems.SCRAP_DIAMOND, Items.PHANTOM_MEMBRANE);
        palicoArmor(recipeOutput, "f_diamond", MHPetArmorItems.F_DIAMOND_MAIL.get(), MHPetItems.SCRAP_DIAMOND, Items.DEEPSLATE);

        palicoWeapon(recipeOutput, "f_kamura", MHPetWeaponItems.F_KAMURA_BOKKEN.get(), MHPetItems.SCRAP_WOOD, Ingredient.EMPTY, 0);
        palicoWeapon(recipeOutput, "f_acorn", MHPetWeaponItems.F_BONE_PICK.get(), MHPetItems.SCRAP_BONE, Ingredient.EMPTY, 2);
        palicoWeapon(recipeOutput, "f_melynx", MHPetWeaponItems.F_MELYNX_TOOL.get(), MHPetItems.SCRAP_WOOD, Items.RABBIT_HIDE, 2);
        palicoWeapon(recipeOutput, "f_duffel", MHPetWeaponItems.F_TREKKER_PECKAXE.get(), MHPetItems.SCRAP_ORE, Ingredient.EMPTY, 2);
        palicoWeapon(recipeOutput, "f_bone", MHPetWeaponItems.F_BONE_HAMMER.get(), MHPetItems.SCRAP_BONE, Items.BONE, 1);
        palicoWeapon(recipeOutput, "f_alloy", MHPetWeaponItems.F_IRON_SWORD.get(), MHPetItems.SCRAP_ORE, Ingredient.EMPTY, 0);
        palicoWeapon(recipeOutput, "f_creeper", MHPetWeaponItems.F_CREEPER_CLOBBERER.get(), MHPetItems.SCRAP_SINISTER, Items.TNT, 1);
        palicoWeapon(recipeOutput, "f_alloy", MHPetWeaponItems.F_RED_BASKET.get(), MHPetItems.SCRAP_WOOD, Items.APPLE, 4);
        palicoWeapon(recipeOutput, "f_mosgharl", MHPetWeaponItems.F_MOSGHARL_BROOM.get(), MHPetItems.SCRAP_HUMBLE, Items.HAY_BLOCK, 2);
        palicoWeapon(recipeOutput, "f_ghost", MHPetWeaponItems.F_GHOST_LANTERN.get(), MHPetItems.SCRAP_SINISTER, Items.JACK_O_LANTERN, 5);
        palicoWeapon(recipeOutput, "f_frankie", MHPetWeaponItems.F_FRANKIE_BALL.get(), MHPetItems.SCRAP_SINISTER, Items.IRON_BLOCK, 5);
        palicoWeapon(recipeOutput, "f_diamond", MHPetWeaponItems.F_DIAMOND_SWORD.get(), MHPetItems.SCRAP_DIAMOND, Items.DEEPSLATE, 0);
        palicoWeapon(recipeOutput, "f_valkyrie", MHPetWeaponItems.F_VALKYRIE_SWORD.get(), MHPetItems.SCRAP_DIAMOND, Items.BLUE_ICE, 0);
    }


    protected static void palicoArmor(RecipeOutput recipeOutput, String group, PetHuntingArmorItem<?,?> result,
                                      ItemLike firstMaterial, TagKey<Item> secondMaterialTag) {
        palicoArmor(recipeOutput, group, result, firstMaterial, secondMaterialTag != null ? Ingredient.of(secondMaterialTag) : null);
    }

    protected static void palicoArmor(RecipeOutput recipeOutput, String group, PetHuntingArmorItem<?,?> result,
                                      ItemLike firstMaterial, ItemLike secondMaterial) {
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



    protected static void palicoWeapon(RecipeOutput recipeOutput, String group, PetHuntingWeaponItem<?> result,
                                       ItemLike firstMaterial, @NotNull TagKey<Item> secondMaterialTag, int shape) {
        palicoWeapon(recipeOutput, group, result, firstMaterial, Ingredient.of(secondMaterialTag), shape);
    }

    protected static void palicoWeapon(RecipeOutput recipeOutput, String group, PetHuntingWeaponItem<?> result,
                                       ItemLike firstMaterial, @NotNull ItemLike secondMaterialItem, int shape) {
        palicoWeapon(recipeOutput, group, result, firstMaterial, Ingredient.of(secondMaterialItem), shape);
    }

    protected static void palicoWeapon(RecipeOutput recipeOutput, String group, PetHuntingWeaponItem<?> result,
                                      ItemLike firstMaterial, @Nullable Ingredient secondMaterial, int shape) {

        boolean noSecondMaterial = secondMaterial == null || secondMaterial.isEmpty();

        String pattern1, pattern2, pattern3;
        if (shape > 4 || shape < 0) shape = 0;
        switch (shape) {
            case 0: //sword
                pattern1 = " A "; pattern2 = " B "; pattern3 = "AIA"; break;
            case 1: //hammer
                pattern1 = "ABA"; pattern2 = " A "; pattern3 = " I "; break;
            case 2: //stick
                pattern1 = " B "; pattern2 = " A "; pattern3 = " I "; break;
            case 3: //boomerang
                pattern1 = "ABI"; pattern2 = "  A"; pattern3 = "  A"; break;
            case 4: //shuriken
                pattern1 = " B "; pattern2 = "AIA"; pattern3 = " A "; break;
            case 5: //hook
                pattern1 = " A "; pattern2 = "A A"; pattern3 = "I B"; break;
            default:
                shape = 0; pattern1 = pattern2 = pattern3 = "";
                System.out.println("ERROR: NO PATTERN CHOSEN");
        }

        ShapedRecipeBuilder recipeBuilder = ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).group(group)
                .pattern(pattern1).pattern(pattern2).pattern(pattern3)
                .define('I', Items.STICK).define('A', firstMaterial)
                .unlockedBy("has_" + firstMaterial.asItem().toString(), has(firstMaterial));

        if (noSecondMaterial) recipeBuilder = recipeBuilder.define('B', firstMaterial);
        else recipeBuilder = recipeBuilder.define('B', secondMaterial);

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
                    .save(recipeOutput, MHMod.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }
}
