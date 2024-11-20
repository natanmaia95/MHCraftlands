package com.nateplays.mhcraftlands.item;

import com.nateplays.mhcraftlands.block.ModBlocks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

public class NulberryItem extends ItemNameBlockItem {

    public NulberryItem(Item.Properties properties) {
        super(ModBlocks.NULBERRY_BUSH.get(),
                properties.food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0.1F).build()));
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using the Item before the action is complete.
     */
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        if (!level.isClientSide()) {
            entityLiving.removeEffectsCuredBy(EffectCures.MILK);
        }
        return super.finishUsingItem(stack, level, entityLiving);
    }
}
