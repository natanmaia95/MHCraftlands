package com.nateplays.mhcraftlands.common.skill;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static final TagKey<Enchantment> TAG_SKILL_ENCHANTMENTS = TagKey.create(
            Registries.ENCHANTMENT,
//            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "skill_enchantments"
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "skill_enchantments"
            ));

    public static final ResourceKey<Enchantment> QUICK_EATING = key("quick_eating");
    public static final ResourceKey<Enchantment> MUCK_RESISTANCE = key("muck_resistance");
    public static final ResourceKey<Enchantment> BOMBARDIER = key("muck_resistance");

    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name));
    }
}
