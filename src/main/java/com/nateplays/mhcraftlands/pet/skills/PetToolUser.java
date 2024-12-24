package com.nateplays.mhcraftlands.pet.skills;

import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface PetToolUser {
    String TAG_TOOLS = "Buddy Tools";

    SimpleContainer getToolsContainer();

    public default List<ItemStack> getAllUsableTools() {
        List<ItemStack> possibleTools = new ArrayList<>();
        for (ItemStack stack : getToolsContainer().getItems()) {
            boolean shouldAddItem = false;
            if (stack.getItem() instanceof PetToolItem<?> toolItem) shouldAddItem = toolItem.canUsePetTool(stack, (LivingEntity) this);

            if (shouldAddItem) possibleTools.add(stack);
        }
        return possibleTools;
    }

    default void readBuddyToolsFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        if (tag.contains(TAG_TOOLS, 9)) {
            this.getToolsContainer().fromTag(tag.getList(TAG_TOOLS, 10), levelRegistry);
        }
    }

    default void writeBuddyToolsToTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        tag.put(TAG_TOOLS, this.getToolsContainer().createTag(levelRegistry));
    }
}
