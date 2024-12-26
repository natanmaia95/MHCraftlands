package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.weapon.ModWeaponItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Felyne extends PalicoEntity {
    public Felyne(EntityType<? extends HuntingBuddyEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/entity/pet/palico_felyne.png");
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (!level.isClientSide()) {
            if (this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(MHPetWeaponItems.F_BONE_PICK.get()));
            }
            this.setToolPreference(PetToolPreference.getRandom(this.getRandom()));
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }



    @Override
    public boolean isFood(ItemStack stack) { return false; }

    @Override
    public boolean isTameItem(ItemStack stack) {
        return stack.getItem() == Items.EMERALD;
    }
}
