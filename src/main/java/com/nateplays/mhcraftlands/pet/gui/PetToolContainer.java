package com.nateplays.mhcraftlands.pet.gui;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class PetToolContainer extends SimpleContainer {

    protected HuntingBuddyEntity owner;

    public PetToolContainer(HuntingBuddyEntity owner, int size) {
        super(size);
        this.owner = owner;
    }

    // this function isn't called anywhere outside of InventoryCarrier so we don't care
    @Override
    public boolean canAddItem(ItemStack stack) { return false; }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (stack.getItem() instanceof PetToolItem<?> toolItem) {
            if (!toolItem.canEquipPetTool(stack, this.owner)) return false;
            return (this.hasPointsToPlaceItem(slot, stack));
        }
        return false;
    }

    public boolean hasPointsToPlaceItem(int slot, ItemStack stack) {
        int oldPoints = 0;
        ItemStack oldStack = getItem(slot);
        if (oldStack.getItem() instanceof PetToolItem<?> oldToolItem) {
            oldPoints = oldToolItem.getPointCost(this.owner);
        }

        int newPoints = 0;
        if (stack.getItem() instanceof PetToolItem<?> newToolItem) { //always true
            newPoints = newToolItem.getPointCost(this.owner);
        }

        int remainingPoints = owner.getRemainingToolPoints() + oldPoints;
        return remainingPoints >= newPoints;
    }
}
