package com.nateplays.my_neoforge_mod.block.custom;

import com.nateplays.my_neoforge_mod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
//        return super.useWithoutItem(state, level, pos, player, hitResult);
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // yes, item entities can step on blocks
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == ModItems.MALACHITE_CHUNK.get()) {
                itemEntity.setItem(new ItemStack(ModItems.MALACHITE_INGOT.get(), itemEntity.getItem().getCount()));
            } else {
                entity.setDeltaMovement(entity.getDeltaMovement().add(0, 0.5, 0));
            }
        }

        super.stepOn(level, pos, state, entity);
    }
}
