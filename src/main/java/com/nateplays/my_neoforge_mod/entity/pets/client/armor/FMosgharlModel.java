package com.nateplays.my_neoforge_mod.entity.pets.client.armor;
// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FMosgharlModel<T extends PalicoEntity> extends PalicoModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "f_ghost_helm"), "main");

	public FMosgharlModel(ModelPart root) {
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

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(40, 21).addBox(-3.0F, -5.25F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(32, 33).addBox(-4.0F, -5.5F, -3.5F, 8.0F, 1.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create(), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create(), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -5.5F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.1F))
				.texOffs(40, 60).addBox(-0.5F, -8.5F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition cube_r1 = armorhead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 8).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = armorhead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 56).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.25F, -0.7854F, -0.7854F, 1.5708F));

		PartDefinition cube_r3 = armorhead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 56).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -5.5F, 0.5F, 0.3491F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}