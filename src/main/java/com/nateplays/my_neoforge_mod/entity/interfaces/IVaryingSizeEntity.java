package com.nateplays.my_neoforge_mod.entity.interfaces;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.ApiStatus;

public interface IVaryingSizeEntity {

    //Scale will only affect the _base_ scale attribute. Modifiers (from potions, etc) will be added on top.
    public enum CrownType {
        NONE, SMALL_SILVER, LARGE_SILVER, SMALL_GOLD, LARGE_GOLD
    }

    //Override these!!
    default double getMinScale() {return 0.9;}
    default double getMaxScale() {return 1.1;}


    @ApiStatus.NonExtendable
    default void setScale(double value) {
        AttributeInstance scaleAttr = ((LivingEntity) this).getAttribute(Attributes.SCALE);
        if (scaleAttr == null) return;
        scaleAttr.setBaseValue(value);
    }

    default void makeSizeToSpawn() {
        this.setScale(rollScale());
    }




    default double rollScale() {
        RandomSource randomSource = RandomSource.create();
        float exactEdgeProbability = 0.1f;
        double min = getMinScale(); double max = getMaxScale();

        float randomValue = randomSource.nextFloat();

        if (randomValue < exactEdgeProbability/2.0) {
            return min;
        } else if (randomValue < exactEdgeProbability) {
            return max;
        } else {
            return min + (max-min)*randomSource.nextFloat();
        }
    }



    // Use these for user interfaces
    default double getDefaultDisplaySize() {
        return ((LivingEntity)this).getType().getDimensions().width();
    }
    default double getDisplaySize() {
        return getDefaultDisplaySize()* ((LivingEntity)this).getScale();
    }
    default double getMinDisplaySize() {
        return getDefaultDisplaySize()*getMinScale();
    }
    default double getMaxDisplaySize() {
        return getDefaultDisplaySize()*getMaxScale();
    }

    default CrownType getCrownType() {
        double scale = ((LivingEntity)this).getScale();
        double min = getMinScale(); double max = getMaxScale();

        if (scale == min) return CrownType.SMALL_SILVER;
        if (scale == max) return CrownType.LARGE_SILVER;

        if (scale < min) return CrownType.SMALL_GOLD;
        else if (scale > max) return CrownType.LARGE_GOLD;
        else return CrownType.NONE;
    }
}
