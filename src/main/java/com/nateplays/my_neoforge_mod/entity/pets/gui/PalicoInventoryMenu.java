package com.nateplays.my_neoforge_mod.entity.pets.gui;

import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.gui.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
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
        int startY = 12;
        int slotSizePlus2 = 18;

        // Armor Slots?
        this.addSlot(new Slot(this.palicoEntity.helmArmorAccess, 0, startX, startY));
        this.addSlot(new Slot(this.palicoEntity.mailArmorAccess, 0, startX + slotSizePlus2, startY));

        // Pouch slots (9 slots)
        for (int i = 0; i < this.pouchInventory.getContainerSize(); i++) {
            this.addSlot(new Slot(this.pouchInventory, i, startX + i * slotSizePlus2, startY + slotSizePlus2));
        }

        // Add player inventory slots
        startY = 84;
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

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


}
