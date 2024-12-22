package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.entity.interfaces.ILevelableEntity;
import com.nateplays.mhcraftlands.pet.goals.HuntingBuddyHurtByTargetGoal;
import com.nateplays.mhcraftlands.pet.goals.PalicoTamedHarvestBlockGoal;
import com.nateplays.mhcraftlands.pet.gui.PalicoInventoryMenu;
import com.nateplays.mhcraftlands.pet.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import com.nateplays.mhcraftlands.pet.sound.MHPetSounds;
import com.nateplays.mhcraftlands.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class PalicoEntity extends HuntingBuddyEntity implements ILevelableEntity, InventoryCarrier, MenuProvider {
    //Base class for all sorts of Lynians.

    public final AnimationState livingAnimState = new AnimationState();
    public final AnimationState sitAnimState = new AnimationState();
    public final AnimationState standUpAnimState = new AnimationState();
    public final AnimationState faintAnimState = new AnimationState();

    //TODO: make all these containers a single class for all pet
    public final Container helmArmorAccess = new ContainerSingleItem() {
        public ItemStack getTheItem() {
            return PalicoEntity.this.getItemBySlot(EquipmentSlot.HEAD);
        }
        public void setTheItem(ItemStack stack) {
            PalicoEntity.this.setItemSlot(EquipmentSlot.HEAD, stack);
        }
        public boolean stillValid(Player player) { return true; }
        public void setChanged() {}

        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            if (stack.getItem() instanceof PetHuntingArmorItem petHuntingArmorItem) {
                return petHuntingArmorItem.canEquip(stack, EquipmentSlot.HEAD, PalicoEntity.this);
            } else return false;
        }
    };

    public final Container mailArmorAccess = new ContainerSingleItem() {
        public ItemStack getTheItem() {
            return PalicoEntity.this.getItemBySlot(EquipmentSlot.CHEST);
        }
        public void setTheItem(ItemStack stack) {
            PalicoEntity.this.setItemSlot(EquipmentSlot.CHEST, stack);
        }
        public boolean stillValid(Player player) { return true; }
        public void setChanged() {}

        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            if (stack.getItem() instanceof PetHuntingArmorItem petHuntingArmorItem) {
                return petHuntingArmorItem.canEquip(stack, EquipmentSlot.CHEST, PalicoEntity.this);
            } else return false;
        }
    };

    public final Container weaponAccess = new ContainerSingleItem() {
        public ItemStack getTheItem() {
            return PalicoEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
        }
        public void setTheItem(ItemStack stack) {
            PalicoEntity.this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        }
        public boolean stillValid(Player player) { return true; }
        public void setChanged() {}

        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            if (stack.getItem() instanceof PetHuntingWeaponItem petHuntingWeaponItem) {
                return petHuntingWeaponItem.canEquip(stack, EquipmentSlot.MAINHAND, PalicoEntity.this);
            } else return false;
        }
    };

    private final SimpleContainer pouchInventory = new SimpleContainer(6) {};

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;

    public PalicoEntity(EntityType<? extends HuntingBuddyEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return HuntingBuddyEntity.createAttributes()
                .add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 1.0).add(ModAttributes.DEFENSE, 0.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimal.TamableAnimalPanicGoal(2.0, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        //this.goalSelector.addGoal(3, new Wolf.WolfAvoidEntityGoal<>(this, Llama.class, 24.0F, 1.5, 1.5));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.6F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.5, 12.0F, 4.0F));

//        this.goalSelector.addGoal(7, new RemoveBlockGoal(Blocks.OAK_LOG, this, 1.0, 16));

        this.goalSelector.addGoal(7, new PalicoTamedHarvestBlockGoal(this, ModTags.Blocks.PALICO_HARVESTABLE_ORES, 1.2, 8, 2, 100));
        this.goalSelector.addGoal(7, new PalicoTamedHarvestBlockGoal(this, ModTags.Blocks.PALICO_HARVESTABLE_PLANTS, 1.2, 8, 2, 200));

        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2, Ingredient.of(Tags.Items.GEMS_EMERALD), false));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HuntingBuddyHurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));

    }

    public static boolean checkPalicoSpawnRules(EntityType<? extends PalicoEntity> palico, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        boolean isBrightEnough = level.getRawBrightness(pos, 0) > 0; //can't spawn in complete darkness
        long dayTime = level.dayTime();
        boolean isCorrectTimeOfDay = true;//dayTime < 2000 || dayTime > 12000; // don't spawn between 8am and 6pm
        return isBrightEnough && isCorrectTimeOfDay && checkMobSpawnRules(palico, level, spawnType, pos, random);
    }




    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.OFF_HAND && player.getItemInHand(hand).isEmpty()) return super.mobInteract(player, hand);

        //open menu
        if (player instanceof ServerPlayer serverPlayer && !player.isShiftKeyDown() && player.getItemInHand(hand).isEmpty()) {
//            serverPlayer.openMenu(new SimpleMenuProvider(
//                    (containerId, playerInventory, menuPlayer) -> new PalicoInventoryMenu(containerId, playerInventory), PalicoInventoryScreen.TITLE)
//            );
            serverPlayer.openMenu(this, buffer -> buffer.writeVarInt(this.getId()));
            return InteractionResult.PASS;
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PalicoInventoryMenu(containerId, playerInventory, this);
    }



    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    public void setupAnimationStates() {
        if (this.isKOed()) {
            this.resetLivingAnimations();
            this.faintAnimState.startIfStopped(this.tickCount);

        } else {
            this.faintAnimState.stop();

            this.livingAnimState.startIfStopped(this.tickCount);
            if (this.isInSittingPose()) {
                this.sitAnimState.startIfStopped(this.tickCount);
                this.standUpAnimState.stop();
            } else {
                this.sitAnimState.stop();
                this.standUpAnimState.startIfStopped(this.tickCount);
            }
        }
    }

    public void resetLivingAnimations() {
        this.livingAnimState.stop();
        this.standUpAnimState.stop();
        this.sitAnimState.stop();
    }

    //Extend attack range because palico is tiny!
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return this.getAttackBoundingBox().inflate(0.5).intersects(entity.getHitbox());
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    // Sounds section
    @Override
    protected @Nullable SoundEvent getHurtSound(@NotNull DamageSource damageSource) { return MHPetSounds.FELYNE_HURT.get(); }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return MHPetSounds.FELYNE_DEATH.get();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() { return MHPetSounds.FELYNE_AMBIENT.get(); }

    @Override
    public int getAmbientSoundInterval() {
        RandomSource randomSource = RandomSource.create();
        return 300 + randomSource.nextInt(200);
    }

    @Override
    public @NotNull SimpleContainer getInventory() {
        return this.pouchInventory;
    }

    public boolean wantsToPickUp(ItemStack stack) {
        return this.getInventory().canAddItem(stack);
    }
}
