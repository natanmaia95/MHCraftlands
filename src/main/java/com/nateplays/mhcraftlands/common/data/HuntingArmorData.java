package com.nateplays.mhcraftlands.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.common.skill.HuntingSkillData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.*;

public class HuntingArmorData {
    private final ResourceLocation armorItem;
    public final double defense;
    public final List<HuntingArmorData.SkillDataEntry> skills;
    public final ElementResistanceData resistances;



    public HuntingArmorData(ResourceLocation armorItemIn, double defenseIn, List<HuntingArmorData.SkillDataEntry> skillsIn, ElementResistanceData resistancesIn) {
        this.armorItem = armorItemIn;
        this.defense = defenseIn;
        this.skills = skillsIn;
        this.resistances = resistancesIn;
    }


    public static HuntingArmorData fromJson(ResourceLocation armorId, JsonObject json) {
        //HuntingArmorData data = new HuntingArmorData(armorId);

        double defense = GsonHelper.getAsDouble(json, "defense");

        List<HuntingArmorData.SkillDataEntry> skills = new ArrayList<>();
        for (JsonElement element : GsonHelper.getAsJsonArray(json, "skills")) {
            JsonObject skillJson = element.getAsJsonObject();
            ResourceLocation skill = ResourceLocation.parse(GsonHelper.getAsString(skillJson, "skill"));
            int level = GsonHelper.getAsInt(skillJson, "level");

            skills.add(new SkillDataEntry(skill, level));
        }

        ElementResistanceData resistances = new ElementResistanceData();
        for (JsonElement element : GsonHelper.getAsJsonArray(json, "resistances")) {
            JsonObject skillJson = element.getAsJsonObject();
            ResourceLocation attributeId = ResourceLocation.parse(GsonHelper.getAsString(skillJson, "name"));
            double value = GsonHelper.getAsDouble(skillJson, "value");
            System.out.printf("resistance id?: %s -> %f%n", attributeId.getPath(), value);

//            Optional<Holder.Reference<Attribute>> attrOpt = ModAttributes.ATTRIBUTES.getRegistry().get().getHolder(attributeId);
            Holder<Attribute> attrHolder = ModAttributes.fromName(attributeId.getPath());
//            if (attrOpt.isEmpty()) continue;
//            Holder<Attribute> attrHolder = attrOpt.get();
            System.out.printf("resistance: %s%n", attrHolder.getRegisteredName());

            resistances.addElementModifier(attrHolder, value);
        }

        return new HuntingArmorData(armorId, defense, skills, resistances);
    }

    //TODO: make datagen for this?
//    public JsonObject toJson() {
//        JsonObject obj = new JsonObject();
//
//        obj.add("defense", GsonHelper.);
//    }

    public static class ElementResistanceData {
        protected Map<Holder<Attribute>, Double> elements;

        public ElementResistanceData() {
            this.elements = new HashMap<>();
        }

        public Set<Holder<Attribute>> keySet() {
            return elements.keySet();
        }

        public void addElementModifier(Holder<Attribute> attributeHolder, double amount) {
            elements.put(attributeHolder, amount);
        }

        public double getElementModifier(Holder<Attribute> attributeHolder) {
            return elements.getOrDefault(attributeHolder, 0.0);
        }
    }

    public static class SkillDataEntry {
        private final ResourceLocation skillEnchantment;
        private final int level;

        public SkillDataEntry(ResourceLocation skillEnchantment, int level) {
            this.skillEnchantment = skillEnchantment;
            this.level = level;
        }

        public ResourceLocation getSkillEnchantment() {
            return skillEnchantment;
        }

        public int getLevel() {
            return level;
        }
    }
}
