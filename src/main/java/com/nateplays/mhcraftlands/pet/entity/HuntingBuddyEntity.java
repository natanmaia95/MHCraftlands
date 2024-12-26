package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.entity.interfaces.ILevelableEntity;
import com.nateplays.mhcraftlands.pet.gui.PetToolContainer;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import com.nateplays.mhcraftlands.pet.item.PetToolItem;
import com.nateplays.mhcraftlands.pet.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.pet.item.custom.PetTrainingBookItem;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public abstract class HuntingBuddyEntity extends TamableAnimal implements ILevelableEntity, PetToolUser {

    static final Logger LOGGER = LoggerFactory.getLogger(HuntingBuddyEntity.class);


    private static final EntityDataAccessor<Integer> EXP;
    private static final EntityDataAccessor<Integer> TOOL_PREFERENCE;
    public static final Predicate<LivingEntity> ALLIED_TO_HUNTERS_SELECTOR;
    public static final Predicate<LivingEntity> NOT_ALLIED_TO_HUNTERS_SELECTOR;

    private static final EntityDataAccessor<Byte> BUDDY_DATA_FLAGS =
            SynchedEntityData.defineId(HuntingBuddyEntity.class, EntityDataSerializers.BYTE);
    private static final int FLAG_KO_ID = 0b00000001;

    public static final int KO_DURATION = 1200;

    private boolean goalsCleared = false; //for KO effect
    protected boolean usingTool = false;

    private PetToolContainer toolsContainer = new PetToolContainer(this,5);




    protected HuntingBuddyEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false, false);
    }

    static {
        EXP = SynchedEntityData.defineId(HuntingBuddyEntity.class, EntityDataSerializers.INT);
        TOOL_PREFERENCE = SynchedEntityData.defineId(HuntingBuddyEntity.class, EntityDataSerializers.INT);
        ALLIED_TO_HUNTERS_SELECTOR = (livingEntity) -> {
            //TODO: change half these steps to a tag check
            if (livingEntity instanceof Player) return true;
            if (livingEntity instanceof HuntingBuddyEntity buddyEntity) {
                return buddyEntity.isTame();
            }
            if (livingEntity instanceof TamableAnimal) return true;
            if (livingEntity instanceof Villager) return true;
            return false;
        };
        NOT_ALLIED_TO_HUNTERS_SELECTOR = (e) -> (!ALLIED_TO_HUNTERS_SELECTOR.test(e));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(ModAttributes.DEFENSE)
                .add(ModAttributes.FIRE_DAMAGE).add(ModAttributes.WATER_DAMAGE); //TODO: add the rest of them
    }

    public abstract ResourceLocation getTextureLocation();

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BUDDY_DATA_FLAGS, (byte)0);
        buildLevelSynchedData(builder);
        buildBuddyToolsSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("is_knocked_out", isKOed());
        addAdditionalLevelableSaveData(tag);
        writeBuddyToolsToTag(tag, this.registryAccess());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setKOed(tag.getBoolean("is_knocked_out"));
        readAdditionalLevelableSaveData(tag);
        readBuddyToolsFromTag(tag, this.registryAccess());
    }

    @Override
    public void tick() {
        super.tick();
        this.tickKOEffect();
    }

    public void tickKOEffect() {
        if (this.isKOed()) {
            // reset goals
            if (!goalSelector.getAvailableGoals().isEmpty()) {
                goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                goalSelector.getAvailableGoals().clear();
                goalsCleared = true;
            }
            // KO regen, 5% every 3 seconds
            if ((this.tickCount+1) % (KO_DURATION / 20) == 0) {
                this.heal(this.getMaxHealth() / 20);
                if (this.getMaxHealth() <= this.getHealth()) this.setKOed(false);
            }

        } else {
            // return goals
            if (goalSelector.getAvailableGoals().isEmpty()) {
                this.registerGoals();
                goalsCleared = false;
            }
            // passive health regen, 1 health every minute
            if ((this.tickCount+1) % 1200 == 0) this.heal(1.0f);

        }
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity target) {
        return super.canAttack(target) && !(this.isTame() && ALLIED_TO_HUNTERS_SELECTOR.test(target));
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity livingentity, @NotNull TargetingConditions condition) {
        return super.canAttack(livingentity, condition) && !(this.isTame() && ALLIED_TO_HUNTERS_SELECTOR.test(livingentity));
    }

    @Override
    public boolean wantsToAttack(@NotNull LivingEntity target, @NotNull LivingEntity owner) {
        if (!this.isTame()) return true;
        return NOT_ALLIED_TO_HUNTERS_SELECTOR.test(target);
    }

    @Override
    public EntityDataAccessor<Integer> getExpAccessor() {
        return EXP;
    }

    @Override
    public void doLevelUp(int newLevel) { return; }


    @Override
    protected void hurtArmor(@NotNull DamageSource damageSource, float damageAmount) {
        if (damageAmount > 0 && !damageSource.is(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES)) {
            this.doHurtEquipment(damageSource, 1.0f, new EquipmentSlot[]{EquipmentSlot.CHEST, EquipmentSlot.HEAD});
        }
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        boolean result = super.doHurtTarget(target);
        if (result && target instanceof LivingEntity) {
            ItemStack weapon = this.getMainHandItem(); // Get the item in the mob's main hand.
            if (!weapon.isEmpty() && weapon.isDamageableItem()) {
                weapon.hurtAndBreak(1, this, EquipmentSlot.MAINHAND);
            }
        }
        return result;
    }



    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.OFF_HAND && player.getItemInHand(hand).isEmpty()) return super.mobInteract(player, hand);
        ItemStack handItem = player.getItemInHand(hand);

//        if (player.level().isClientSide()) return super.mobInteract(player, hand);

        if (handItem.is(MHPetItems.DISMISS_BUDDY_VOUCHER)) {
            if ((this.isTame() && this.isOwnedBy(player)) || player.isCreative()) return mobInteractDismissBuddy(player, hand, handItem);
        }

        if (handItem.is(MHPetItems.TRAINING_BOOK)) {
            if ((this.isTame() && this.isOwnedBy(player)) || player.isCreative())
                return PetTrainingBookItem.interactWithBuddy(handItem, this, player);
        }

        if (this.isTame() && this.isOwnedBy(player)) {
            //if shift pressed, do sit actions
            if (player.isShiftKeyDown()) {
                this.setOrderedToSit(!this.isOrderedToSit());
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            }
            //try swapping armors
            if (isValidArmorItem(handItem)) return mobInteractArmorItem(player, hand, handItem);
            if (isValidWeaponItem(handItem)) return mobInteractWeaponItem(player, hand, handItem);
        }

        if (!this.isTame()) {
            if (isTameItem(handItem)) return mobInteractTryTame(player, hand, handItem);
        }

        return super.mobInteract(player, hand);
    }


    public InteractionResult mobInteractArmorItem(Player player, InteractionHand hand, ItemStack stack) {
        if (player.level().isClientSide()) return InteractionResult.CONSUME;
        PetHuntingArmorItem<?, ?> armorItem = (PetHuntingArmorItem<?,?>) stack.getItem();
//        LOGGER.debug(stack.toString());
        EquipmentSlot slot = armorItem.getEquipmentSlot();
//        LOGGER.debug(slot.toString());
        ItemStack oldArmor = this.getItemBySlot(slot);
        this.setItemSlot(slot, stack);
        player.setItemInHand(hand, oldArmor);
        return InteractionResult.CONSUME;
    }

    public InteractionResult mobInteractWeaponItem(Player player, InteractionHand hand, ItemStack stack) {
        if (player.level().isClientSide()) return InteractionResult.CONSUME;
        ItemStack oldWeapon = this.getItemBySlot(EquipmentSlot.MAINHAND);
        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        player.setItemInHand(hand, oldWeapon);
        return InteractionResult.CONSUME;
    }


    public InteractionResult mobInteractTryTame(Player player, InteractionHand hand, ItemStack stack) {
        boolean isSuccess = this.random.nextFloat() < 0.5;
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

    public InteractionResult mobInteractDismissBuddy(Player player, InteractionHand hand, ItemStack stack) {
        if (this.level().isClientSide()) {
            for (int i = 0; i < 10; i++) {
                this.level().addParticle(ParticleTypes.POOF,
                        this.getX() + this.random.nextFloat()*0.2, this.getY() + 0.5, this.getZ() + this.random.nextFloat()*0.2,
                        0.0, 0.1, 0.0);
            }

        } else {
            stack.shrink(1);
            MutableComponent dismissMessage = MutableComponent.create(this.getDisplayName().getContents());
            dismissMessage.append(Component.literal(" was sent to greener pastures. Goodbye!"));
            player.sendSystemMessage(dismissMessage);
            //TODO: make translatable

            this.remove(RemovalReason.KILLED);

        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        this.setTarget(null); //forget targets.
        super.setOrderedToSit(orderedToSit);
    }

    public boolean isTameItem(ItemStack stack) { return false; }

    public boolean shouldTryTeleportToOwner() {
        LivingEntity livingentity = this.getOwner();
        return livingentity != null && this.distanceToSqr(this.getOwner()) >= this.maxDistanceBeforeTeleport()*this.maxDistanceBeforeTeleport();
    }

    public double maxDistanceBeforeTeleport() {
        return 20.0;
    }



    public boolean isValidArmorItem(ItemStack stack) {
        if (stack.getItem() instanceof PetHuntingArmorItem<?,?> petHuntingArmorItem) {
            EquipmentSlot slot = petHuntingArmorItem.getEquipmentSlot();
            boolean result = petHuntingArmorItem.canEquip(stack, slot, this);
            return result;
        }
        return false;
    }

    public boolean isValidWeaponItem(ItemStack stack) {
        if (stack.getItem() instanceof PetHuntingWeaponItem<?> petHuntingWeaponItem) {
            return petHuntingWeaponItem.canEquip(stack, EquipmentSlot.MAINHAND, this);
        }
        return false;
    }

    public boolean isKOed() {
        return (this.entityData.get(BUDDY_DATA_FLAGS) & FLAG_KO_ID) != 0;
    }

    public void setKOed(boolean value) {
        byte oldFlags = this.entityData.get(BUDDY_DATA_FLAGS);
        if (value == true) {
            this.entityData.set(BUDDY_DATA_FLAGS, (byte) (oldFlags | FLAG_KO_ID));
            this.navigation.stop();
            this.setAggressive(false);
        } else {
            int mask = ~FLAG_KO_ID;
            this.entityData.set(BUDDY_DATA_FLAGS, (byte) (oldFlags & mask));
        }
    }

    @Override
    public boolean isAlive() {
        return super.isAlive() && !isKOed();
    }

    public boolean isInCombat() {
        return this.getTarget() != null;
    }

    @Override
    public EntityDataAccessor<Integer> getToolPreferenceAccessor() {
        return TOOL_PREFERENCE;
    }

    @Override
    public PetToolContainer getToolsContainer() {
        return toolsContainer;
    }


    @Nullable
    public ItemStack getCurrentTool() {
        ItemStack offHand = getItemInHand(InteractionHand.OFF_HAND);
        if (offHand.getItem() instanceof PetToolItem<?> petToolItem) {
            if (petToolItem.canUsePetTool(offHand, this)) return offHand;
        }
        return null;
    }
}
