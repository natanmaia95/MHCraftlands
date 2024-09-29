package com.nateplays.my_neoforge_mod.entity.pets.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.client.MosswineGoldLayer;
import com.nateplays.my_neoforge_mod.entity.client.MosswineModel;
import com.nateplays.my_neoforge_mod.entity.custom.MosswineEntity;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PalicoRenderer extends MobRenderer<PalicoEntity, PalicoModel<PalicoEntity>> {
    public PalicoRenderer(EntityRendererProvider.Context context) {
        super(context, new PalicoModel<>(context.bakeLayer(ModModelLayers.PALICO_LAYER)), 0.3f);
        this.addLayer(new ItemInHandLayer<PalicoEntity, PalicoModel<PalicoEntity>>(this, context.getItemInHandRenderer()) {
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

                poseStack.scale(0.7f,0.7f,0.7f);
                float transX = -0.07f;
                float transY = 1.78f;
                float transZ = 0.21f;
                if (arm == HumanoidArm.RIGHT) {
                    poseStack.translate(transX, transY, transZ);  // Adjust these values to your needs
                } else {
                    poseStack.translate(-transX, transY, transZ); // Adjust values for offhand if necessary
                }

                // Call the parent class to render the item with the custom offset
                super.renderArmWithItem(livingEntity, itemStack, displayContext, arm, poseStack, buffer, packedLight);

                poseStack.popPose();
            }
        }); //add default
    }

    @Override
    public ResourceLocation getTextureLocation(PalicoEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/entity/pets/palico_felyne.png");
    }

    @Override
    public void render(PalicoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
