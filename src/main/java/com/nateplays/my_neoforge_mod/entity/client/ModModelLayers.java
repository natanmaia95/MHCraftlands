package com.nateplays.my_neoforge_mod.entity.client;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MOSSWINE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "mosswine_layer"), "main");

    public static final ModelLayerLocation MOSSWINE_GOLD_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "mosswine_gold_layer"), "main");
}
