package com.nateplays.mhcraftlands.hunter.armor.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.client.rendering.BaseHuntingArmorModel;
import com.nateplays.mhcraftlands.hunter.armor.PlayerHuntingArmorItem;
import com.nateplays.mhcraftlands.mixin.ArmorMaterialLayerMixin;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class PlayerHuntingArmorLayer <T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
//    private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
    private final A baseArmorModel;
    private final EntityRendererProvider.Context context;

    public PlayerHuntingArmorLayer(RenderLayerParent<T, M> renderer, EntityRendererProvider.Context context) {
        super(renderer);
        this.baseArmorModel = null;
        this.context = context;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderArmorPiece(poseStack, buffer, livingEntity, EquipmentSlot.CHEST, packedLight);
        this.renderArmorPiece(poseStack, buffer, livingEntity, EquipmentSlot.LEGS, packedLight);
        this.renderArmorPiece(poseStack, buffer, livingEntity, EquipmentSlot.FEET, packedLight);
        this.renderArmorPiece(poseStack, buffer, livingEntity, EquipmentSlot.HEAD, packedLight);
    }

    private void renderArmorPiece(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot, int packedLight) {
        ItemStack itemstack = livingEntity.getItemBySlot(slot);
        if (itemstack.getItem() instanceof PlayerHuntingArmorItem<?,?> armorItem) {
            if (armorItem.getEquipmentSlot() != slot) return;

            A p_model = this.getArmorModel(livingEntity, armorItem, slot); //TODO: get model from armorItem

            this.getParentModel().copyPropertiesTo(p_model);
            this.copyProperties(getParentModel(), p_model);
            if (p_model.getClass() == BaseHuntingArmorModel.class) this.setPartVisibility(p_model, slot);

            ArmorMaterial armorMaterial = armorItem.getMaterial().value();

            int probableDyeColor = DyedItemColor.getOrDefault(itemstack, -1);
            if (itemstack.is(ItemTags.DYEABLE) && probableDyeColor != -1) {
                probableDyeColor = FastColor.ARGB32.opaque(probableDyeColor);
            }

            for (ArmorMaterial.Layer armorLayer : armorMaterial.layers()) {
                //dont render color layer if doesn't have color component
                if (armorLayer.dyeable() && probableDyeColor == -1) continue;
                int dyeColor = armorLayer.dyeable() ? probableDyeColor : -1;

                ResourceLocation layerTexture;
                String suffix = ((ArmorMaterialLayerMixin) (Object) armorLayer).getSuffix();

                if (p_model.getClass() == BaseHuntingArmorModel.class) {
                    layerTexture = armorLayer.texture(this.usesInnerModel(slot));
                } else { //subclasses
                    String assetName = ((ArmorMaterialLayerMixin) (Object) armorLayer).getAssetName().getPath();
                    String namespace = ((ArmorMaterialLayerMixin) (Object) armorLayer).getAssetName().getNamespace();
                    layerTexture = ResourceLocation.fromNamespaceAndPath(namespace, "textures/models/armor/" + assetName + suffix + ".png");
                }
                // = ((ArmorMaterialLayerMixin) (Object) armorLayer).resolveSingleTexture(MHMod.MOD_ID);

                RenderType renderType = RenderType.armorCutoutNoCull(layerTexture);
                this.renderModel(poseStack, bufferSource, packedLight, p_model, dyeColor, layerTexture, renderType);
            }

            if (itemstack.hasFoil()) {
                this.renderGlint(poseStack, bufferSource, packedLight, p_model);
            }
        }
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, A model, int dyeColor, ResourceLocation textureLocation, RenderType renderType) {
        this.renderModel(poseStack, bufferSource, packedLight, (Model)model, dyeColor, textureLocation, renderType);
    }

    private void renderModel(PoseStack p_289664_, MultiBufferSource p_289689_, int p_289681_, Model p_289658_, int p_350798_, ResourceLocation p_324344_, RenderType renderType) {
        VertexConsumer vertexconsumer = p_289689_.getBuffer(renderType);
        p_289658_.renderToBuffer(p_289664_, vertexconsumer, p_289681_, OverlayTexture.NO_OVERLAY, p_350798_);
    }

    private void renderGlint(PoseStack p_289673_, MultiBufferSource p_289654_, int p_289649_, Model p_289659_) {
        p_289659_.renderToBuffer(p_289673_, p_289654_.getBuffer(RenderType.armorEntityGlint()), p_289649_, OverlayTexture.NO_OVERLAY);
    }


//    private A getArmorModel(EquipmentSlot slot) {
//        return this.baseArmorModel;
//    }

    private boolean usesInnerModel(EquipmentSlot slot) {
        return slot == EquipmentSlot.LEGS;
    }

    private A getArmorModel(T entity, PlayerHuntingArmorItem<?,?> armorItem, EquipmentSlot slot) {
        A armorModel = (A) armorItem.getArmorModel(this.context);
        if (armorModel == null) return baseArmorModel;
        return armorModel;
    }

    private void copyProperties(M from, A to) {
        from.copyPropertiesTo(to);
    }

    private void setPartVisibility(A model, EquipmentSlot slot) {
        //TODO: ask armorItem for which parts should be visible.
        model.setAllVisible(false);
        switch (slot) {
            case HEAD:
                model.head.visible = true;
                model.hat.visible = true;
                break;
            case CHEST:
                model.body.visible = true;
                model.rightArm.visible = true;
                model.leftArm.visible = true;
                break;
            case LEGS:
                model.body.visible = true;
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
                break;
            case FEET:
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
        }
    }
}
