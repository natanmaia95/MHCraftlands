package com.nateplays.mhcraftlands.pet.item.custom;

import com.nateplays.mhcraftlands.common.component.ModDataComponents;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PetTrainingBookItem extends Item {

    public PetTrainingBookItem(Item.Properties properties) {
        super(properties);
    }


    public static InteractionResult interactWithBuddy(ItemStack stack, HuntingBuddyEntity buddyEntity, Player player) {
        int buddyExp = buddyEntity.getExp();
        boolean wasBookUsed = false;


        if (stack.get(ModDataComponents.BUDDY_EXP) != null) {
            int bookExp = stack.get(ModDataComponents.BUDDY_EXP);
            buddyEntity.gainExp(bookExp);
            wasBookUsed = true;
        } else {
            stack.set(ModDataComponents.BUDDY_EXP, buddyExp);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        }

        for (int i = 0; i < 5; i++) {
            buddyEntity.level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                    buddyEntity.getRandomX(1.0), buddyEntity.getY()+1.0, buddyEntity.getRandomZ(1.0),
                    0.0, 0.0, 0.0);
        }

        if (wasBookUsed) {
            stack.shrink(1);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.mhcraftlands.training_book.desc"));

        if (stack.get(ModDataComponents.BUDDY_EXP) != null) {
            MutableComponent newComponent = Component.literal(stack.get(ModDataComponents.BUDDY_EXP) + " EXP");
            ComponentUtils.mergeStyles(newComponent, Style.EMPTY.withBold(true).withColor(ChatFormatting.GREEN));
            tooltipComponents.add(newComponent);
        }
//         else {
//            tooltipComponents.add(Component.literal("--- EXP"));
//        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
