package com.nateplays.mhcraftlands.pet.goals;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class PalicoMeleeAttackGoal extends MeleeAttackGoal {
    protected HuntingBuddyEntity buddyMob;

    public PalicoMeleeAttackGoal(HuntingBuddyEntity mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
        this.buddyMob = mob;
    }

    public boolean hasMeleeWeapon() {
        return this.mob.isHolding((is) -> {
            if (is.getItem() instanceof PetHuntingWeaponItem<?> weaponItem) {
                return weaponItem.getWeaponRange() == PetHuntingWeaponItem.Range.MELEE;
            }
            return false;
        });
    }

    @Override
    public boolean canUse() {
        return super.canUse() && hasMeleeWeapon() && this.buddyMob.canJoinCombat();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && hasMeleeWeapon() && this.buddyMob.canJoinCombat();
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }
}
