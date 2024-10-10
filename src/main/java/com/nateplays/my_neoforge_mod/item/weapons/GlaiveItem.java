package com.nateplays.my_neoforge_mod.item.weapons;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlaiveItem extends HuntingWeaponItem {
    static final Logger LOGGER = LoggerFactory.getLogger(GlaiveItem.class);

    public GlaiveItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier) {
        return HuntingWeaponItem.createAttributes(tier, getAttackDamageMultiplier(), -2.7f, 1.0f);
    }

    public static float getAttackDamageMultiplier() {
        return 2.0F;
    }

    public float getUseItemSlowdown(Player player, ItemStack stack) {
        return 0.5F;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
//        if (!level.isClientSide()) {
            if (player.onGround() && player.isSprinting()) {
                player.jumpFromGround();
//                player.fallDistance = -3.0f;
                Vec3 oldDelta = player.getDeltaMovement();
                Vec3 newAddDelta = new Vec3(oldDelta.x * 2.0, oldDelta.y * 0.5, oldDelta.z * 2.0);
                player.addDeltaMovement(newAddDelta);

                // Play a custom sound or particle effect (optional)
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BREEZE_JUMP, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.getCooldowns().addCooldown(stack.getItem(), 20);
                return InteractionResultHolder.success(stack);
//            }
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.onGround()) {
            boolean hasEnoughAttackTicker = true;
            if (attacker instanceof Player player) {
                hasEnoughAttackTicker = player.getAttackStrengthScale(0) >= 0.5f;
                LOGGER.debug("hasTicker!");
            }
            if (hasEnoughAttackTicker) {
                LOGGER.debug("rejump!");
                double jumpForce = 0.6;
                Vec3 deltaMove = attacker.getDeltaMovement();
                attacker.setDeltaMovement(new Vec3(deltaMove.x(), jumpForce, deltaMove.z()));
//                attacker.hasImpulse = true;
                attacker.hurtMarked = true;
                attacker.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, false, false));
//                attacker.resetFallDistance();
//                attacker.jumpFromGround();
                attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.BREEZE_JUMP, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
        super.postHurtEnemy(stack, target, attacker);
    }
}
