package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.entity.interfaces.ILevelableEntity;
import com.nateplays.mhcraftlands.pet.gui.PetToolContainer;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface PetToolUser {
    String TAG_TOOLS = "Buddy Tools";
    String TAG_TOOL_PREFERENCE = "Tool Preference";

    PetToolContainer getToolsContainer();
    EntityDataAccessor<Integer> getToolPreferenceAccessor();

    default List<ItemStack> getAllUsableTools() {
        List<ItemStack> possibleTools = new ArrayList<>();
        for (ItemStack stack : getToolsContainer().getItems()) {
            boolean shouldAddItem = false;
            if (stack.getItem() instanceof PetToolItem<?> toolItem) shouldAddItem = toolItem.canUsePetTool(stack, (LivingEntity) this);

            if (shouldAddItem) possibleTools.add(stack);
        }
        return possibleTools;
    }

    default int getMaxToolPoints() {
        int level = ((ILevelableEntity) this).getExpLevel();
        return 6 + (level-1) * 2;
    }

    default int getUsedToolPoints() {
        int sum = 0;
        for (ItemStack stack : this.getToolsContainer().getItems()) {
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof PetToolItem<?> item) {
                sum += item.getPointCost((LivingEntity) this);
            }
        }
        return sum;
    }

    default int getRemainingToolPoints() {
        return getMaxToolPoints() - getUsedToolPoints();
    }

    default Component getToolPreferenceName() {
        return Component.translatable(PetToolPreference.getTranslationKey(this.getToolPreference()));
    }

    default PetToolPreference getToolPreference() {
        // "& 255" because horse does it too? Kaupenjoe said so.
        return PetToolPreference.byId(((LivingEntity) this).getEntityData().get(getToolPreferenceAccessor()) & 255);
    }

    default void setToolPreference(PetToolPreference pref) {
        ((LivingEntity) this).getEntityData().set(getToolPreferenceAccessor(), pref.getId() & 255);
    }

    default void buildBuddyToolsSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(getToolPreferenceAccessor(), PetToolPreference.NONE.getId());
    }

    default void readBuddyToolsFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        if (tag.contains(TAG_TOOLS, 9)) {
            this.getToolsContainer().fromTag(tag.getList(TAG_TOOLS, 10), levelRegistry);
        }
        setToolPreference(PetToolPreference.byId(tag.getInt(TAG_TOOL_PREFERENCE)));
    }

    default void writeBuddyToolsToTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        tag.put(TAG_TOOLS, this.getToolsContainer().createTag(levelRegistry));
        tag.putInt(TAG_TOOL_PREFERENCE, getToolPreference().getId());
    }
}
