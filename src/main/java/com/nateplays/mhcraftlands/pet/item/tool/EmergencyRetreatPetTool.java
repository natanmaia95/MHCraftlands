package com.nateplays.mhcraftlands.pet.item.tool;

import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class EmergencyRetreatPetTool<T extends PalicoEntity> extends PetToolItem<T> {

    public EmergencyRetreatPetTool(Class<T> entityClass, int durability, Properties properties) {
        super(entityClass, durability, properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 10;
    }

    @Override
    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player) return false;
        // Only use at 40% health or below
        if (entity.getHealth() > 0.4f*entity.getMaxHealth()) return false;
        return super.canUsePetTool(stack, entity);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        stack.hurtAndBreak(1, livingEntity,
                livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

        if (livingEntity instanceof PalicoEntity palicoEntity) {
            palicoEntity.setKOed(true);
            palicoEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20*10, 1));
        }

        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {}

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.BLOCK;}
}
