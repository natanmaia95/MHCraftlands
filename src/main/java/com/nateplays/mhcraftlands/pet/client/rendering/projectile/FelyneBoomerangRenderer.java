package com.nateplays.mhcraftlands.pet.client.rendering.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nateplays.mhcraftlands.pet.entity.projectile.FelyneBoomerangEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FelyneBoomerangRenderer extends EntityRenderer<FelyneBoomerangEntity> {
    private final ItemRenderer itemRenderer;
    private final RandomSource random = RandomSource.create();

    public FelyneBoomerangRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    public ResourceLocation getTextureLocation(FelyneBoomerangEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

//    public void render(ItemEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
//        poseStack.pushPose();
//        ItemStack itemstack = entity.getItem();
//        this.random.setSeed((long)getSeedForItemStack(itemstack));
//        BakedModel bakedmodel = this.itemRenderer.getModel(itemstack, entity.level(), null, entity.getId());
//        boolean flag = bakedmodel.isGui3d();
//        float f = 0.25F;
//        boolean shouldBob = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(itemstack).shouldBobAsEntity(itemstack);
//        float f1 = shouldBob ? Mth.sin(((float)entity.getAge() + partialTicks) / 10.0F + entity.bobOffs) * 0.1F + 0.1F : 0;
//        float f2 = bakedmodel.getTransforms().getTransform(ItemDisplayContext.GROUND).scale.y();
//        poseStack.translate(0.0F, f1 + 0.25F * f2, 0.0F);
//        float f3 = entity.getSpin(partialTicks);
//        poseStack.mulPose(Axis.YP.rotation(f3));
//        renderMultipleFromCount(this.itemRenderer, poseStack, buffer, packedLight, itemstack, bakedmodel, flag, this.random);
//        poseStack.popPose();
//        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
//    }


    @Override
    public void render(FelyneBoomerangEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        ItemStack itemStack = p_entity.getWeaponItem();
        if (itemStack == null) itemStack = new ItemStack(Items.EMERALD);

        poseStack.pushPose();

        BakedModel bakedModel = this.itemRenderer.getModel(itemStack, p_entity.level(), null, p_entity.getId());
        float spin = p_entity.getSpin(partialTick);
        poseStack.mulPose(Axis.YP.rotation(spin));
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
        itemRenderer.render(itemStack, ItemDisplayContext.FIXED, false, poseStack, bufferSource,
                packedLight, OverlayTexture.NO_OVERLAY, bakedModel);

        poseStack.popPose();

        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
