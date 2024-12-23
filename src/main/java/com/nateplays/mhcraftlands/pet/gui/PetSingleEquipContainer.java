package com.nateplays.mhcraftlands.pet.gui;

import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.ticks.ContainerSingleItem;

public class PetSingleEquipContainer implements ContainerSingleItem {

    public HuntingBuddyEntity entity;
    public EquipmentSlot slotType;

    public PetSingleEquipContainer(HuntingBuddyEntity entity, EquipmentSlot slotType) {
        this.entity = entity;
        this.slotType = slotType;
    }

    @Override
    public ItemStack getTheItem() {
        return entity.getItemBySlot(slotType);
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        entity.setItemSlot(slotType, itemStack);
    }

    @Override
    public void setChanged() {}

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        Item item = stack.getItem();
        if (slotType.isArmor()) {
            if (item instanceof PetHuntingArmorItem armor) {
                return armor.canEquip(stack, slotType, entity);
            }
        } else if (slotType == EquipmentSlot.MAINHAND) {
            if (item instanceof PetHuntingWeaponItem weapon) {
                return weapon.canEquip(stack, slotType, entity);
            }
        }
        return false;
    }
}
