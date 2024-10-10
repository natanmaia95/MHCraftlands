package com.nateplays.my_neoforge_mod.entity.client;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MOSSWINE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "mosswine_layer"), "main");

    public static final ModelLayerLocation MOSSWINE_GOLD_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "mosswine_gold_layer"), "main");

    public static final ModelLayerLocation PALICO_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "palico_layer"), "main");

    public static final ModelLayerLocation PALICO_ARMOR_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "palico_armor_layer"), "main");
    public static final ModelLayerLocation F_BONE_HELM_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "f_bone_helm_layer"), "main");
    public static final ModelLayerLocation F_GHOST_HELM_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "f_ghost_helm_layer"), "main");
    public static final ModelLayerLocation F_MOSGHARL_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "f_mosgharl_layer"), "main");
}
