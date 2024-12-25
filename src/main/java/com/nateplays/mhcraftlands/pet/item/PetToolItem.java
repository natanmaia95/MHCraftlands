package com.nateplays.mhcraftlands.pet.item;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class PetToolItem<T extends HuntingBuddyEntity> extends Item {
    protected final Class<T> entityClass;

    public PetToolItem(Class<T> entityClass, int durability, Properties properties) {
        super(durability > 0 ? properties.durability(durability) : properties);
        this.entityClass = entityClass;
    }

    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
        boolean isCorrectEntity = false;
        isCorrectEntity = (entity instanceof Player) || (entityClass.isInstance(entity));

        if (!isCorrectEntity) return false;
        return this.isDamageable(stack);
    }

    //    Tool can't break past 1 durability
    @Override
    public boolean isDamageable(ItemStack stack) {
        return super.isDamageable(stack) && (stack.getDamageValue() < stack.getMaxDamage()-1);
    }





    //Test Implementations

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 60;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        stack.hurtAndBreak(1, livingEntity,
                livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

        if (livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 20);
        }

        System.out.println("Finished using pet tool.");
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.GUST,
                    livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(),
            3, livingEntity.getRandom().nextDouble(),0,livingEntity.getRandom().nextDouble(),1.0);
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (this.canUsePetTool(stack, player)) {
            player.startUsingItem(usedHand);
            return InteractionResultHolder.consume(stack);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(),
                    5, livingEntity.getRandom().nextDouble(),0,livingEntity.getRandom().nextDouble(),1.0);
        }

        System.out.println("using tool");
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
//        return super.getUseAnimation(stack);
    }


}
