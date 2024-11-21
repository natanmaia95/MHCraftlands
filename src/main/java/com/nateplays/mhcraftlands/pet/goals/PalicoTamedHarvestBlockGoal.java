package com.nateplays.mhcraftlands.pet.goals;

import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
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

    private static final int GIVE_UP_TICKS = 200; //one minute
    private static final int STAY_TICKS = 120;
//    private static final int INTERVAL_TICKS = 40; //10 secs
    private static final int HARVESTING_TICKS = 40;
    protected int harvestingTicks;
    private int maxStayTicks;
    private int maxIntervalTicks;

    public PalicoTamedHarvestBlockGoal(PathfinderMob mob, TagKey<Block> targetBlocksTag, double speedModifier, int searchRange, int verticalRange) {
        this(mob, targetBlocksTag, speedModifier, searchRange, verticalRange, 40);
    }

    public PalicoTamedHarvestBlockGoal(PathfinderMob mob, TagKey<Block> targetBlocksTag, double speedModifier, int searchRange, int verticalRange, int intervalTicks) {
        super(mob, speedModifier, searchRange, verticalRange);
        this.palicoEntity = (PalicoEntity) mob;
        this.targetBlocksTag = targetBlocksTag;
        this.harvestingTicks = 0;
        this.maxIntervalTicks = intervalTicks;
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
        BlockState state = levelReader.getBlockState(blockPos);
        return state.is(targetBlocksTag);
    }



    @Override
    public double acceptedDistance() {
        return 1.6;
    }

    @Override
    public boolean canUse() {
        return palicoEntity.isTame() && super.canUse();
    }

    public boolean canContinueToUse() {
        return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= GIVE_UP_TICKS && this.isValidTarget(this.mob.level(), this.blockPos);
    }

    @Override
    protected BlockPos getMoveToTarget() {
        return this.blockPos;
    }

    @Override
    protected int nextStartTick(PathfinderMob creature) {
        return reducedTickDelay(this.maxIntervalTicks);// + creature.getRandom().nextInt(this.maxIntervalTicks));
    }

    @Override
    public void start() {
        super.start();
        this.maxStayTicks = Math.round((float) STAY_TICKS * (0.5f + this.mob.getRandom().nextFloat())); //super omega random
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
