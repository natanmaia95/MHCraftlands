package com.nateplays.mhcraftlands.item.armor;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.attribute.ModAttributes;
import com.nateplays.mhcraftlands.enchantment.ModEnchantmentHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HuntingArmorItem extends ArmorItem {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuntingArmorItem.class);

    public static final ResourceLocation MODIFIER_ID_BASE_DEFENSE =
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID,"base_defense");



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

        } else {
//            LOGGER.debug("No data was found for armor " + stack.getItem().toString());
        }


    }

    //This being added as an attribute component removes EVERY OTHER ATTRIBUTE THE ARMOR WOULD HAVE INCLUDING ARMOR VALUE :D
    public static ItemAttributeModifiers createAttributes(Holder<ArmorMaterial> material, Type type) {
        return ItemAttributeModifiers.builder()
                .add(
                        ModAttributes.DEFENSE,
                        new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("armor." + type.getName()),
                                (double) material.value().getDefense(type) / 10.0, //Defense has 1 decimal digit
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(type.getSlot())
                )
                //add elemental resistances here depending on data loader.
                .build();

    }
}
