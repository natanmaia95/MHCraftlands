package com.nateplays.mhcraftlands.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.custom.MosswineEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MosswineRenderer extends MobRenderer<MosswineEntity, MosswineModel<MosswineEntity>> {

    public MosswineRenderer(EntityRendererProvider.Context context) {
        super(context, new MosswineModel<>(context.bakeLayer(ModModelLayers.MOSSWINE_LAYER)), 0.8f);
        this.addLayer(new MosswineGoldLayer<>(this, context));
        this.addLayer(new ItemInHandLayer<MosswineEntity, MosswineModel<MosswineEntity>>(this, context.getItemInHandRenderer()) {
            @Override
            protected void renderArmWithItem(
                    LivingEntity livingEntity,
                    ItemStack itemStack,
                    ItemDisplayContext displayContext,
                    HumanoidArm arm,
                    PoseStack poseStack,
                    MultiBufferSource buffer,
                    int packedLight
            ) {
                // Modify the pose stack to offset the item as needed
                poseStack.pushPose();

                poseStack.mulPose(Axis.XP.rotation(-0.2f));
                // Apply your custom offset
                if (arm == HumanoidArm.RIGHT) {
                    poseStack.translate(0.1F, -0.3F, 0.3F);  // Adjust these values to your needs
                } else {
                    poseStack.translate(-0.1F, -0.3F, 0.3F); // Adjust values for offhand if necessary
                }

                // Call the parent class to render the item with the custom offset
                super.renderArmWithItem(livingEntity, itemStack, displayContext, arm, poseStack, buffer, packedLight);

                poseStack.popPose();
            }
        }); //add default
    }

    /**
     * Returns the location of an entity's texture.
     *
     * @param entity
     */
    @Override
    public ResourceLocation getTextureLocation(MosswineEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/entity/mosswine.png");
    }

    @Override
    public void render(MosswineEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        //add custom hand renderer!
//        if (!entity.getMainHandItem().isEmpty()) {
//            poseStack.pushPose();
//
//            // Position the item in the mob's hand
//            //poseStack.translate(0.0F, 0.0F, 0.0F);  // Adjust the position as needed
//            this.getModel().translateToHand(HumanoidArm.RIGHT, poseStack);
//
//            Minecraft.getInstance().getItemRenderer().renderStatic(
//                    entity.getMainHandItem(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
//                    packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), entity.getId());
//            poseStack.popPose();
//        }
    }
}
