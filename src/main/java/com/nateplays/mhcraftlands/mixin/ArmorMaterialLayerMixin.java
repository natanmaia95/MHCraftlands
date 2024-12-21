package com.nateplays.mhcraftlands.mixin;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ArmorMaterial.Layer.class)
public interface ArmorMaterialLayerMixin {

    @Accessor("assetName")
    public abstract ResourceLocation getAssetName();

    @Accessor("suffix")
    public abstract String getSuffix();

//    @Shadow
//    private ResourceLocation assetName; // Shadow the private assetName field from the target class
//
//    @Shadow
//    private String suffix; // Shadow the private suffix field from the target class

    // Custom method to simulate resolveTexture without the "layer_1" or "layer_2" part
//    public ResourceLocation resolveSingleTexture(String namespace) {
////        ArmorMaterial.Layer layer = (ArmorMaterial.Layer) (Object) this;
////        ResourceLocation assetName = ((ArmorMaterialLayerMixin) (Object) layer).getAssetName();
////        String path = assetName.getPath() + "_" + ((ArmorMaterialLayerMixin) (Object) layer).getSuffix(); // Add suffix if applicable
//
//        String path = assetName.getPath() + "_" + suffix;
//        return ResourceLocation.fromNamespaceAndPath(namespace, "textures/models/armor/" + path + ".png");
//    }
}
