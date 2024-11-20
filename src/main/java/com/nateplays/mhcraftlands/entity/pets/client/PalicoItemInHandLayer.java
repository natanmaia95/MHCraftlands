package com.nateplays.mhcraftlands.entity.pets.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PalicoItemInHandLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends ItemInHandLayer<T, M> {
    private final ItemInHandRenderer itemInHandRenderer;

    public PalicoItemInHandLayer(RenderLayerParent<T, M> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer, itemInHandRenderer);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    protected void renderArmWithItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext displayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
//        super.renderArmWithItem(livingEntity, itemStack, displayContext, arm, poseStack, buffer, packedLight);
        if (!itemStack.isEmpty()) {
            poseStack.pushPose();
            ((ArmedModel)this.getParentModel()).translateToHand(arm, poseStack);
//            PalicoModel palicoModel = ((PalicoModel)this.getParentModel());
//            palicoModel.arml.visible = false;
//            palicoModel.armr.visible = false;

            poseStack.scale(0.6f, 0.6f, 0.6f);
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            boolean flag = arm == HumanoidArm.LEFT;
            poseStack.translate((float)(flag ? -1 : 1) / 16.0F, 0.08F, -0.425F);
            this.itemInHandRenderer.renderItem(livingEntity, itemStack, displayContext, flag, poseStack, buffer, packedLight);
            poseStack.popPose();
        }
    }
}
