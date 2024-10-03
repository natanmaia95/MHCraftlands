package com.nateplays.my_neoforge_mod.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nateplays.my_neoforge_mod.event.LivingRenderHandItemEvent;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public abstract class ItemInHandLayerMixin<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> {

    @Shadow
    private ItemInHandRenderer itemInHandRenderer;

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void onRenderArmWithItem(LivingEntity entity, ItemStack itemStack, ItemDisplayContext displayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        // Fire custom event to allow item modification
        LivingRenderHandItemEvent event = new LivingRenderHandItemEvent(entity, itemStack, arm);
        NeoForge.EVENT_BUS.post(event);

        // If the event has modified the item, render the modified item instead
        if (event.getModifiedItemStack() != event.getOriginalItemStack()) {
            ItemStack modifiedItemStack = event.getModifiedItemStack();
            if (modifiedItemStack != null && !modifiedItemStack.isEmpty()) {
                // Render the modified item stack
                poseStack.pushPose();
                ItemInHandLayer layer = (ItemInHandLayer<T, M>) (Object) this;
                ((ArmedModel) layer.getParentModel()).translateToHand(arm, poseStack);
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                boolean flag = arm == HumanoidArm.LEFT;
                poseStack.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
                this.itemInHandRenderer.renderItem(entity, modifiedItemStack, displayContext, flag, poseStack, buffer, packedLight);
                poseStack.popPose();
                ci.cancel(); // Cancel further processing in the original method
            }
        }
    }
}