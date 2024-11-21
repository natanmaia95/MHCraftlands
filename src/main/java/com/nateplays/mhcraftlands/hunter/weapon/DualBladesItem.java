package com.nateplays.mhcraftlands.hunter.weapon;

import com.nateplays.mhcraftlands.common.component.ModDataComponents;
import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DualBladesItem extends HuntingWeaponItem {
    static final Logger LOGGER = LoggerFactory.getLogger(DualBladesItem.class);

    //TODO: change this to be stored in the weapon
    public static boolean lastSwingWithMainHand = false;



    public DualBladesItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier) {
        return HuntingWeaponItem.createAttributes(tier, 1f, -1.3f, -0.5f);
    }

    @Override
    public float getAttackDamageMultiplier() {
        return 1.2F;
    }


//    @Override
//    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
//        return ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
//    }



    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player player) {
//            RandomSource randomSource = RandomSource.create();
//            if (randomSource.nextFloat() > 0.5) {
//                forceServerSwing(player, InteractionHand.OFF_HAND);
//                return true;
//            }
            int handSwing = stack.getOrDefault(ModDataComponents.HAND_SWING, 1);
            if (handSwing == 1) {
                stack.set(ModDataComponents.HAND_SWING, 2);
                forceServerSwing(player, InteractionHand.OFF_HAND);
                return true;
            }
        }
        stack.set(ModDataComponents.HAND_SWING, 1);
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHurtEnemy(stack, target, attacker);
        target.hurtTime = 0;
        target.invulnerableTime = 0;
        target.setDeltaMovement(target.getDeltaMovement().x()*0.5, target.getDeltaMovement().y(), target.getDeltaMovement().z()*0.5);
    }


}
