package com.nateplays.my_neoforge_mod.item.armor;

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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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

    public HuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type,
                properties.component(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
                        .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
        );
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        LOGGER.debug("crafted hunting armor.");
        applyInitialEnchantments(stack, level);
    }

    public static ItemStack makeItemStackWithLookup(HuntingArmorItem item, HolderLookup.RegistryLookup<Enchantment> registryLookup) {
        ItemStack itemStack = new ItemStack(item, 1);
        applyInitialEnchantmentsWithLookup(itemStack, registryLookup);
        return itemStack;
    }

    public static void applyInitialEnchantments(ItemStack stack, Level level) {
        ResourceLocation armorTypeId = stack.getItemHolder().getKey().location();
//        LOGGER.debug("armorid: " + armorTypeId.toString());
        ArmorSkillData data = ArmorSkillDataLoader.getArmorEnchantmentData(armorTypeId);

        if (data != null) {
//            LOGGER.debug("hasdata");
            List<Holder<Enchantment>> holdersList = new ArrayList<>();
            level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).holders().forEach(holdersList::add);

//            LOGGER.debug(holdersList.toString());

            for (ArmorSkillData.EnchantmentData enchantmentData : data.getEnchantments()) {
                Optional<Holder<Enchantment>> holderOptional = holdersList.stream()
                        .filter(h -> h.unwrapKey().isPresent() && h.unwrapKey().get().location().equals(enchantmentData.getEnchantment()))
                        .findFirst();
                if (holderOptional.isPresent()) {
//                    LOGGER.debug("found thing");
                    stack.enchant(holderOptional.get(), enchantmentData.getLevel());
                }
            }
        } else {
//            LOGGER.debug("not hasdata");
        }
    }

    //copy-pasted because fuck it
    public static void applyInitialEnchantmentsWithLookup (ItemStack stack, HolderLookup.RegistryLookup<Enchantment> registryLookup) {
        ResourceLocation armorTypeId = stack.getItemHolder().getKey().location();
//        LOGGER.debug("armorid: " + armorTypeId.toString());
        ArmorSkillData data = ArmorSkillDataLoader.getArmorEnchantmentData(armorTypeId);

        if (data != null) {
//            LOGGER.debug("hasdata");
            List<Holder<Enchantment>> holdersList = new ArrayList<>();
            registryLookup.listElements().forEach(enchantmentReference -> {
                Holder<Enchantment> h = enchantmentReference.getDelegate();
                holdersList.add(h);
            });

//            LOGGER.debug(holdersList.toString());

            for (ArmorSkillData.EnchantmentData enchantmentData : data.getEnchantments()) {
                Optional<Holder<Enchantment>> holderOptional = holdersList.stream()
                        .filter(h -> h.unwrapKey().isPresent() && h.unwrapKey().get().location().equals(enchantmentData.getEnchantment()))
                        .findFirst();
                if (holderOptional.isPresent()) {
//                    LOGGER.debug("found thing");
                    stack.enchant(holderOptional.get(), enchantmentData.getLevel());
                }
            }
        } else {
//            LOGGER.debug("not hasdata");
        }
    }


}
