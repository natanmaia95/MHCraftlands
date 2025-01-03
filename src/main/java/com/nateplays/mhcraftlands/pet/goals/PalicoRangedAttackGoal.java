package com.nateplays.mhcraftlands.pet.goals;

import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

public class PalicoRangedAttackGoal extends Goal {
    protected final PalicoEntity mob;
    protected final double speedModifier;
    protected int attackIntervalMin;
    protected final float attackRadiusSqr;
    protected int attackTime;
    protected int seeTime;
    protected boolean strafingClockwise;
    protected boolean strafingBackwards;
    protected int strafingTime;

    public PalicoRangedAttackGoal(PalicoEntity mob, double speedModifier, int attackIntervalMin, float attackRadius) {
        this.attackTime = -1;
        this.strafingTime = -1;
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean hasRangedWeapon() {
        return this.mob.isHolding((is) -> {
            if (is.getItem() instanceof PetHuntingWeaponItem<?> weaponItem) {
                return weaponItem.getWeaponRange() == PetHuntingWeaponItem.Range.RANGED;
            }
            return false;
        });
    }

    public void setMinAttackInterval(int attackCooldown) {
        this.attackIntervalMin = attackCooldown;
    }

    public boolean canUse() {
        return this.mob.getTarget() != null && this.hasRangedWeapon();
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone()) && this.hasRangedWeapon();
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            boolean hasLineOfSight = this.mob.getSensing().hasLineOfSight(livingentity);
            boolean flag1 = this.seeTime > 0;
            if (hasLineOfSight != flag1) {
                this.seeTime = 0;
            }

            if (hasLineOfSight) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.mob.getRandom().nextFloat() < 0.3) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.mob.getRandom().nextFloat() < 0.3) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                Entity var7 = this.mob.getControlledVehicle();
                if (var7 instanceof Mob) {
                    Mob mob = (Mob)var7;
                    mob.lookAt(livingentity, 30.0F, 30.0F);
                }

                this.mob.lookAt(livingentity, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            }

            this.attackTime--;
            if (this.attackTime <= 0 && this.seeTime >= -60) {
                ((RangedAttackMob)this.mob).performRangedAttack(livingentity, 1.0f);
                this.attackTime = this.attackIntervalMin;
            }
        }

    }
}
