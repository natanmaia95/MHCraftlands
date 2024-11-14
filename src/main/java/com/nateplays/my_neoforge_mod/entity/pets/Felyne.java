package com.nateplays.my_neoforge_mod.entity.pets;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.item.weapons.ModWeaponItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class Felyne extends PalicoEntity {
    public Felyne(EntityType<? extends HuntingBuddyEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/entity/pets/palico_felyne.png");
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (!level.isClientSide()) {
            if (this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModWeaponItems.F_BONE_PICK.get()));
            }
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
