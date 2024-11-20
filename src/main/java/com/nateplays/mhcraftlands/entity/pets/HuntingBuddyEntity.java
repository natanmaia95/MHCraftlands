package com.nateplays.mhcraftlands.entity.pets;

import com.nateplays.mhcraftlands.attribute.ModAttributes;
import com.nateplays.mhcraftlands.effect.ModEffects;
import com.nateplays.mhcraftlands.entity.interfaces.ILevelableEntity;
import com.nateplays.mhcraftlands.item.ModItems;
import com.nateplays.mhcraftlands.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.item.weapons.PetHuntingWeaponItem;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mutable;

import java.util.function.Predicate;

public abstract class HuntingBuddyEntity extends TamableAnimal implements ILevelableEntity {

    static final Logger LOGGER = LoggerFactory.getLogger(HuntingBuddyEntity.class);


    private static final EntityDataAccessor<Integer> EXP;
    public static final Predicate<LivingEntity> ALLIED_TO_HUNTERS_SELECTOR;
    public static final Predicate<LivingEntity> NOT_ALLIED_TO_HUNTERS_SELECTOR;


    private boolean goalsCleared = false; //for KO effect


    protected HuntingBuddyEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false, false);
    }

    static {
        EXP =  SynchedEntityData.defineId(HuntingBuddyEntity.class, EntityDataSerializers.INT);
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
    public void tick() {
        super.tick();
        this.tickKOEffect();
    }

    public void tickKOEffect() {
        if (this.isKOed()) {
            if (!goalSelector.getAvailableGoals().isEmpty()) {
                goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                goalSelector.getAvailableGoals().clear();
                goalsCleared = true;
            }
        } else {
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

        if (player.level().isClientSide()) return super.mobInteract(player, hand);

        if (handItem.is(ModItems.DISMISS_BUDDY_VOUCHER)) {
            if ((this.isTame() && this.isOwnedBy(player)) || player.isCreative()) return mobInteractDismissBuddy(player, hand, handItem);
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
        return this.hasEffect(ModEffects.HUNTING_BUDDY_KO); //TODO: change into entity data to sync to client
    }
}
