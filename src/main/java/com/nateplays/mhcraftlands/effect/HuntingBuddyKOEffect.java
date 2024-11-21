package com.nateplays.mhcraftlands.effect;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/* This effect should always be applied for a constant duration, defined in this class.
 *
 *
 *
 *
 *
 */
public class HuntingBuddyKOEffect extends MobEffect {

//    public static final int DURATION = 2400; //two minutes
    public static final int DURATION = 1200; //one minute

    protected HuntingBuddyKOEffect(MobEffectCategory category) {
        super(category, 0);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!(livingEntity instanceof HuntingBuddyEntity)) return false;

        float maxHealth = livingEntity.getMaxHealth();
        if (livingEntity.getHealth() < maxHealth) {
            livingEntity.heal(maxHealth / 20);
            return true;
        } else {
            return false; //this triggers removal
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        //heal 1/20th max health every 3 seconds
        return duration % (DURATION / 20) == 0;
    }
}
