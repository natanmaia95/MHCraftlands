package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PetToolPreference;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import com.nateplays.mhcraftlands.pet.sound.MHPetSounds;
import com.nateplays.mhcraftlands.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class HornPetTool<T extends HuntingBuddyEntity> extends PetToolItem<T> {

    public HornPetTool(Class<T> entityClass, List<PetToolPreference> preferences, int basePointCost, int durability, Properties properties) {
        super(entityClass, preferences, basePointCost, durability, properties);
    }

    public double getEffectRadius(ItemStack stack, LivingEntity entity) {
        return 32.0;
    }

    //Override this!
    public void applyActualEffectToEntity(LivingEntity livingEntity, LivingEntity user) {
        livingEntity.heal(4.0f);
    }

    public void applyEffectToEntity(LivingEntity livingEntity, LivingEntity user) {
        applyActualEffectToEntity(livingEntity, user);

        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ(), 10,
                    0.5, 0.0, 0.5, 1.0);

            serverLevel.playSound(null, livingEntity.blockPosition(),
                    ModSounds.ITEM_HEAL.get(),
                    SoundSource.NEUTRAL);
        }
    }

    public boolean isValidTarget(LivingEntity entity, LivingEntity user) {
        return entity instanceof HuntingBuddyEntity || entity instanceof Player;
    }

    protected void applyEffectAroundUser(ItemStack stack, Level level, LivingEntity userEntity) {
//        if (level instanceof ServerLevel serverLevel) {
            AABB boundingBox = userEntity.getBoundingBox().inflate(getEffectRadius(stack, userEntity));
            List<LivingEntity> nearbyTargets = level.getEntitiesOfClass(LivingEntity.class, boundingBox, (target) -> this.isValidTarget(target, userEntity));
            nearbyTargets.forEach((target) -> this.applyEffectToEntity(target, userEntity));
//        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 80; //four seconds
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
//        return super.finishUsingItem(stack, level, livingEntity);
        stack.hurtAndBreak(1, livingEntity,
                livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.GUST,
                    livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(),
                    10, livingEntity.getRandom().nextDouble(),0,livingEntity.getRandom().nextDouble(),1.0);
        }

        applyEffectAroundUser(stack, level, livingEntity);

        if (livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 20);
        }
        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        System.out.println("remaining: %d".formatted(remainingUseDuration));
        if (level instanceof ServerLevel serverLevel) {
            if (remainingUseDuration % 10 == 0) {
                serverLevel.sendParticles(ParticleTypes.NOTE,
                        livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ(), 5,
                        1.0, 0.0, 1.0,
                        1.0);
            }

            if ((remainingUseDuration+1) % 40 == 0) {
                serverLevel.playSound(null, livingEntity.blockPosition(),
                        this.getHornTootSound(),
                        SoundSource.NEUTRAL);
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }

    public SoundEvent getHornTootSound() {
        return ModSounds.ITEM_HORN_1.get();
    }
}
