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
        this.addLayer(new PalicoArmorLayer<>(
                this, new PalicoModel<>(context.bakeLayer(ModModelLayers.PALICO_LAYER)), context));
        this.addLayer(new PalicoItemInHandLayer<>(this, context.getItemInHandRenderer())); //add default
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
