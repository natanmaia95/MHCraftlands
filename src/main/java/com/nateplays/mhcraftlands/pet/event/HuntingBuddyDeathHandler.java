package com.nateplays.mhcraftlands.pet.event;

import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.effect.ModEffects;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.slf4j.Logger;

import java.util.List;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class HuntingBuddyDeathHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void livingDeathEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level();
        if (!level.isClientSide() && livingEntity instanceof HuntingBuddyEntity buddyEntity) {
            if (buddyEntity.isTame()) {
                event.setCanceled(true);
                level.playLocalSound(buddyEntity,
                        SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.NEUTRAL, 1.0f, 1.0f);

                buddyEntity.setHealth(0.01f);
                buddyEntity.setOrderedToSit(false);
                buddyEntity.setInSittingPose(false);
                buddyEntity.setKOed(true);

                // remove buddy as target from nearby entities
                // Doesn't work?
//                double radius = 20.0;
//                AABB boundingBox = buddyEntity.getBoundingBox().inflate(radius);
//                List<Mob> nearbyMobs = level.getEntitiesOfClass(Mob.class, boundingBox);
//                for (Mob mob : nearbyMobs) {
//                    System.out.println("mob: " + mob.toString());
//                    if (mob.getTarget() == buddyEntity) {
//                        LivingEntity owner = buddyEntity.getOwner();
//                        if (owner != null) {
//                            mob.setTarget(owner);
////                            mob.setLastHurtByMob(null);
//                        }
//                        System.out.println("mob had target cleared");
////                        mob.setTarget(null); // Clear target
////                        mob.setLastHurtMob(null);
////                        mob.setLastHurtByMob(null);
//////                        mob.setLastHurtByPlayer((Player) buddyEntity.getOwner());
////                        mob.targetSelector.tick();
////                        mob.getNavigation().stop(); // Stop navigation
////                        mob.getNavigation().recomputePath();
////
////                        GoalSelector targetSelector = mob.targetSelector;
////                        targetSelector.getAvailableGoals().forEach(wrappedGoal -> {
////                            if (wrappedGoal.getGoal() instanceof NearestAttackableTargetGoal<?> nearestAttackableTargetGoal) {
////                                nearestAttackableTargetGoal.stop();
////                            }
////                        });
//                    }
//                }

            }
        }
    }

    @SubscribeEvent
    public static void livingDamageEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof HuntingBuddyEntity buddyEntity) {
            if (buddyEntity.isKOed()) {
                event.setCanceled(true);
            } else if (buddyEntity.isTame()) {
                if (event.getSource().getEntity() != null) {
                    Entity sourceEntity = event.getSource().getEntity();

                    //no damage if player hits it outside of creative mode
                    if (sourceEntity instanceof Player player && !player.isCreative()) {
                        event.setAmount(0.0f);
                    }
                }
            }
        }
    }

}
