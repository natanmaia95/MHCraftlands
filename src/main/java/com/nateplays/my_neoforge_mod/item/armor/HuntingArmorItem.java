package com.nateplays.my_neoforge_mod.item.armor;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.enchantment.ModEnchantmentHelper;
import com.nateplays.my_neoforge_mod.item.ModToolTiers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HuntingArmorItem extends ArmorItem {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuntingArmorItem.class);

    public static final ResourceLocation MODIFIER_ID_BASE_DEFENSE =
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID,"base_defense");



    public HuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type,
                properties
                        .component(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
                        .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                        .attributes(createAttributes(material, type)) //this also removes Armor and Toughness attributes.
        );
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        LOGGER.debug("crafted hunting armor." + stack.toString());
        applyInitialEnchantments(stack, level);
    }

    public static ItemStack makeItemStackWithLookup(HuntingArmorItem item, HolderLookup.RegistryLookup<Enchantment> registryLookup) {
        ItemStack itemStack = new ItemStack(item, 1);
        applyInitialEnchantmentsWithLookup(itemStack, registryLookup);
        return itemStack;
    }

    public static void applyInitialEnchantments(ItemStack stack, Level level) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        applyInitialEnchantmentsWithLookup(stack, registryLookup);
    }

    //copy-pasted because fuck it
    public static void applyInitialEnchantmentsWithLookup (ItemStack stack, HolderLookup.RegistryLookup<Enchantment> registryLookup) {
        ResourceLocation armorId = stack.getItemHolder().getKey().location();
//        LOGGER.debug("armorid: " + armorTypeId.toString());
        ArmorSkillData data = ArmorSkillDataLoader.getArmorEnchantmentData(armorId);

        if (data != null) {
//            LOGGER.debug("hasdata");

            for (ArmorSkillData.EnchantmentData enchantmentData : data.getEnchantments()) {
                Holder<Enchantment> holder = ModEnchantmentHelper.getEnchantmentFromLocationAndLookup(
                        ModEnchantmentHelper.getKey(enchantmentData.getEnchantment()), registryLookup);
                if (holder != null) {
                    stack.enchant(holder, enchantmentData.getLevel());
                }
            }

        } else LOGGER.debug("No data was found for armor " + stack.getItemHolder().getKey().registry().getPath());
    }

    //This being added as an attribute component removes EVERY OTHER ATTRIBUTE THE ARMOR WOULD HAVE
    public static ItemAttributeModifiers createAttributes(Holder<ArmorMaterial> material, Type type) {
        return ItemAttributeModifiers.builder()
                .add(
                        ModAttributes.DEFENSE,
                        new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("armor." + type.getName()),
                                material.value().getDefense(type),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(type.getSlot())
                )
                //add elemental resistances here depending on data loader.
                .build();

    }
}
