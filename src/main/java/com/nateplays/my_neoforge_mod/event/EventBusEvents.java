package com.nateplays.my_neoforge_mod.event;

import com.mojang.logging.LogUtils;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.item.custom.HammerItem;
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
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
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

    @SubscribeEvent
    public static void livingPreDamageEvent(LivingDamageEvent.Pre event) {
        livingPreDamageDefenseStep(event);

    }

    public static void livingPreDamageDefenseStep(LivingDamageEvent.Pre event) {
        LivingEntity livingEntity = event.getEntity();
        float damageAmount = event.getNewDamage();
        if (!livingEntity.getAttributes().hasAttribute(ModAttributes.DEFENSE)) return;
        double defense = livingEntity.getAttributeValue(ModAttributes.DEFENSE);
        if (defense == 0) return;
        double defenseReduction = 8.0 / (8.0 + defense);
        double damageAfterDefense = damageAmount * defenseReduction;
        event.setNewDamage((float)damageAfterDefense);
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
}
