package com.nateplays.mhcraftlands.entity.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ILevelableEntity {
    static final Logger LOGGER = LoggerFactory.getLogger(ILevelableEntity.class);

    EntityDataAccessor<Integer> getExpAccessor();
    // Add one of these to your entity:
    //    private static final EntityDataAccessor<Integer> EXP =
    //            SynchedEntityData.defineId(ENTITYCLASSNAME.class, EntityDataSerializers.INT);

    public default void buildLevelSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(getExpAccessor(), 0); //levelable entities start at 0 exp
    }

    default void addAdditionalLevelableSaveData(@NotNull CompoundTag tag) {
        tag.putInt("exp", getExp());
    }

    default void readAdditionalLevelableSaveData(@NotNull CompoundTag tag) {
        setExp(tag.getInt("exp"));
    }

    default int getExp() {
        return ((LivingEntity) this).getEntityData().get(getExpAccessor());
    }

    @ApiStatus.NonExtendable
    default void setExp(int value) {
        ((LivingEntity) this).getEntityData().set(getExpAccessor(), value);
    }

    @ApiStatus.NonExtendable
    default void gainExp(int value) {
        int oldLevel = getExpLevel();
        LOGGER.debug(((LivingEntity) this).getName().getString() + " ENTITY GAINED EXP: " + String.valueOf(value));
        setExp(getExp() + value);
        while (getExpLevel() > oldLevel) {
            oldLevel += 1;
            doLevelUp(oldLevel);
            LOGGER.debug(((LivingEntity) this).getName().getString() + " ENTITY LEVELED UP: level " + String.valueOf(oldLevel));
        }
    }

    @ApiStatus.NonExtendable
    default int getExpLevel( ) { return calculateLevelAtExp(getExp()); }
    @ApiStatus.NonExtendable
    default int getExpToNextLevel() { return calculateExpToReachLevel(getExpLevel()+1); }
    @ApiStatus.NonExtendable
    default int getRemainingExpToNextLevel() { return getExpToNextLevel() - getExp(); }

    //default formula is Exp. to Level = 10 * Level^2
    //so current level by Exp is sqrt(Exp/10)+1
    static int calculateLevelAtExp(int inputExp) { return (int) Math.sqrt(inputExp/ 10.0) + 1; }
    static int calculateExpToReachLevel(int targetLevel) { return (targetLevel-1)*(targetLevel-1) * 10; }



    /*
    * Override this for each entity's custom level up behavior.
    * */
    abstract void doLevelUp(int newLevel);
}
