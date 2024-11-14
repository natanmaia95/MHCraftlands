package com.nateplays.my_neoforge_mod.entity.pets.gui;

import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.gui.ModMenuTypes;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
import com.nateplays.my_neoforge_mod.item.weapons.PetHuntingWeaponItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;

public class PalicoInventoryMenu extends AbstractContainerMenu {

    public final PalicoEntity palicoEntity;
//    private final Container armorContainer;
    private final Container pouchInventory;

    // Client menu constructor
    public PalicoInventoryMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) { // optional FriendlyByteBuf parameter if reading data from server
        this(containerId, playerInventory, decodeBuffer(playerInventory.player.level(), buf));
    }

    // Server menu constructor //TODO: change additional param
    public PalicoInventoryMenu(int containerId, Inventory playerInventory, final PalicoEntity palicoIn) {
        super(ModMenuTypes.PALICO_INVENTORY.get(), containerId);
        this.palicoEntity = palicoIn;
        this.pouchInventory = palicoEntity.getInventory();

        // Add inventory slots here, for example:
        // addSlot(new Slot(playerInventory, 0, 10, 10));

        int startX = 8;
        int startY = 33;
        int slotSizePlus2 = 18;

        // Armor Slots?
        this.addSlot(new Slot(this.palicoEntity.helmArmorAccess, 0, startX, startY) {
            @Override
            public boolean mayPlace(ItemStack stack) { return palicoEntity.helmArmorAccess.canPlaceItem(0, stack); }
        });
        this.addSlot(new Slot(this.palicoEntity.mailArmorAccess, 0, startX, startY + slotSizePlus2){
            @Override
            public boolean mayPlace(ItemStack stack) { return palicoEntity.mailArmorAccess.canPlaceItem(0, stack); }
        });
        this.addSlot(new Slot(this.palicoEntity.weaponAccess, 0, startX, startY + slotSizePlus2*2){
            @Override
            public boolean mayPlace(ItemStack stack) { return palicoEntity.weaponAccess.canPlaceItem(0, stack); }
        });

        // Pouch slots (6 slots)
        int currentY = startY;
        for (int i = 0; i < this.pouchInventory.getContainerSize(); i++) {
            this.addSlot(new Slot(this.pouchInventory, i, 134 + slotSizePlus2*(i%2), currentY));
            if (i % 2 == 1) currentY += slotSizePlus2;
        }

        // Add player inventory slots
        startY = 99;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * slotSizePlus2, startY + row * slotSizePlus2));
            }
        }
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, startX + col * slotSizePlus2, startY + 58));
        }
    }

    public static PalicoEntity decodeBuffer(Level level, FriendlyByteBuf buffer) {
        int i = buffer.readVarInt();
        return level.getEntity(i) instanceof PalicoEntity palico ? palico : null;
    }


//    @Override
//    public ItemStack quickMoveStack(Player player, int index) {
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(index);
//
//        if (slot != null && slot.hasItem()) {
//            ItemStack slotItemStack = slot.getItem();
//        }
//
//        return null;
//    }

    // Assume we have a data inventory of size 5
    // The inventory has 4 inputs (index 1 - 4) which outputs to a result slot (index 0)
    // We also have the 27 player inventory slots and the 9 hotbar slots
    // As such, the actual slots are indexed like so:
    //   - Data Inventory: Helm(0), Mail(1), Weapon(2), Pouch(3-8)
    //   - Player Inventory (9 - 35)
    //   - Player Hotbar (36 - 44)
    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();
            EquipmentSlot equipmentSlot = null;//quickMovedStack.getItem().getEquipmentSlot(quickMovedStack);

            if (rawStack.getItem() instanceof PetHuntingWeaponItem<?> petHuntingWeaponItem) {
                if (petHuntingWeaponItem.canEquip(rawStack, EquipmentSlot.MAINHAND, palicoEntity)) equipmentSlot = EquipmentSlot.MAINHAND;
            } else if (rawStack.getItem() instanceof PetHuntingArmorItem<?,?> huntingArmorItem) {
                if (huntingArmorItem.canEquip(rawStack, huntingArmorItem.getEquipmentSlot(), palicoEntity)) equipmentSlot = huntingArmorItem.getEquipmentSlot();
            }

            /*
            The following quick move logic can be simplified to if in data inventory,
            try to move to player inventory/hotbar and vice versa for containers
            that cannot transform data (e.g. chests).
            */
            // If the quick move was performed on the head, chest or weapon slots, send to player inventory
            if (quickMovedSlotIndex <= 2) {
                // Try to move the result slot into the player inventory/hotbar
                if (!this.moveItemStackTo(rawStack, 9, 45, true)) return ItemStack.EMPTY; // If cannot move, no longer quick move
            }

            // If the quick move was performed on pouch, also move to player inventory
            else if (quickMovedSlotIndex <= 8) {
                // Try to move the result slot into the player inventory/hotbar
                if (!this.moveItemStackTo(rawStack, 9, 45, true)) return ItemStack.EMPTY; // If cannot move, no longer quick move
            }

            // Else if the quick move was performed on the player inventory or hotbar slot
            else if (quickMovedSlotIndex < 45) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                boolean moveResult = false;
                //first try moving to equipment
                if (equipmentSlot == EquipmentSlot.HEAD) moveResult = this.moveItemStackTo(rawStack, 0, 0 + 1, false);
                if (equipmentSlot == EquipmentSlot.CHEST) moveResult = this.moveItemStackTo(rawStack, 1, 1 + 1, false);
                if (equipmentSlot == EquipmentSlot.MAINHAND) moveResult = this.moveItemStackTo(rawStack, 2, 2 + 1, false);
                //then if not, try moving to pouch
                if (!moveResult) moveResult = this.moveItemStackTo(rawStack, 3, 9, false);
                //if that also doesn't work, move from inv to hotbar or vice-versa
                if (!moveResult) {
                    if (quickMovedSlotIndex < 36) { //if in inventory
                        moveResult = this.moveItemStackTo(rawStack, 36, 45, false); //move to hotbar
                    } else { //if in hotbar
                        moveResult = this.moveItemStackTo(rawStack, 9, 36, false); //move to inventory
                    }
                }
                if (!moveResult) return ItemStack.EMPTY;
            }
            //if none of these attempts worked, quit


            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that that the stack count has changed
                quickMovedSlot.setChanged();
            }

            /*
            The following if statement and Slot#onTake call can be removed if the
            menu does not represent a container that can transform stacks (e.g.
            chests).
            */
            if (rawStack.getCount() == quickMovedStack.getCount()) {
                // If the raw stack was not able to be moved to another slot, no longer quick move
                return ItemStack.EMPTY;
            }
            // Execute logic on what to do post move with the remaining stack
            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


}
