package com.nateplays.mhcraftlands.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nateplays.mhcraftlands.hunter.armor.PlayerHuntingArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    private void onRenderArmorPiece(
            PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot,
            int packedLight, A model, CallbackInfo ci) {

        ItemStack itemStack = livingEntity.getItemBySlot(slot);
        if (itemStack.getItem() instanceof PlayerHuntingArmorItem) {
            ci.cancel();
        }
    }
}