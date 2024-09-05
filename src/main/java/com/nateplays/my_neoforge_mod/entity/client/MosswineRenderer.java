package com.nateplays.my_neoforge_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.custom.MosswineEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MosswineRenderer extends MobRenderer<MosswineEntity, MosswineModel<MosswineEntity>> {

    public MosswineRenderer(EntityRendererProvider.Context context) {
        super(context, new MosswineModel<>(context.bakeLayer(ModModelLayers.MOSSWINE_LAYER)), 0.8f);
    }

    /**
     * Returns the location of an entity's texture.
     *
     * @param entity
     */
    @Override
    public ResourceLocation getTextureLocation(MosswineEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/entity/mosswine.png");
    }

    @Override
    public void render(MosswineEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
