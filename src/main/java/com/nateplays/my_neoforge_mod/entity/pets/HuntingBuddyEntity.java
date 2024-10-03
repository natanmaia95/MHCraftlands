package com.nateplays.my_neoforge_mod.entity.pets;

import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class HuntingBuddyEntity extends TamableAnimal implements ILevelableEntity {

    protected HuntingBuddyEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }
}
