package com.nateplays.mhcraftlands.common.client.event;

import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.bus.api.Event;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;

public class LivingRenderHandItemEvent extends Event implements ICancellableEvent {
    private final LivingEntity entity;
    private final ItemStack originalItemStack;
    private ItemStack modifiedItemStack;
    private final HumanoidArm arm;

    public LivingRenderHandItemEvent(LivingEntity entity, ItemStack originalItemStack, HumanoidArm arm) {
        this.entity = entity;
        this.originalItemStack = originalItemStack;
        this.modifiedItemStack = originalItemStack;
        this.arm = arm;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public ItemStack getOriginalItemStack() {
        return originalItemStack;
    }

    public ItemStack getModifiedItemStack() {
        return modifiedItemStack;
    }

    public void setModifiedItemStack(ItemStack modifiedItemStack) {
        this.modifiedItemStack = modifiedItemStack;
    }

    public HumanoidArm getArm() {
        return arm;
    }
}
