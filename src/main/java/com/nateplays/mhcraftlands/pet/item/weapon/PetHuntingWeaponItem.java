package com.nateplays.mhcraftlands.pet.item.weapon;

import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.projectile.FelyneBoomerangEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;

public class PetHuntingWeaponItem<E extends HuntingBuddyEntity> extends HuntingWeaponItem {
    public static enum Range {
        MELEE, RANGED
    }

    private final Class<E> entityClass;
    private final Range weaponRange;

    public PetHuntingWeaponItem(Tier tier, Range range, Properties properties, Class<E> entityClassIn) {
        super(tier, properties);
        this.entityClass = entityClassIn;
        this.weaponRange = range;
    }

    public static Tool createToolProperties() {
        return HuntingWeaponItem.createToolProperties();
    }

    public static ItemAttributeModifiers createAttributes(Tier tier) {
        return HuntingWeaponItem.createAttributes(tier, 1f, -1.0f, 0.0f);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot equipmentSlot, LivingEntity entity) {
        if (equipmentSlot != EquipmentSlot.MAINHAND) return false;
        if (!this.entityClass.isInstance(entity)) return false;
        return true;
    }

    public Range getWeaponRange() { return this.weaponRange; }


    //Debug purposes, for player to test
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack weaponStack = player.getItemInHand(usedHand);
        if (this.getWeaponRange() == Range.RANGED) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL,
                    0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide()) {
                FelyneBoomerangEntity boomerang = new FelyneBoomerangEntity(weaponStack, player, level);

                boomerang.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.0f, 0.0f);
                level.addFreshEntity(boomerang);
                System.out.println(boomerang.getWeaponItem().toString());
            }

            player.awardStat(Stats.ITEM_USED.get(this));
//            weaponStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(usedHand));
            return InteractionResultHolder.success(weaponStack);
        }

        return super.use(level, player, usedHand);
    }
}
