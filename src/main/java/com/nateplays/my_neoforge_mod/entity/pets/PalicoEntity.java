package com.nateplays.my_neoforge_mod.entity.pets;

import com.nateplays.my_neoforge_mod.entity.ai.MosswineAttackGoal;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
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
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class PalicoEntity extends Animal implements ILevelableEntity {

    private static final EntityDataAccessor<Integer> EXP =
            SynchedEntityData.defineId(PalicoEntity.class, EntityDataSerializers.INT);

    public PalicoEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.2));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2, Ingredient.of(Tags.Items.GEMS_EMERALD), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }




    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (!level.isClientSide()) {
            this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
            this.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.GOAT_HORN));
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!player.level().isClientSide()) {
            ItemStack handItem = player.getItemInHand(hand);
            if (handItem.getItem() instanceof PetHuntingArmorItem<?> armorItem) {
                EquipmentSlot slot = armorItem.getEquipmentSlot();
                ItemStack oldArmor = this.getItemBySlot(slot);
                this.setItemSlot(slot, handItem);
                player.setItemInHand(hand, oldArmor);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }




    @Override
    public EntityDataAccessor<Integer> getExpAccessor() {
        return EXP;
    }

    @Override
    public void doLevelUp(int newLevel) { return; }

    @Override
    public boolean isFood(ItemStack stack) { return false; }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }
}
