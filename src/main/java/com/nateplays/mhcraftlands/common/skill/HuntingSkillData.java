package com.nateplays.mhcraftlands.common.skill;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import java.util.ArrayList;
import java.util.List;

public class HuntingSkillData {
    private final ResourceLocation armorItem;
    private final List<EnchantmentData> enchantments;

    public HuntingSkillData(ResourceLocation armorItem, List<EnchantmentData> enchantments) {
        this.armorItem = armorItem;
        this.enchantments = enchantments;
    }

    public ResourceLocation getArmorItem() {
        return armorItem;
    }

    public List<EnchantmentData> getEnchantments() {
        return enchantments;
    }

    public static HuntingSkillData fromJson(ResourceLocation armorId, JsonObject json) {
        List<EnchantmentData> enchantments = new ArrayList<>();

        JsonArray enchantmentsArray = GsonHelper.getAsJsonArray(json, "enchantments");
        for (JsonElement element : enchantmentsArray) {
            JsonObject enchantmentJson = element.getAsJsonObject();
            ResourceLocation enchantment = ResourceLocation.parse(GsonHelper.getAsString(enchantmentJson, "enchantment"));
            int level = GsonHelper.getAsInt(enchantmentJson, "level");

            enchantments.add(new EnchantmentData(enchantment, level));
        }

        return new HuntingSkillData(armorId, enchantments);
    }

    public static class EnchantmentData {
        private final ResourceLocation enchantment;
        private final int level;

        public EnchantmentData(ResourceLocation enchantment, int level) {
            this.enchantment = enchantment;
            this.level = level;
        }

        public ResourceLocation getEnchantment() {
            return enchantment;
        }

        public int getLevel() {
            return level;
        }
    }
}
