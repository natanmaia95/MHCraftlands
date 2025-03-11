package com.nateplays.mhcraftlands.pet.client.armor;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FDuffelModel<T extends PalicoEntity> extends PalicoModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "f_duffel_layer"), "main");


	public FDuffelModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition armorbody = body.addOrReplaceChild("armorbody", CubeListBuilder.create().texOffs(42, 11).addBox(-3.0F, -1.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 17).addBox(-2.0F, -5.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(46, 0).addBox(-2.5F, -6.25F, -1.5F, 5.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(42, 25).addBox(-3.0F, -7.0F, -1.75F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.1F))
		.texOffs(36, 24).addBox(-1.5F, -5.0F, 2.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = armortorso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 34).addBox(-0.5F, -1.25F, -3.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 4.25F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r2 = armortorso.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 34).addBox(-1.5F, -1.25F, -2.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 3.75F, -0.2618F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(8, 0).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create().texOffs(38, 7).mirror().addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(30, 17).addBox(-0.75F, 1.75F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 17).addBox(-2.25F, 1.75F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(11, 17).addBox(-1.5F, 0.0F, -3.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 8).addBox(-3.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).mirror().addBox(1.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create().texOffs(26, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create().texOffs(20, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -5.5F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.2F))
		.texOffs(24, 52).addBox(-3.0F, -7.5F, -2.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition cube_r3 = armorhead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = armorhead.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 48).addBox(-2.0F, 0.0F, -5.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.5F, -4.75F, 0.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(16, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(24, 0).addBox(-1.0F, 1.25F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(56, 21).addBox(-1.0F, 1.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(24, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(16, 0).addBox(-1.0F, 1.25F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(48, 21).addBox(-1.0F, 1.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}