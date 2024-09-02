package com.nateplays.my_neoforge_mod.enchantment;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModEnchantmentHelper {

    public static ResourceLocation getRL(String id) {
        return ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, id);
    }

    @Nullable
    public static Holder<Enchantment> getEnchantmentFromLocation(ResourceLocation location, Level level) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return getEnchantmentFromLocationAndLookup(location, registryLookup);
    }

    @Nullable public static Holder<Enchantment> getEnchantmentFromLocationAndLookup(ResourceLocation location, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return lookup.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, location)).getDelegate();
    }

    public static int getTotalEnchantmentLevel(LivingEntity livingEntity, Holder<Enchantment> enchantment) {
        int totalLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack itemStack = livingEntity.getItemBySlot(slot);
            if (enchantment.value().matchingSlot(slot)) {
                int level = itemStack.getEnchantmentLevel(enchantment);
                totalLevel += level;
            }
        }
        if (enchantment.is(ModEnchantments.TAG_SKILL_ENCHANTMENTS)) {
            totalLevel = Mth.floor(Math.min(totalLevel, enchantment.value().getMaxLevel()));
        }
        return totalLevel;
    }

}
