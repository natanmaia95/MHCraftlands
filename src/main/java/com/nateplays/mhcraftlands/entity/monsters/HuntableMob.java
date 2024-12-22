package com.nateplays.mhcraftlands.entity.monsters;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public abstract class HuntableMob extends Mob {
    protected HuntableMob(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }
}
