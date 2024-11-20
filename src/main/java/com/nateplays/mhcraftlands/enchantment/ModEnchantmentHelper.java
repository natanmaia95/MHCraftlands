package com.nateplays.mhcraftlands.enchantment;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ModEnchantmentHelper {

    public static ResourceLocation getRL(String id) {
        return ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, id);
    }

    public static ResourceKey<Enchantment> getKey(ResourceLocation location) {
        return ResourceKey.create(Registries.ENCHANTMENT, location);
    }

    @Nullable
    public static Holder<Enchantment> getEnchantmentFromLocation(ResourceKey<Enchantment> key, Level level) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return getEnchantmentFromLocationAndLookup(key, registryLookup);
    }

    @Nullable public static Holder<Enchantment> getEnchantmentFromLocationAndLookup(ResourceKey<Enchantment> key, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return lookup.getOrThrow(key).getDelegate();
    }

    public static int getTotalEnchantmentLevel(LivingEntity livingEntity, Holder<Enchantment> enchantment) {
        int totalLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot == EquipmentSlot.OFFHAND) continue;
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
