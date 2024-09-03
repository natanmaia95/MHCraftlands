package com.nateplays.my_neoforge_mod.enchantment.event;

import com.mojang.logging.LogUtils;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.enchantment.ModEnchantmentHelper;
import com.nateplays.my_neoforge_mod.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import org.slf4j.Logger;

import java.util.function.Supplier;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class SkillAttributeEventHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level();
        if (!level.isClientSide) {
            updateQuickEatingModifier(livingEntity);
            updateMuckResistanceModifier(livingEntity);
        }
    }



    public static void updateQuickEatingModifier(LivingEntity livingEntity) {
        ResourceKey<Enchantment> enchantmentKey = ModEnchantments.QUICK_EATING;
        AttributeInstance attributeInstance = livingEntity.getAttribute(ModAttributes.EATING_SPEED);
        if (attributeInstance == null) return;
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "skill."+enchantmentKey.registry().getPath());
        Holder<Enchantment> enchantment = ModEnchantmentHelper.getEnchantmentFromLocation(enchantmentKey, livingEntity.level());
        int enchantLevel = ModEnchantmentHelper.getTotalEnchantmentLevel(livingEntity, enchantment);
        if (enchantLevel > 0) {
            AttributeModifier modifier = new AttributeModifier(modifierId, enchantLevel * 1.0d, AttributeModifier.Operation.ADD_VALUE);
            attributeInstance.addOrUpdateTransientModifier(modifier);
        } else {
            attributeInstance.removeModifier(modifierId);
        }
    }

    public static void updateMuckResistanceModifier(LivingEntity livingEntity) {
        ResourceKey<Enchantment> enchantmentKey = ModEnchantments.MUCK_RESISTANCE;
        AttributeInstance attributeInstance = livingEntity.getAttribute(Attributes.MOVEMENT_EFFICIENCY);
        if (attributeInstance == null) return;
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "skill."+enchantmentKey.registry().getPath());
        Holder<Enchantment> enchantment = ModEnchantmentHelper.getEnchantmentFromLocation(enchantmentKey, livingEntity.level());
        int enchantLevel = ModEnchantmentHelper.getTotalEnchantmentLevel(livingEntity, enchantment);
        if (enchantLevel > 0) {
            AttributeModifier modifier = new AttributeModifier(modifierId, enchantLevel * 0.5d, AttributeModifier.Operation.ADD_VALUE);
            attributeInstance.addOrUpdateTransientModifier(modifier);
        } else {
            attributeInstance.removeModifier(modifierId);
        }
    }


}
