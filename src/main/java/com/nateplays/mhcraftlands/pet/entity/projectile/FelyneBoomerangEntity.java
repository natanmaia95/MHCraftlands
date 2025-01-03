package com.nateplays.mhcraftlands.pet.entity.projectile;

import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Predicate;

public class FelyneBoomerangEntity extends AbstractArrow {

    private static final EntityDataAccessor<ItemStack> DATA_WEAPON =
            SynchedEntityData.defineId(FelyneBoomerangEntity.class, EntityDataSerializers.ITEM_STACK);

    private boolean shouldReturn = false;
//    protected double flySpeed = 1.0;
    protected double returnSpeed = 1.0;
    protected double maxDistance = 12.0;
    protected int maxPierces = 5;

    protected int currentPierces = 0;
    protected int pierceTimer = 0;


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
    protected boolean tryPickup(Player player) {
        if (this.pickup == Pickup.ALLOWED && this.ownedBy(player)) return true; //won't drop item back to player
        return super.tryPickup(player);
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return super.canHitEntity(target);
    }

    @Override
    protected @Nullable EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return getEntityHitResultNoClip(
                this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(0.0), this::canHitEntity, 0.5f
        );
    }

    public EntityHitResult getEntityHitResultNoClip(AABB boundingBox, Predicate<Entity> filter, float targetInflation) {
        double d0 = Double.MAX_VALUE;
        Entity bestEntity = null;
        Iterator var10 = this.level().getEntities(this, boundingBox, this::canHitEntity).iterator();

        while(var10.hasNext()) {
            Entity currentEntity = (Entity)var10.next();
            double d1 = this.position().subtract(currentEntity.position()).length();
            if (d1 < d0) {
                bestEntity = currentEntity;
                d0 = d1;
            }
        }

        return bestEntity == null ? null : new EntityHitResult(bestEntity);
    }

    @Override
    public void tick() {
        super.tick();

        Entity owner = getOwner();
        if (this.shouldReturn) {
            if (!canReturnToOwner()) {
                this.discard();
            } else { //can return to owner
                this.setNoPhysics(true); //no physics means not hit tests. TODO: rethink this for damage on the way back.
                Vec3 directionToOwner = owner.getEyePosition().subtract(this.position());


                this.setDeltaMovement(this.getDeltaMovement().scale(0.90).add(directionToOwner.normalize().scale(this.returnSpeed*0.1)));
                if (this.getDeltaMovement().length() > this.returnSpeed) {
                    this.setDeltaMovement(this.getDeltaMovement().normalize().scale(this.returnSpeed));
                }
            }
        } else {
            if (this.pierceTimer > 0) this.pierceTimer--;
            else this.setNoPhysics(false);


            if (canReturnToOwner()) {
                Vec3 directionToOwner = owner.getEyePosition().subtract(this.position());
                if (directionToOwner.length() > this.maxDistance) {
                    this.shouldReturn = true;
                }
            }
        }

        boolean noPhys = this.isNoPhysics();
        Vec3 movement = this.getDeltaMovement();

    }

    @Override
    protected void applyGravity() {}

    private boolean canReturnToOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.shouldReturn = true;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        Entity owner = this.getOwner();
        DamageSource damageSource = this.damageSources().arrow(this, owner == null ? this : owner);

        double damageAmount = 0.0;
        if (owner instanceof LivingEntity livingEntity) {
            damageAmount += livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        }
        if (this.getWeaponItem() != null) {
            damageAmount += this.getWeaponItem().getItem().getAttackDamageBonus(target, (float) damageAmount, damageSource);
        }

        //TODO: try getPierceLevel()
        this.currentPierces += 1;
        if (this.currentPierces >= this.maxPierces) {
            this.shouldReturn = true;
        } else {
            this.pierceTimer = 2;
            this.setNoPhysics(true);
        }

        if (target.hurt(damageSource, (float)damageAmount)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (this.level() instanceof ServerLevel serverLevel) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, target, damageSource, this.getWeaponItem());
            }

            if (target instanceof LivingEntity livingentity) {
                this.doKnockback(livingentity, damageSource);
                this.doPostHurtEffects(livingentity);
                livingentity.hurtTime = 1;
                livingentity.hurtDuration = 1;
                livingentity.invulnerableTime = 1;
            }
        }

        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
        if (this.getWeaponItem() != null && this.getOwner() instanceof LivingEntity) {
            this.getWeaponItem().hurtAndBreak(1, (LivingEntity)this.getOwner(), EquipmentSlot.MAINHAND);
        }
    }
}
