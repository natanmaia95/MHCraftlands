package com.nateplays.my_neoforge_mod.entity.ai;

import com.nateplays.my_neoforge_mod.entity.custom.MosswineEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MosswineAttackGoal extends MeleeAttackGoal {

    //TODO: find a better way to handle attack timers
    //FIXME: a

    private final MosswineEntity entity;
    private int ATTACK_ANIMATION_LENGTH = 25;
    private int attackDelay = 3; //time between animation start and hit
    private int ticksUntilNextAttack = 21; //time between hit and end of animation
    private boolean shouldCountUntilNextAttack = false;

    public MosswineAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
        entity = ((MosswineEntity) mob);
    }

    protected void performAttack(LivingEntity target) {
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
//        super.checkAndPerformAttack(target);
        if (isEnemyWithinAttackDistance(target)) {
            shouldCountUntilNextAttack = true;

            if (isTimeToStartAttackAnimation()) entity.setAttacking(true);

            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                performAttack(target);
            }

        } else {
            resetAttackCooldown();
            shouldCountUntilNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity enemy) {
        return this.mob.isWithinMeleeAttackRange(enemy);
//        return this.mob.getAttackBoundingBox().inflate(0.5).intersects(entity.getHitbox());
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(ATTACK_ANIMATION_LENGTH);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= this.attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 3;
        ticksUntilNextAttack = ATTACK_ANIMATION_LENGTH - attackDelay;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountUntilNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        super.stop();
        entity.setAttacking(false);
    }
}
