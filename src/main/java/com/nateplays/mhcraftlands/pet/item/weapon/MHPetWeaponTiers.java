package com.nateplays.mhcraftlands.pet.item.weapon;

import com.nateplays.mhcraftlands.item.ModItems;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.function.Supplier;

public class MHPetWeaponTiers {




    public static final Tier F_KAMURA_BOKKEN = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(MHPetItems.SCRAP_WOOD));
    public static final Tier F_BONE_PICK = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(MHPetItems.SCRAP_BONE));
    public static final Tier F_MELYNX_TOOL = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(MHPetItems.SCRAP_FUR));
    public static final Tier F_TREKKER_PECKAXE = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(MHPetItems.SCRAP_ORE));
    public static final Tier F_BONE_HAMMER = makePetWeaponTier(
            128, 1.5f, () -> Ingredient.of(MHPetItems.SCRAP_BONE));
    public static final Tier F_IRON_SWORD = makePetWeaponTier(
            128, 1.5f, () -> Ingredient.of(MHPetItems.SCRAP_ORE));
    public static final Tier F_CREEPER_CLOBBERER = makePetWeaponTier(
            128, 1.5f, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER));
    public static final Tier F_RED_BASKET = makePetWeaponTier(
            128, 1.7f, () -> Ingredient.of(MHPetItems.SCRAP_WOOD));
    public static final Tier F_MOSGHARL_BROOM = makePetWeaponTier(
            128, 1.7f, () -> Ingredient.of(MHPetItems.SCRAP_HUMBLE));
    public static final Tier F_GHOST_LANTERN = makePetWeaponTier(
            256, 2.0f, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER));
    public static final Tier F_FRANKIE_BALL = makePetWeaponTier(
            256, 2.0f, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER));
    public static final Tier F_DIAMOND_SWORD = makePetWeaponTier(
            512, 3.0f, () -> Ingredient.of(MHPetItems.SCRAP_DIAMOND));
    public static final Tier F_VALKYRIE_SWORD = makePetWeaponTier(
            512, 2.0f, () -> Ingredient.of(MHPetItems.SCRAP_DIAMOND));



    public static Tier makePetWeaponTier(int uses, float attackBonus, Supplier<Ingredient> repairIngredient) {
        return new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, uses, 1, attackBonus, 0, repairIngredient);
    }
}
