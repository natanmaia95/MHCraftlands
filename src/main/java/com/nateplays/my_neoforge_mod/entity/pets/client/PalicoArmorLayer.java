package com.nateplays.my_neoforge_mod.entity.pets.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.client.MosswineModel;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.item.armor.PetHuntingArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


//public class PalicoArmorLayer<T extends PalicoEntity, M extends PalicoModel<T>, A extends PalicoModel<T>> extends RenderLayer<T, M> {

@OnlyIn(Dist.CLIENT)
public class PalicoArmorLayer<T extends PalicoEntity, M extends PalicoModel<T>, A extends PalicoModel<T>> extends RenderLayer<T, M> {
    private final A baseArmorModel;

//    public PalicoArmorLayer(RenderLayerParent<T, M> renderer, A armorModel, EntityRendererProvider.Context context) {
    public PalicoArmorLayer(RenderLayerParent<T, M> renderer, A armorModel, EntityRendererProvider.Context context) {
        super(renderer);
//        this.baseArmorModel = new PalicoModel<>(context.bakeLayer(ModModelLayers.PALICO_LAYER));
        this.baseArmorModel = armorModel;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderArmorPiece(poseStack, bufferSource, livingEntity, EquipmentSlot.HEAD, packedLight, baseArmorModel);
        this.renderArmorPiece(poseStack, bufferSource, livingEntity, EquipmentSlot.CHEST, packedLight, baseArmorModel);
    }

//    private A getArmorModel(EquipmentSlot slot) {
////        return this.usesInnerModel(slot) ? this.innerModel : this.outerModel;
//        //return custom model
//        return baseArmorModel;
//    }

    private void renderArmorPiece(
            PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity,
            EquipmentSlot slot, int packedLight, A p_model
    ) {
        ItemStack itemstack = livingEntity.getItemBySlot(slot);
        if (itemstack.getItem() instanceof PetHuntingArmorItem armoritem) {
            if (armoritem.getEquipmentSlot() != slot) return; //if incorrect slot, return
            this.getParentModel().copyPropertiesTo(p_model);
            this.copyParts(getParentModel(), p_model);

            //TODO: change to custom material class for pet armor
            ArmorMaterial armormaterial = armoritem.getMaterial().value();
            int probableDyeColor = itemstack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(itemstack, -6265536)) : -1;

            for (ArmorMaterial.Layer armorlayer : armormaterial.layers()) {
                int dyeColor = armorlayer.dyeable() ? probableDyeColor : -1;
                ResourceLocation layerTexture = null;
                if (slot == EquipmentSlot.HEAD) layerTexture = armorlayer.texture(false);
                if (slot == EquipmentSlot.CHEST) layerTexture = armorlayer.texture(true);

                if (layerTexture != null) this.renderModel(poseStack, bufferSource, packedLight, p_model, dyeColor, layerTexture);
            }
        }
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, A model, int dyeColor, ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(textureLocation));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, dyeColor);
    }

    private void copyParts(M from, A to) {
        copyModelPart(from.root(), to.root());
        copyModelPart(from.body, to.body);
        copyModelPart(from.torso, to.torso);
        copyModelPart(from.neck, to.neck);
        copyModelPart(from.head, to.head);
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