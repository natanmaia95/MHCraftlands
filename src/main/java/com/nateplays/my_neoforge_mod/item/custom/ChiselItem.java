package com.nateplays.my_neoforge_mod.item.custom;

import com.nateplays.my_neoforge_mod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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
            if (!level.isClientSide()) { // only change things on server
                level.setBlockAndUpdate(clickedPos, CHISEL_MAP.get(clickedBlock).defaultBlockState());

                if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                    context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                }

                level.playSound(null, clickedPos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }

            level.addParticle(ParticleTypes.CHERRY_LEAVES, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(),
                    0.0, 0.0, 0.0);
        }
        return InteractionResult.SUCCESS;
    }
}
