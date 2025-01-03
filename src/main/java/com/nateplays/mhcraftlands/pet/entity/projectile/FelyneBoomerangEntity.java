package com.nateplays.mhcraftlands.pet.entity.projectile;

import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OminousItemSpawner;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class FelyneBoomerangEntity extends AbstractArrow {

    private static final EntityDataAccessor<ItemStack> DATA_WEAPON =
            SynchedEntityData.defineId(FelyneBoomerangEntity.class, EntityDataSerializers.ITEM_STACK);



    public FelyneBoomerangEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public FelyneBoomerangEntity(ItemStack shootingStack, LivingEntity shooter, Level level) {
        super(MHPetEntities.FELYNE_BOOMERANG.get(), shooter, level, shootingStack, shootingStack);
        this.setWeaponItem(shootingStack);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_WEAPON, ItemStack.EMPTY);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getWeaponItem() != ItemStack.EMPTY && this.getWeaponItem() != null) {
            this.entityData.set(DATA_WEAPON, this.getWeaponItem());
            compound.put("weapon", this.getWeaponItem().save(this.registryAccess(), new CompoundTag()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("weapon", 10)) {
            this.setWeaponItem(ItemStack.parse(this.registryAccess(), compound.getCompound("weapon")).orElse(null));
        } else {
            this.setWeaponItem(ItemStack.EMPTY);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    public boolean isGrounded() { return inGround; }

    public float getSpin(float partialTicks) {
        return 20f * ((float)this.tickCount + partialTicks) / 20.0F;
    }

    public void setWeaponItem(ItemStack weapon) {
        this.entityData.set(DATA_WEAPON, weapon);
    }

    @Override
    public ItemStack getWeaponItem() {
        ItemStack retStack = this.entityData.get(DATA_WEAPON);
        if (retStack.isEmpty()) return null;
        return retStack;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
    }

    @Override
    protected boolean tryPickup(Player player) {
        if (this.pickup == Pickup.ALLOWED && this.ownedBy(player)) return true; //won't drop item back to player
        return super.tryPickup(player);
    }
}
