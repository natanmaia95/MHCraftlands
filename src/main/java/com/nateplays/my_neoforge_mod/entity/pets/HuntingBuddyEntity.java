package com.nateplays.my_neoforge_mod.entity.pets;

import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HuntingBuddyEntity extends TamableAnimal implements ILevelableEntity {
    static final Logger LOGGER = LoggerFactory.getLogger(HuntingBuddyEntity.class);
    private static final EntityDataAccessor<Integer> EXP =
            SynchedEntityData.defineId(HuntingBuddyEntity.class, EntityDataSerializers.INT);

    protected HuntingBuddyEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false, false);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        buildLevelSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        addAdditionalLevelableSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        readAdditionalLevelableSaveData(tag);
    }





    @Override
    public EntityDataAccessor<Integer> getExpAccessor() {
        return EXP;
    }

    @Override
    public void doLevelUp(int newLevel) { return; }






    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.OFF_HAND && player.getItemInHand(hand).isEmpty()) return super.mobInteract(player, hand);

        if (!player.level().isClientSide()) {
            ItemStack handItem = player.getItemInHand(hand);
            LOGGER.debug("a");
            //Try to equip armor! //TODO: player can only change armor of owned
            if (isValidArmorItem(handItem)) return mobInteractArmorItem(player, hand, handItem);
            LOGGER.debug("b");
            if (this.isTame()) {
                LOGGER.debug("c");
                if (handItem.isEmpty() && isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    LOGGER.debug("d");
                    return InteractionResult.SUCCESS_NO_ITEM_USED;
                }
            } else {
                LOGGER.debug("e");
                //Try to tame
                if (isTameItem(handItem)) return mobInteractTryTame(player, hand, handItem);
            }
        }
        LOGGER.debug("f");
        return super.mobInteract(player, hand);
    }

    public InteractionResult mobInteractArmorItem(Player player, InteractionHand hand, ItemStack stack) {
        PetHuntingArmorItem<?, ?> armorItem = (PetHuntingArmorItem<?,?>) stack.getItem();
//        LOGGER.debug(stack.toString());
        EquipmentSlot slot = armorItem.getEquipmentSlot();
//        LOGGER.debug(slot.toString());
        ItemStack oldArmor = this.getItemBySlot(slot);
        this.setItemSlot(slot, stack);
        player.setItemInHand(hand, oldArmor);
        return InteractionResult.CONSUME;
    }

    public InteractionResult mobInteractTryTame(Player player, InteractionHand hand, ItemStack stack) {
        boolean isSuccess = true;
        if (isSuccess) {
            stack.shrink(1);
            this.tame(player);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, (byte)7);
        } else {
            this.level().broadcastEntityEvent(this, (byte)6);
        }
        return InteractionResult.CONSUME;
    }


    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setTarget(null); //forget targets.
    }

    //TODO: replace this. It's here for test purposes.
    public boolean isTameItem(ItemStack stack) { return false; }

    public boolean isValidArmorItem(ItemStack stack) {
        return stack.getItem() instanceof PetHuntingArmorItem<?,?>;
    }
}
