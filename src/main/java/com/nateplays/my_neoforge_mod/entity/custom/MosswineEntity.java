package com.nateplays.my_neoforge_mod.entity.custom;

import com.nateplays.my_neoforge_mod.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class MosswineEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public MosswineEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.idleAnimationTimeout = this.getRandom().nextInt(100) + 100;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.2));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2, Ingredient.of(Tags.Items.MUSHROOMS), false));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f));

        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25);
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.canAddPassenger(player)) {
            player.startRiding(this);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.mobInteract(player, hand);
    }

    private void setupAnimationStates() {
        if (this.getPose() == Pose.STANDING) {
            if (this.idleAnimationTimeout <= 0.0) {
                this.idleAnimationTimeout = this.random.nextInt(200) + 100;
                this.idleAnimationState.start(this.tickCount);
            } else {
                this.idleAnimationTimeout--;
            }
        } else {
            this.idleAnimationState.stop();
        }
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

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on the animal type)
     *
     * @param stack
     */
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Tags.Items.MUSHROOMS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.MOSSWINE.get().create(level);
    }
}
