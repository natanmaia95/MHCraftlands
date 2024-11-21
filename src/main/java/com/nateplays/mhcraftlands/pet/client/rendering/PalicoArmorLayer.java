package com.nateplays.mhcraftlands.pet.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.armor.PetHuntingArmorItem;
import com.nateplays.mhcraftlands.mixin.ArmorMaterialLayerAccessor;
import net.minecraft.client.model.geom.ModelPart;
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
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.slf4j.Logger;

import java.util.Objects;


//public class PalicoArmorLayer<T extends PalicoEntity, M extends PalicoModel<T>, A extends PalicoModel<T>> extends RenderLayer<T, M> {

@OnlyIn(Dist.CLIENT)
public class PalicoArmorLayer<T extends PalicoEntity, M extends PalicoModel<T>, A extends PalicoModel<T>> extends RenderLayer<T, M> {
    private final A baseArmorModel;
    private final EntityRendererProvider.Context context;

    private static final Logger LOGGER = LogUtils.getLogger();

//    public PalicoArmorLayer(RenderLayerParent<T, M> renderer, A armorModel, EntityRendererProvider.Context context) {
    public PalicoArmorLayer(RenderLayerParent<T, M> renderer, A armorModel, EntityRendererProvider.Context context) {
        super(renderer);
//        this.baseArmorModel = new PalicoModel<>(context.bakeLayer(ModModelLayers.PALICO_LAYER));
        this.baseArmorModel = armorModel;
        this.context = context;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        resetPartVisibility();
        this.renderArmorPiece(poseStack, bufferSource, livingEntity, EquipmentSlot.CHEST, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch, this.getArmorModel(livingEntity, EquipmentSlot.CHEST));
        this.renderArmorPiece(poseStack, bufferSource, livingEntity, EquipmentSlot.HEAD, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch, this.getArmorModel(livingEntity, EquipmentSlot.HEAD));
    }

    private void resetPartVisibility() {
        PalicoModel<T> palicoModel = this.getParentModel();
        palicoModel.legl.visible = true;
        palicoModel.legr.visible = true;
        palicoModel.arml.visible = true;
        palicoModel.armr.visible = true;
        palicoModel.earl.visible = true;
        palicoModel.earr.visible = true;
        palicoModel.head.visible = true;
    }

    private A getArmorModel(LivingEntity livingEntity, EquipmentSlot slot) {
//        return this.usesInnerModel(slot) ? this.innerModel : this.outerModel;
        //return custom model

        ItemStack itemStack = livingEntity.getItemBySlot(slot);
//        LOGGER.debug("a");
        if (itemStack.isEmpty()) return baseArmorModel;
//        LOGGER.debug("b");
        if (itemStack.getItem() instanceof PetHuntingArmorItem armorItem) {
//            LOGGER.debug("c");
            A armorModel = (A) armorItem.getArmorModel(this.context);
            if (armorModel == null) return baseArmorModel;
//            LOGGER.debug("d");
            return armorModel;
        }
//        LOGGER.debug("e");
        return baseArmorModel;
    }

    private void renderArmorPiece(
            PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity,
            EquipmentSlot slot, int packedLight,
            float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch,
            A p_model
    ) {
        ItemStack itemstack = livingEntity.getItemBySlot(slot);
        if (itemstack.getItem() instanceof PetHuntingArmorItem armorItem) {
            if (armorItem.getEquipmentSlot() != slot) return; //if incorrect slot, return
            this.getParentModel().copyPropertiesTo(p_model);
            this.copyParts(getParentModel(), p_model);
            p_model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//            LOGGER.debug(p_model.toString());
            //TODO: change to custom material class for pet armor
            ArmorMaterial armormaterial = armorItem.getMaterial().value();
            int probableDyeColor = DyedItemColor.getOrDefault(itemstack, -1);;// itemstack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(itemstack, -6265536)) : -1;
            if (itemstack.is(ItemTags.DYEABLE) && probableDyeColor != -1) {
                probableDyeColor = FastColor.ARGB32.opaque(probableDyeColor);
            }

            for (ArmorMaterial.Layer armorLayer : armormaterial.layers()) {
                int finalPackedLight = packedLight;

                //dont render color layer if doesn't have color component
                if (armorLayer.dyeable() && probableDyeColor == -1) continue;
                int dyeColor = armorLayer.dyeable() ? probableDyeColor : -1;

                ResourceLocation layerTexture = null;
                if (slot == EquipmentSlot.HEAD) layerTexture = armorLayer.texture(false);
                if (slot == EquipmentSlot.CHEST) layerTexture = armorLayer.texture(true);
                if (layerTexture == null) continue;

                RenderType renderType = RenderType.entityCutoutNoCull(layerTexture);
                String suffix = ((ArmorMaterialLayerAccessor) (Object) armorLayer).suffix();
                if (Objects.equals(suffix, "_emissive")) {
                    renderType = RenderType.eyes(layerTexture);
//                    LOGGER.debug("ligjt:" + String.valueOf(packedLight));
                }

                this.renderModel(poseStack, bufferSource, finalPackedLight, p_model, dyeColor, layerTexture, renderType);
            }

            //remove body parts
//            PalicoModel<T> palicoModel = this.getParentModel();
//            palicoModel.legl.visible = !(!palicoModel.legl.visible || armorItem.hideLegs());
//            palicoModel.legr.visible = !(!palicoModel.legr.visible || armorItem.hideLegs());
//            palicoModel.arml.visible = !(!palicoModel.arml.visible || armorItem.hideArms());
//            palicoModel.armr.visible = !(!palicoModel.armr.visible || armorItem.hideArms());
//            palicoModel.earl.visible = !(!palicoModel.earl.visible || armorItem.hideEars());
//            palicoModel.earr.visible = !(!palicoModel.earr.visible || armorItem.hideEars());
//            palicoModel.head.visible = !(!palicoModel.head.visible || armorItem.hideHead());
        }
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, A model, int dyeColor, ResourceLocation textureLocation, RenderType renderType) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, dyeColor);
    }

    private void copyParts(M from, A to) {
        copyModelPart(from.root(), to.root());
//        Stream<ModelPart> parts = from.root().getAllParts();
        copyModelPart(from.body, to.body);
        copyModelPart(from.torso, to.torso);
        copyModelPart(from.neck, to.neck);
        copyModelPart(from.head, to.head);
        copyModelPart(from.earr, to.earr);
        copyModelPart(from.earl, to.earl);
        copyModelPart(from.arml, to.arml);
        copyModelPart(from.armr, to.armr);
        copyModelPart(from.legl, to.legl);
        copyModelPart(from.legr, to.legr);
        copyModelPart(from.tail, to.tail);
    }

    private void copyModelPart(ModelPart from, ModelPart to) {
        to.copyFrom(from);
    }
}