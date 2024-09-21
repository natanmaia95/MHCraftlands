package com.nateplays.my_neoforge_mod.item.custom;

import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.component.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            ModBlocks.MACHALITE_ORE.get(), ModBlocks.MACHALITE_BLOCK.get()
    );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        BlockPos clickedPos = context.getClickedPos();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            // only change things on server
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(clickedPos, CHISEL_MAP.get(clickedBlock).defaultBlockState());

                if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                    context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                }

                level.playSound(null, clickedPos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
            }

            //particles can run on server / client
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(),
                    0.0, 0.0, 0.0);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("Shift is being pressed!"));
            MutableComponent newComponent = Component.literal("[COOL STUFF]");
            ComponentUtils.mergeStyles(newComponent, Style.EMPTY.withBold(true).withObfuscated(true).withColor(ChatFormatting.DARK_BLUE));
            tooltipComponents.add(newComponent);
        } else {
            tooltipComponents.add(Component.literal("Press Shift to see cool stuff!"));
        }


        if (stack.get(ModDataComponents.COORDINATES) != null) {
            tooltipComponents.add(Component.literal("Last block changed at: " + stack.get(ModDataComponents.COORDINATES)));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
