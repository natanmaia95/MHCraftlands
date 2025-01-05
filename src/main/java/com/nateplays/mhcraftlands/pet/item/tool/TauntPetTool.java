package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.entity.PetToolPreference;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TauntPetTool<T extends HuntingBuddyEntity> extends PetToolItem<T> {

    public TauntPetTool(Class<T> entityClass, List<PetToolPreference> preferences, int basePointCost, int durability, Properties properties) {
        super(entityClass, preferences, basePointCost, durability, properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 600;
    }

    @Override
    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
//        if (entity instanceof Player) return false;
        // Only use above 20% health
        if (entity.getHealth() < 0.2f*entity.getMaxHealth()) return false;
        return super.canUsePetTool(stack, entity);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2));
        if ((remainingUseDuration-1) % 40 == 0) {
            if (level instanceof ServerLevel serverLevel) {
                this.tauntNearbyEnemies(serverLevel, livingEntity, stack, remainingUseDuration);
            }
        }

        if ((remainingUseDuration+1) % 40 == 0) {
            stack.hurtAndBreak(1, livingEntity,
                    livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.BLOCK;}

    public void tauntNearbyEnemies(ServerLevel serverLevel, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        int count = 0;
        AABB boundingBox = livingEntity.getBoundingBox().inflate(32.0);
        List<Monster> nearbyMobs = serverLevel.getEntitiesOfClass(Monster.class, boundingBox);
        for (Monster mob : nearbyMobs) {
            if (mob.getRandom().nextFloat() < 0.5f) {
                if (mob.getTarget() != livingEntity) {
                    count += 1;
                    mob.setTarget(livingEntity);
                    serverLevel.sendParticles(ParticleTypes.GUST,
                            mob.getX(), mob.getEyeY(), mob.getZ(),
                            3, mob.getRandom().nextDouble(),0,mob.getRandom().nextDouble(),1.0);
                }
            }
            if (count > 2) break;
        }
    }
}
