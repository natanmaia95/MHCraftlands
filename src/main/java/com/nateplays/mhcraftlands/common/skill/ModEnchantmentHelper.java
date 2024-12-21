package com.nateplays.mhcraftlands.common.skill;

import com.nateplays.mhcraftlands.MHMod;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static ItemEnchantments getAllPlayerSkills(Player player) {
        Map<Holder<Enchantment>, Integer> map = new HashMap<>();
        ItemEnchantments.Mutable skills = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

//        ItemEnchantments.Mutable enchantsMutable = new ItemEnchantments.Mutable(
//                EnchantmentHelper.getEnchantmentsForCrafting(player.getItemInHand(InteractionHand.MAIN_HAND)).
//        );

        for (ItemStack stack : player.getAllSlots()) {
            if (stack.isEmpty()) continue;

            ItemEnchantments stackEnchants = EnchantmentHelper.getEnchantmentsForCrafting(stack);
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : stackEnchants.entrySet()) {
                Holder<Enchantment> currentEnchant = entry.getKey();
                int level = stackEnchants.getLevel(currentEnchant);

                if (!currentEnchant.is(ModEnchantments.TAG_SKILL_ENCHANTMENTS)) continue;

//                skills.upgrade(currentEnchant, stackEnchants.getLevel(currentEnchant));
                skills.set(currentEnchant, skills.getLevel(currentEnchant) + level);
            }
        }

        return skills.toImmutable();
    }
}
