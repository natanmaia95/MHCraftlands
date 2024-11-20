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



    public static final Tier F_KAMURA_BOKKEN = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(ModItems.SCRAP_WOOD));
    public static final Tier F_BONE_PICK = makePetWeaponTier(
            64, 1.0f, () -> Ingredient.of(ModItems.SCRAP_BONE));
    public static final Tier F_BONE_HAMMER = makePetWeaponTier(
            128, 1.5f, () -> Ingredient.of(ModItems.SCRAP_BONE));
    public static final Tier F_IRON_SWORD = makePetWeaponTier(
            128, 1.5f, () -> Ingredient.of(ModItems.SCRAP_ORE));
    public static final Tier F_RED_BASKET = makePetWeaponTier(
            128, 1.7f, () -> Ingredient.of(ModItems.SCRAP_WOOD));
    public static final Tier F_MOSGHARL_BROOM = makePetWeaponTier(
            128, 1.7f, () -> Ingredient.of(ModItems.SCRAP_HUMBLE));
    public static final Tier F_GHOST_LANTERN = makePetWeaponTier(
            256, 2.0f, () -> Ingredient.of(ModItems.SCRAP_SINISTER));
    public static final Tier F_FRANKIE_BALL = makePetWeaponTier(
            256, 2.0f, () -> Ingredient.of(ModItems.SCRAP_SINISTER));



    public static Tier makePetWeaponTier(int uses, float attackBonus, Supplier<Ingredient> repairIngredient) {
       return new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, uses, 1, attackBonus, 0, repairIngredient);
    }
}
