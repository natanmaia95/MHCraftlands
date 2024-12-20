package com.nateplays.mhcraftlands.common.client.rendering;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class BaseHuntingArmorModel<T extends LivingEntity> extends HumanoidModel<T> {

    //IMPLEMENT A "LAYER_LOCATION" STATIC FINAL VARIABLE IN EVERY SUBCLASS.

    public static final ModelLayerLocation DEFAULT_HUNTING_ARMOR_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "default_hunting_armor"), "main");

    public BaseHuntingArmorModel(ModelPart root) {
        super(root);
    }
}
