package com.nateplays.mhcraftlands.pet.goals;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class HuntingBuddyUseToolGoal extends Goal {

    private final int MAX_USING_TOOL_TICKS = 100;

    protected final HuntingBuddyEntity mob;
    protected int cooldownTicks = 40;
    protected int remainingUseTicks = 0;
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
            this.cooldownTicks = 40 + mob.getRandom().nextInt(0, 40);

            boolean hasToolToUse = this.tryUseTool();
//            if (!hasToolToUse) System.out.println("Buddy has no valid tool to use");
//            else System.out.println("Buddy will use tool: " + currentTool.getDisplayName());
            return hasToolToUse;
        }
    }

    @Override
    public boolean canContinueToUse() {
        boolean isUsingItem = mob.isUsingItem();
        boolean freeToUseTool = isMobFreeToUseTool();
        return isUsingItem && freeToUseTool;
    }

    @Override
    public void start() {
        this.remainingUseTicks = currentTool.getUseDuration(mob);
        this.mob.setItemInHand(InteractionHand.OFF_HAND, this.currentTool);
        mob.startUsingItem(InteractionHand.OFF_HAND);
    }

    @Override
    public void stop() {
        this.remainingUseTicks = 0;

        mob.stopUsingItem();
        this.currentTool = ItemStack.EMPTY;
        this.clearOffHand();
    }

    @Override
    public void tick() {
        //debug for using item
//        this.mob.getJumpControl().jump();
        this.remainingUseTicks--;
    }

    public boolean isMobFreeToUseTool() {
        return !mob.isKOed() && !mob.isInSittingPose();
    }

    public boolean tryUseTool() {
//        this.currentTool = ItemStack.EMPTY;

        SimpleContainer toolsContainer = mob.getToolsContainer();
        if (toolsContainer.isEmpty()) return false;

        List<ItemStack> possibleTools = new ArrayList<>();
        for (ItemStack stack : toolsContainer.getItems()) {
            boolean shouldAddItem = false;
            if (stack.getItem() instanceof PetToolItem<?> toolItem) shouldAddItem = toolItem.canUsePetTool(stack, mob);

            if (shouldAddItem) possibleTools.add(stack);
        }
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
}
