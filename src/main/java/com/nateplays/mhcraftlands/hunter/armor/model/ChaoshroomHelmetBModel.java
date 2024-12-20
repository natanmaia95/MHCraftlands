package com.nateplays.mhcraftlands.hunter.armor.model;
// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.client.rendering.BaseHuntingArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class ChaoshroomHelmetBModel<T extends Player> extends BaseHuntingArmorModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "chaoshroom_helmet_b"), "main");
	private final ModelPart shroom_left;
	private final ModelPart tie_left;
	private final ModelPart shroom_right;
	private final ModelPart tie_right;

	public ChaoshroomHelmetBModel(ModelPart root) {
		super(root);
		this.shroom_left = this.head.getChild("shroom_left");
		this.tie_left = this.shroom_left.getChild("tie_left");
		this.shroom_right = this.head.getChild("shroom_right");
		this.tie_right = this.shroom_right.getChild("tie_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shroom_left = head.addOrReplaceChild("shroom_left", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.0F, 0.25F, -0.6109F, 1.5708F, 0.0F));

		PartDefinition tie_left = shroom_left.addOrReplaceChild("tie_left", CubeListBuilder.create().texOffs(16, 0).addBox(0.5F, 0.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-1.5F, 0.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition shroom_right = head.addOrReplaceChild("shroom_right", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0F, 0.25F, -0.6109F, -1.5708F, 0.0F));

		PartDefinition tie_right = shroom_right.addOrReplaceChild("tie_right", CubeListBuilder.create().texOffs(16, 0).addBox(0.5F, 0.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-1.5F, 0.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(-1.5F, -5.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
}