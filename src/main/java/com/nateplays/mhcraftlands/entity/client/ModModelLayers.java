package com.nateplays.mhcraftlands.entity.client;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MOSSWINE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "mosswine_layer"), "main");

    public static final ModelLayerLocation MOSSWINE_GOLD_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "mosswine_gold_layer"), "main");
}
