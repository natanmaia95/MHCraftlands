package com.nateplays.my_neoforge_mod.entity.custom;

import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.entity.ai.MosswineAttackGoal;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class MosswineEntity extends Animal implements ILevelableEntity {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MosswineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GOLD_HEAD =
            SynchedEntityData.defineId(MosswineEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> EXP =
            SynchedEntityData.defineId(MosswineEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public MosswineEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.idleAnimationTimeout = this.getRandom().nextInt(100) + 100;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.2));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2, Ingredient.of(Tags.Items.MUSHROOMS), false));
        this.goalSelector.addGoal(2, new MosswineAttackGoal(this, 1.0f, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {

        if (!level.isClientSide()) {
            this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
            setIsGoldHead(random.nextFloat() < 0.1f);
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.canAddPassenger(player)) {
            player.startRiding(this);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.mobInteract(player, hand);
    }

    private void setupAnimationStates() {
        if (this.getPose() == Pose.STANDING && this.getDeltaMovement() != Vec3.ZERO) {
            if (this.idleAnimationTimeout <= 0.0) {
                this.idleAnimationTimeout = this.random.nextInt(200) + 100;
                this.idleAnimationState.start(this.tickCount);
            } else {
                this.idleAnimationTimeout--;
            }
        } else { this.idleAnimationState.stop(); }

        if (this.isAttacking() && this.attackAnimationTimeout <= 0) {
            this.attackAnimationTimeout = 25; //length in ticks of animation
            this.attackAnimationState.start(this.tickCount);
        } else {
            this.attackAnimationTimeout--;
        }

        if (!this.isAttacking()) this.attackAnimationState.stop();
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        super.updateWalkAnimation(partialTick);
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 6f, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
        builder.define(IS_GOLD_HEAD, false);
        buildLevelSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("gold_head", isGoldHead());
        addAdditionalLevelableSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setIsGoldHead(tag.getBoolean("gold_head"));
        readAdditionalLevelableSaveData(tag);
    }

    public void setIsGoldHead(boolean value) {
        this.entityData.set(IS_GOLD_HEAD, value);
    }

    public boolean isGoldHead() {
        return this.entityData.get(IS_GOLD_HEAD);
    }

    public void setAttacking(boolean value) {
        this.entityData.set(ATTACKING, value);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return this.getAttackBoundingBox().inflate(0.3).intersects(entity.getHitbox());
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Tags.Items.MUSHROOMS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.MOSSWINE.get().create(level);
    }

    @Override
    public EntityDataAccessor<Integer> getExpAccessor() {
        return EXP;
    }

    @Override
    public void doLevelUp(int newLevel) {}
}
