package com.nateplays.mhcraftlands.pet.client.rendering;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.client.animation.PalicoAnimations;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

public class PalicoModel<T extends PalicoEntity> extends HierarchicalModel<T> implements ArmedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "palico"), "main");
	public final ModelPart root;
	public final ModelPart body;
	public final ModelPart armorbody;
	public final ModelPart torso;
	public final ModelPart armortorso;
	public final ModelPart arml;
	public final ModelPart armorarml;
	public final ModelPart armr;
	public final ModelPart armorarmr;
	public final ModelPart neck;
	public final ModelPart head;
	public final ModelPart earr;
	public final ModelPart earl;
	public final ModelPart armorhead;
	public final ModelPart tail;
	public final ModelPart legl;
	public final ModelPart legr;

	public PalicoModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.armorbody = this.body.getChild("armorbody");
		this.torso = this.body.getChild("torso");
		this.armortorso = this.torso.getChild("armortorso");
		this.arml = this.torso.getChild("arml");
		this.armorarml = this.arml.getChild("armorarml");
		this.armr = this.torso.getChild("armr");
		this.armorarmr = this.armr.getChild("armorarmr");
		this.neck = this.torso.getChild("neck");
		this.head = this.neck.getChild("head");
		this.earr = this.head.getChild("earr");
		this.earl = this.head.getChild("earl");
		this.armorhead = this.head.getChild("armorhead");
		this.tail = this.body.getChild("tail");
		this.legl = this.body.getChild("legl");
		this.legr = this.body.getChild("legr");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition armorbody = body.addOrReplaceChild("armorbody", CubeListBuilder.create().texOffs(42, 11).addBox(-3.0F, -1.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
				.texOffs(0, 17).addBox(-2.0F, -5.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(46, 0).addBox(-2.5F, -6.25F, -1.5F, 5.0F, 7.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(8, 0).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create().texOffs(38, 7).mirror().addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(11, 17).addBox(-1.5F, 0.0F, -3.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(0, 8).addBox(-3.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).mirror().addBox(1.25F, -0.5F, -3.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create().texOffs(26, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create().texOffs(20, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -5.5F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.1F))
				.texOffs(0, 49).addBox(-2.5F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
				.texOffs(6, 49).mirror().addBox(0.5F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(11, 23).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F))
				.texOffs(14, 25).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(16, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
				.texOffs(24, 0).addBox(-1.0F, 1.25F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(24, 5).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
				.texOffs(16, 0).addBox(-1.0F, 1.25F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(PalicoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		if (entity.isInSittingPose()) {
			if (entity.sitAnimationState.getAccumulatedTime() < 1000) this.animate(entity.sitAnimationState, PalicoAnimations.sit, ageInTicks);
			else this.animate(entity.sitAnimationState, PalicoAnimations.sitting, ageInTicks, 1.0F);
		} else {
			if (entity.standUpAnimationState.getAccumulatedTime() < 2000) this.animate(entity.standUpAnimationState, PalicoAnimations.jump_excited, ageInTicks);
			this.animateWalk(PalicoAnimations.walk, limbSwing*2, limbSwingAmount, 1f, 1f);
		}

	}

	private void applyHeadRotation(Entity entity, float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		this.head.xRot = headPitch * (float) (Math.PI / 180.0);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//		torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//		neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//		legl.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//		legr.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void translateToHand(HumanoidArm side, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		this.torso.translateAndRotate(poseStack);
		this.getArm(side).translateAndRotate(poseStack);
	}

	public ModelPart getArm(HumanoidArm side) {
		if (side == HumanoidArm.LEFT) return this.arml;
		else return this.armr;
	}
}