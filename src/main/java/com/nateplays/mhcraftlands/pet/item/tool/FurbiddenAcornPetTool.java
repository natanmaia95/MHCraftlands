package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.effect.MHPetMobEffects;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class FurbiddenAcornPetTool<T extends PalicoEntity> extends PetToolItem<T> {

    public FurbiddenAcornPetTool(Class<T> entityClass, Properties properties) {
        super(entityClass, List.of(), 0, 0, properties);
        this.basePointCost = 16;
        this.preferences = List.of(PetToolPreference.HEALING);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }

    @Override
    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player) return true;

        if (entity.hasEffect(MHPetMobEffects.FURBIDDEN_ACORN_AURA)) return false;
        return this.entityClass.isInstance(entity) && (entity.getHealth() < entity.getMaxHealth()*0.8f);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.removeEffect(MHPetMobEffects.FURBIDDEN_ACORN_AURA);
        livingEntity.addEffect(new MobEffectInstance(MHPetMobEffects.FURBIDDEN_ACORN_AURA, 20*60));
        stack.shrink(1);
        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
//        if ((remainingUseDuration+1) % 20 == 0) {
//            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 2));
//            if (level instanceof ServerLevel serverLevel) {
////                this.tauntNearbyEnemies(serverLevel, livingEntity, stack, remainingUseDuration);
//            }
//        }
//
//        if ((remainingUseDuration+1) % 40 == 0) {
//            stack.hurtAndBreak(1, livingEntity,
//                    livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
//        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.EAT;}

}
