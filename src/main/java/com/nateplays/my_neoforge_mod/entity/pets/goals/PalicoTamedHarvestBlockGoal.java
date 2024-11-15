package com.nateplays.my_neoforge_mod.entity.pets.goals;

import com.nateplays.my_neoforge_mod.entity.pets.HuntingBuddyEntity;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PalicoTamedHarvestBlockGoal extends MoveToBlockGoal {
    public PalicoEntity palicoEntity;
    public TagKey<Block> targetBlocksTag;

//    private static final int GIVE_UP_TICKS = 120;
//    private static final int STAY_TICKS = 120;
//    private static final int INTERVAL_TICKS = 120;
    private static final int HARVESTING_TICKS = 40;
    protected int harvestingTicks;

    public PalicoTamedHarvestBlockGoal(PathfinderMob mob, TagKey<Block> targetBlocksTag, double speedModifier, int searchRange, int verticalRange) {
        super(mob, speedModifier, searchRange, verticalRange);
        this.palicoEntity = (PalicoEntity) mob;
        this.targetBlocksTag = targetBlocksTag;
        this.harvestingTicks = 0;
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
        BlockState state = levelReader.getBlockState(blockPos);
        return state.is(targetBlocksTag);
    }

    @Override
    public double acceptedDistance() {
        return 2.1;
    }

    @Override
    public boolean canUse() {
        return palicoEntity.isTame() && super.canUse();
    }

    @Override
    public void start() {
        super.start();
        this.harvestingTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.harvestingTicks = 0;
        this.mob.level().destroyBlockProgress(this.mob.getId(), this.blockPos, -1);
    }

    @Override
    public void tick() {
        if (this.harvestingTicks > 0) {
            this.tickHarvesting();
        } else {
            super.tick();
            if (this.isReachedTarget() && this.blockPos != BlockPos.ZERO) this.startHarvesting();
        }
    }

    public void startHarvesting() {
        this.harvestingTicks = HARVESTING_TICKS;
        this.mob.level().destroyBlockProgress(this.mob.getId(), this.blockPos, 0);
    }

    public void tickHarvesting() {
        this.harvestingTicks--;
        int progress = Math.round( ((float) (HARVESTING_TICKS - this.harvestingTicks) / (float) HARVESTING_TICKS) * 10.0f);
        this.mob.level().destroyBlockProgress(this.mob.getId(), this.blockPos, progress);

        if (this.harvestingTicks <= 0) this.finishHarvesting();
    }

    public void finishHarvesting() {
        //remove
        this.mob.level().destroyBlockProgress(this.mob.getId(), this.blockPos, -1);
        //
        Level level = this.mob.level();
        BlockState blockState = level.getBlockState(this.blockPos);
        BlockEntity possibleBlockEntity = level.getBlockEntity(this.blockPos);

        List<ItemStack> drops = Block.getDrops(blockState, (ServerLevel) level, this.blockPos, possibleBlockEntity, this.mob, this.mob.getMainHandItem());
        List<ItemStack> remainingDrops = new ArrayList<>();
        drops.forEach((stack) -> {
            ItemStack resultStack = PalicoTamedHarvestBlockGoal.this.palicoEntity.getInventory().addItem(stack);
            if (resultStack != ItemStack.EMPTY) remainingDrops.add(resultStack);
        });
        remainingDrops.forEach(stack -> Block.popResource(level, this.blockPos, stack));

        boolean brokeBlock = level.destroyBlock(this.blockPos, false, this.mob);
        if (brokeBlock) this.blockPos = BlockPos.ZERO;

        Player player = (Player) this.palicoEntity.getOwner();
        if (player != null) { //will be null if player is offline
            player.sendSystemMessage(Component.literal("I just gathered from [").append(blockState.getBlock().getName()).append(Component.literal("]!")));
        }
    }
}
