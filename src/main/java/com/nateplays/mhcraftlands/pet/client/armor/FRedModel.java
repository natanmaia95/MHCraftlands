package com.nateplays.mhcraftlands.pet.client.armor;// Made with Blockbench 4.11.2
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

public class FRedModel<T extends PalicoEntity> extends PalicoModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "f_red_layer"), "main");

	public FRedModel(ModelPart root) {
		super(root);
//		init new parts here
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition armorbody = body.addOrReplaceChild("armorbody", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 17).addBox(-2.0F, -5.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r1 = torso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 21).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.25F, -1.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r2 = torso.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(25, 19).addBox(-1.5F, -1.5F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.75F, -1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r3 = torso.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(17, 19).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -4.5F, -1.25F, 0.0F, 0.0F, 0.7854F));

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(38, 21).addBox(-4.0F, -5.5F, -1.5F, 8.0F, 7.0F, 5.0F, new CubeDeformation(-0.1F))
		.texOffs(33, 19).addBox(-1.5F, -2.25F, 1.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(8, 0).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create().texOffs(38, 7).mirror().addBox(-0.25F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(11, 17).addBox(-1.5F, 0.0F, -3.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 8).addBox(-3.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).mirror().addBox(1.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create().texOffs(26, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create().texOffs(20, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -5.5F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.2F))
		.texOffs(0, 49).addBox(-2.5F, -7.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
		.texOffs(6, 49).mirror().addBox(0.5F, -7.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(16, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(24, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}