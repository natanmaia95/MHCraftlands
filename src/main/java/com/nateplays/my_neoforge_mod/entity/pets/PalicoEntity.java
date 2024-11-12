package com.nateplays.my_neoforge_mod.entity.pets;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.ai.MosswineAttackGoal;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import com.nateplays.my_neoforge_mod.entity.pets.goals.HuntingBuddyHurtByTargetGoal;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
import com.nateplays.my_neoforge_mod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PalicoEntity extends HuntingBuddyEntity implements ILevelableEntity {

    public final AnimationState livingAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standUpAnimationState = new AnimationState();


    public PalicoEntity(EntityType<? extends HuntingBuddyEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.2));
//        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2, Ingredient.of(Tags.Items.GEMS_EMERALD), false));
//        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
//        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f));
//        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
//
//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimal.TamableAnimalPanicGoal(2.0, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        //this.goalSelector.addGoal(3, new Wolf.WolfAvoidEntityGoal<>(this, Llama.class, 24.0F, 1.5, 1.5));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.6F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.5, 8.0F, 2.0F));

        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HuntingBuddyHurtByTargetGoal(this).setAlertOthers());
//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
//        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static boolean checkPalicoSpawnRules(EntityType<? extends PalicoEntity> palico, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        boolean isBrightEnough = level.getRawBrightness(pos, 0) > 0; //can't spawn in complete darkness
        long dayTime = level.dayTime();
        boolean isCorrectTimeOfDay = true;//dayTime < 2000 || dayTime > 12000; // don't spawn between 8am and 6pm
        return isBrightEnough && isCorrectTimeOfDay && checkMobSpawnRules(palico, level, spawnType, pos, random);
    }







    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    public void setupAnimationStates() {
        this.livingAnimationState.startIfStopped(this.tickCount);

        if (this.isInSittingPose()) {
            this.sitAnimationState.startIfStopped(this.tickCount);
            this.standUpAnimationState.stop();
        } else {
            this.sitAnimationState.stop();
            this.standUpAnimationState.startIfStopped(this.tickCount);
        }
    }






    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    // Sounds section
    @Override
    protected @Nullable SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return ModSounds.FELYNE_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.FELYNE_DEATH.get();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.FELYNE_AMBIENT.get();
    }

    @Override
    public int getAmbientSoundInterval() {
        RandomSource randomSource = RandomSource.create();
        return 300 + randomSource.nextInt(200);
    }
}
