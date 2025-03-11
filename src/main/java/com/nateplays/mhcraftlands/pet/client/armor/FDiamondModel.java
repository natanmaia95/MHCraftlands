package com.nateplays.mhcraftlands.pet.client.armor;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.client.animation.PalicoAnimations;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FDiamondModel<T extends PalicoEntity> extends PalicoModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "f_diamond_layer"), "main");

	private final ModelPart cape;
	private final ModelPart armorlegl;
	private final ModelPart armorlegr;

	public FDiamondModel(ModelPart root) {
		super(root);
		this.cape = this.armortorso.getChild("cape");
		this.armorlegl = this.legl.getChild("armorlegl");
		this.armorlegr = this.legr.getChild("armorlegr");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition armorbody = body.addOrReplaceChild("armorbody", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, -5.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(46, 0).addBox(-2.5F, -6.25F, -1.5F, 5.0F, 7.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cape = armortorso.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(50, 12).addBox(-3.5F, -1.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.75F, 2.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(8, 0).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create().texOffs(38, 7).mirror().addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create(), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create(), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -5.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-3.0F, -5.5F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 49).addBox(-2.5F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition cube_r1 = armorhead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 58).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.0F, 1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = armorhead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 58).mirror().addBox(0.0F, -6.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, -2.0F, 1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition armorlegl = legl.addOrReplaceChild("armorlegl", CubeListBuilder.create(), PartPose.offset(1.0F, -1.0F, -0.5F));

		PartDefinition cube_r3 = armorlegl.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(55, 21).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition armorlegr = legr.addOrReplaceChild("armorlegr", CubeListBuilder.create(), PartPose.offset(-1.0F, -1.0F, -0.5F));

		PartDefinition cube_r4 = armorlegr.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(55, 21).mirror().addBox(-0.5F, -0.5F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(PalicoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entity.isKOed() && !entity.isInSittingPose()) {
			this.animateWalk(FDiamondAnimations.walk_armor, limbSwing*2, limbSwingAmount, 1f, 1f);
		}
	}
}