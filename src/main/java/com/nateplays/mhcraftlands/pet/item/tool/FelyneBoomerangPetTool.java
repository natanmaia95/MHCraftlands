package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.entity.PetToolPreference;
import com.nateplays.mhcraftlands.pet.entity.projectile.FelyneBoomerangEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class FelyneBoomerangPetTool extends PetToolItem<PalicoEntity> {
    public FelyneBoomerangPetTool(List<PetToolPreference> preferences, int basePointCost, int durability, Properties properties) {
        super(PalicoEntity.class, preferences, basePointCost, durability, properties);
    }

    public double getDamageMultiplier() { return 2.0; }

    @Override
    public UseBehavior getUseBehavior() { return UseBehavior.STAY; }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) { return 20; }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
//        super.onUseTick(level, livingEntity, stack, remainingUseDuration);

        if (!level.isClientSide) {
//            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(0.0, 1.0, 0.0));
            lookAtTarget(livingEntity);
        }

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            lookAtTarget(livingEntity);
            ItemStack weaponStack = livingEntity.getMainHandItem();
            //for testing, exit if no valid weapon
            if (weaponStack == null || weaponStack.isEmpty()) return stack;

            FelyneBoomerangEntity boomerang = new FelyneBoomerangEntity(livingEntity.getMainHandItem(), livingEntity, level);
            boomerang.visualOverrideStack = stack; //TODO: add override and damageMult to entityData
            boomerang.damageMultiplier = this.getDamageMultiplier();
            boomerang.maxDistance = 12.0;
//            boomerang.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0f, 1.0f, 0.0f);
            boomerang.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0f, 1.0f, 0.0f);
            level.addFreshEntity(boomerang);

            this.hurtUsedItem(1, livingEntity);
        }
        level.playSound(null, livingEntity.getOnPos(), SoundEvents.WIND_CHARGE_THROW, SoundSource.NEUTRAL);
        return stack;
    }

    public void lookAtTarget(LivingEntity livingEntity) {
        if (livingEntity instanceof HuntingBuddyEntity mob) {
            mob.getNavigation().stop();
            LivingEntity target = mob.getTarget();
            if (target != null)  {
//                mob.getLookControl().setLookAt(target, 30f, 30f);
                mob.lookAt(target, 30f, 30f);
            }
        }
    }
}
