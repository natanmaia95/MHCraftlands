package com.nateplays.mhcraftlands.event;

import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.interfaces.ILevelableEntity;
import com.nateplays.mhcraftlands.item.custom.HammerItem;
import com.nateplays.mhcraftlands.item.weapons.HuntingWeaponItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class EventBusEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void livingDeathEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!livingEntity.level().isClientSide()) {
            int radius = 32;

            ServerLevel serverLevel = (ServerLevel) livingEntity.level();
            int experienceReward = livingEntity.getExperienceReward(serverLevel, null);
            Vec3 position = livingEntity.position();
            AABB aabb = new AABB(position.add(radius, radius, radius), position.add(-radius, -radius, -radius));

            List<LivingEntity> entitiesAroundDeath = serverLevel.getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity entity : entitiesAroundDeath) {
                if (entity instanceof ILevelableEntity levelableEntity) {
                    levelableEntity.gainExp(experienceReward);
                }
            }
        }
    }


    private static final Set<BlockPos> HAMMER_HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.isCrouching()) return; //disable hammer functionality

            BlockPos initialBlockPos = event.getPos();
            if (HAMMER_HARVESTED_BLOCKS.contains(initialBlockPos)) return; //avoids recursion
            LOGGER.debug("initialPos: " + initialBlockPos.toString());

            BlockState blockAtInitialPos = event.getLevel().getBlockState(initialBlockPos);
            if (!hammer.isCorrectToolForDrops(mainHandItem, blockAtInitialPos)) return; //avoids exploits with fast breaking blocks

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos == initialBlockPos) {
                    LOGGER.debug("pos: " + pos.toString() + " ||| initialPos: " + initialBlockPos.toString());
                    continue;
                }
                if (!hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) continue;

                HAMMER_HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos); //can cause recursion here
                LOGGER.debug("broken at " + pos.toString());
                HAMMER_HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onMovementInputUpdated(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        if (player.isUsingItem() && player.getUseItem().getItem() instanceof HuntingWeaponItem) {
            event.getInput().leftImpulse /= 0.2F;
            event.getInput().forwardImpulse /= 0.2F;

            float slowdown = ((HuntingWeaponItem) player.getUseItem().getItem()).getUseItemSlowdown(player, player.getUseItem());
            event.getInput().leftImpulse *= slowdown;
            event.getInput().forwardImpulse *= slowdown;
        }
    }
}
