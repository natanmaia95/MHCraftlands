package com.nateplays.my_neoforge_mod.entity.pets.goals;

import com.nateplays.my_neoforge_mod.entity.pets.HuntingBuddyEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class HuntingBuddyHurtByTargetGoal extends HurtByTargetGoal {
    public HuntingBuddyHurtByTargetGoal(PathfinderMob mob, Class<?>... toIgnoreDamage) {
        super(mob, toIgnoreDamage);
    }

    /*
    Wild hunting buddies will attack their assailants.
    Tamed hunting buddies will not attack any creature allied to hunters.
     */
    @Override
    public boolean canUse() {
        LivingEntity possibleTarget = this.mob.getLastHurtByMob();
        HuntingBuddyEntity buddyEntity = (HuntingBuddyEntity) this.mob;

        if (buddyEntity.isTame()) {
            if (HuntingBuddyEntity.ALLIED_TO_HUNTERS_SELECTOR.test(possibleTarget)) return false;
        }

        return super.canUse();
    }

    @Override
    protected void alertOther(Mob mob, LivingEntity target) {
        HuntingBuddyEntity thisBuddyEntity = (HuntingBuddyEntity) this.mob;
        HuntingBuddyEntity otherBuddyEntity = (HuntingBuddyEntity) mob;

        // if tame, only alert from the same owner
        if (thisBuddyEntity.isTame()) {
            if (!otherBuddyEntity.isTame()) return; // don't recruit wild buddies to help (for now)
            if (thisBuddyEntity.getOwnerUUID() != otherBuddyEntity.getOwnerUUID()) return; // don't recruit from other owners
        }
        // if not tame, only alert wild ones as well
        else if (otherBuddyEntity.isTame()) return; // don't

        super.alertOther(mob, target);
    }
}
