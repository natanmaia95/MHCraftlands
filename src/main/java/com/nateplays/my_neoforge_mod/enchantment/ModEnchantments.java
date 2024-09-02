package com.nateplays.my_neoforge_mod.enchantment;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static final TagKey<Enchantment> TAG_SKILL_ENCHANTMENTS = TagKey.create(
            Registries.ENCHANTMENT,
//            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "skill_enchantments"
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "skill_enchantments"
            ));


}
