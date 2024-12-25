package com.nateplays.mhcraftlands.pet.goals;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.pathfinder.Path;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class HuntingBuddyUseToolGoal extends Goal {

    private final int MAX_USING_TOOL_TICKS = 100;

    protected final HuntingBuddyEntity mob;
    protected int cooldownTicks = 40;
    protected int remainingUseTicks = 0;
    protected int useDelayTicks = 0;
    protected boolean hasAvoidPath = false;
    private ItemStack currentTool = ItemStack.EMPTY;

    public HuntingBuddyUseToolGoal(HuntingBuddyEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.TARGET, Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (!mob.isInCombat()) return false;
        if (!isMobFreeToUseTool()) return false;
        this.clearOffHand(); //TODO: replace this

        if (this.cooldownTicks > 0) {
            --this.cooldownTicks;
            return false;
        } else {
            this.cooldownTicks = 40 + mob.getRandom().nextInt(0, 40); //TODO: change cooldown logic

            boolean hasToolToUse = this.tryUseTool();
//            if (!hasToolToUse) System.out.println("Buddy has no valid tool to use");
//            else System.out.println("Buddy will use tool: " + currentTool.getDisplayName());
            return hasToolToUse;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (useDelayTicks > 0) return true;

        boolean isUsingItem = mob.isUsingItem();
        boolean freeToUseTool = isMobFreeToUseTool();
        return isUsingItem && freeToUseTool;
    }

    @Override
    public void start() {
        this.hasAvoidPath = false;
        this.useDelayTicks = 20;
        this.remainingUseTicks = currentTool.getUseDuration(mob);
        this.hasAvoidPath = tryPathfindAvoidTarget();
    }

    @Override
    public void stop() {
        this.hasAvoidPath = false;
        this.useDelayTicks = 0;
        this.remainingUseTicks = 0;

        mob.stopUsingItem();
        this.currentTool = ItemStack.EMPTY;
        this.clearOffHand();
    }

    @Override
    public void tick() {
        if (useDelayTicks > 0) {
            useDelayTicks--;
            if (useDelayTicks == 0) {
                actuallyStart();
                return;
            }
            if (hasAvoidPath) {
                if (mob.getNavigation().isDone()) actuallyStart();
            } else hasAvoidPath = tryPathfindAvoidTarget();
            return;
        }

        //debug for using item
//        this.mob.getJumpControl().jump();
        this.remainingUseTicks--;
    }

    public void actuallyStart() {
        this.useDelayTicks = 0;
        this.remainingUseTicks = currentTool.getUseDuration(mob);
        this.mob.setItemInHand(InteractionHand.OFF_HAND, this.currentTool);
        mob.startUsingItem(InteractionHand.OFF_HAND);
    }

    public boolean isMobFreeToUseTool() {
        return !mob.isKOed() && !mob.isInSittingPose();
    }

    public boolean tryUseTool() {
//        this.currentTool = ItemStack.EMPTY;

        List<ItemStack> possibleTools = mob.getAllUsableTools();
        if (possibleTools.isEmpty()) return false;

        this.currentTool = possibleTools.get(mob.getRandom().nextInt(possibleTools.size()));
//        System.out.println("chosen!" + this.currentTool.toString());
        //move to offhand
        this.mob.setItemInHand(InteractionHand.OFF_HAND, this.currentTool);
        return true;
    }

    private void clearOffHand() {
        if (mob.getItemInHand(InteractionHand.OFF_HAND) != ItemStack.EMPTY) {
            mob.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        }
    }

    private boolean tryPathfindAvoidTarget() {
        LivingEntity target = mob.getTarget();
        Path avoidPath;

        if (target == null) return false;

        Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 12, 7, target.position());
        // if no valid position was found
        if (vec3 == null) {
            return false;
        }

        // try create path
        avoidPath = mob.getNavigation().createPath(vec3.x, vec3.y, vec3.z, 0);
        if (avoidPath == null) return false;

        mob.getNavigation().stop();
        mob.getNavigation().moveTo(avoidPath, 3.0);
        return true;
    }
}
