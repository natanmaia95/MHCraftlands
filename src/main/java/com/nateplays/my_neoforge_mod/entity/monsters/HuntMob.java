package com.nateplays.my_neoforge_mod.entity.monsters;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public abstract class HuntMob extends Mob {
    protected HuntMob(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }
}
