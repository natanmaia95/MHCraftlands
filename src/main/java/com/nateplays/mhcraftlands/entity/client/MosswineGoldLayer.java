package com.nateplays.mhcraftlands.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.custom.MosswineEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MosswineGoldLayer<T extends MosswineEntity, M extends MosswineModel<T>> extends RenderLayer<T,M> {
    private final MosswineModel<T> armorModel;
//    ResourceLocation armorTexture = ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/entity/mosswine_head_gold.png");

    public MosswineGoldLayer(RenderLayerParent<T, M> renderer, EntityRendererProvider.Context context) {
        super(renderer);
        this.armorModel = new MosswineModel<>(context.bakeLayer(ModModelLayers.MOSSWINE_GOLD_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isInvisible() && livingEntity.isGoldHead()) {
            this.getParentModel().copyPropertiesTo(armorModel);
            copyParts(this.getParentModel(), armorModel);
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation()));
            armorModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }
    }

    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/entity/mosswine_head_gold.png");
    }

    private void copyParts(MosswineModel<T> from, MosswineModel<T> to) {
        copyModelPart(from.root(), to.root());
        copyModelPart(from.root().getChild("body"), to.root().getChild("body"));
        copyModelPart(from.root().getChild("head"), to.root().getChild("head"));

        copyModelPart(from.root().getChild("head").getChild("snout"),
                to.root().getChild("head").getChild("snout"));
    }

    private void copyModelPart(ModelPart from, ModelPart to) {
//        to.xRot = from.xRot;
//        to.yRot = from.yRot;
//        to.zRot = from.zRot;
//
//        to.x = from.x;
//        to.y = from.y;
//        to.z = from.z;
//
//        to.xScale = from.xScale;
//        to.yScale = from.yScale;
//        to.zScale = from.zScale;

//        to.loadPose(from.storePose());
        to.copyFrom(from);
    }
}
