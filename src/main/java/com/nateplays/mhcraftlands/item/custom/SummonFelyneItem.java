package com.nateplays.mhcraftlands.item.custom;

import com.nateplays.mhcraftlands.entity.ModEntities;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.common.armor.ModArmorItems;
import com.nateplays.mhcraftlands.common.weapon.ModWeaponItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;

public class SummonFelyneItem extends Item {

    public SummonFelyneItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            Player player = context.getPlayer();
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            BlockPos actualBlockPos;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                actualBlockPos = blockpos;
            } else {
                actualBlockPos = blockpos.relative(direction);
            }

            EntityType<?> entityType = MHPetEntities.FELYNE.get();
            boolean shouldOffsetYMore = !Objects.equals(blockpos, actualBlockPos) && direction == Direction.UP;
            PalicoEntity possibleEntity = (PalicoEntity) entityType.spawn((ServerLevel)level, itemstack, player, actualBlockPos, MobSpawnType.BUCKET, true, shouldOffsetYMore);
            if (possibleEntity != null) {
                itemstack.shrink(1);
                level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
                //new stuff
                possibleEntity.tame(player);
                possibleEntity.lookAt(player, 1000f, 1000f);
                //TODO: play sound
                //TODO: add starter armor
                applyStarterArmor(possibleEntity, level.getRandom());
            }

            return InteractionResult.CONSUME;
        }
    }

    public static void applyStarterArmor(PalicoEntity palicoEntity, RandomSource random) {
        int roll = random.nextInt(2);
        switch (roll) {
            case 0:
                palicoEntity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MHPetArmorItems.F_ACORN_HELM.get()));
                palicoEntity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MHPetArmorItems.F_ACORN_MAIL.get()));
                break;
            case 1:
                palicoEntity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MHPetArmorItems.F_KAMURA_HELM.get()));
                palicoEntity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MHPetArmorItems.F_KAMURA_MAIL.get()));
                palicoEntity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MHPetWeaponItems.F_KAMURA_BOKKEN.get()));
                break;
            default:
                break;
        }
    }
}
