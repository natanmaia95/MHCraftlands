package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.effect.MHPetMobEffects;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SumoStompPetTool<T extends PalicoEntity> extends PetToolItem<T> {

    public SumoStompPetTool(Class<T> entityClass, int durability, Properties properties) {
        super(entityClass, durability, properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 50;
    }

    @Override
    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player) return true;
        return this.entityClass.isInstance(entity);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        pushNearbyEntities(level, livingEntity);
        this.hurtUsedItem(1, livingEntity);
        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (remainingUseDuration == 10) {
            livingEntity.jumpFromGround();
        }

        if (remainingUseDuration == 50 || remainingUseDuration == 30) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                        SoundEvents.MACE_SMASH_GROUND, SoundSource.NEUTRAL, 1.0F, 1.0F);
                BlockState onTopBlockState = serverLevel.getBlockState(livingEntity.getOnPos().below());
                serverLevel.sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, onTopBlockState),
                        livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 10, 0.5, 0.1, 0.5,0.35);
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.BLOCK;}


    public void pushNearbyEntities(Level level, LivingEntity livingEntity) {
        double knockbackPower = livingEntity.getAttributeValue(Attributes.ATTACK_KNOCKBACK) * 0.4; //USUALLY DOES NOTHING

        knockbackPower += 1.5;
        final double finalKnockback = knockbackPower;
        Vec3 origin = livingEntity.position();

        double radius = 16.0;
        AABB area = livingEntity.getBoundingBox().inflate(radius);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.getEntitiesOfClass(LivingEntity.class, area).forEach((victim) -> {
                if (!victim.isPushable()) return;
                if (victim == livingEntity) return;
                if (HuntingBuddyEntity.ALLIED_TO_HUNTERS_SELECTOR.test(victim)) return;
                Vec3 direction = victim.position().subtract(origin).normalize().scale(finalKnockback);
                Vec3 knockback = new Vec3(direction.x, 0.6, direction.z);
                double knockbackResistance = livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                knockback.scale((1 - knockbackResistance));
                victim.push(knockback);
                victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20*10, 5, false, false));
//                livingEntity.doHurtTarget(victim);
            });

            BlockState onTopBlockState = serverLevel.getBlockState(livingEntity.getOnPos().below());
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, onTopBlockState),
//                    ParticleTypes.GUST,
                    origin.x, origin.y + 0.1, origin.z, 50, 1.5, 0.1, 1.5,0.4);
            serverLevel.playSound(
                    null, origin.x, origin.y, origin.z, SoundEvents.MACE_SMASH_GROUND, SoundSource.NEUTRAL, 1.0F, 1.0F
            );
        }
    }
}
