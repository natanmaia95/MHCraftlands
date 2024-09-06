package com.nateplays.my_neoforge_mod.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nateplays.my_neoforge_mod.entity.animations.MosswineAnimations;
import com.nateplays.my_neoforge_mod.entity.custom.MosswineEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MosswineModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "mosswine"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart legfrontright;
	private final ModelPart legfrontleft;
	private final ModelPart legbackleft;
	private final ModelPart legbackright;
	private final ModelPart head;
	private final ModelPart snout;

	public MosswineModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.legfrontright = root.getChild("legfrontright");
		this.legfrontleft = root.getChild("legfrontleft");
		this.legbackleft = root.getChild("legbackleft");
		this.legbackright = root.getChild("legbackright");
		this.head = root.getChild("head");
		this.snout = head.getChild("snout");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition legfrontright = partdefinition.addOrReplaceChild("legfrontright", CubeListBuilder.create().texOffs(33, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 19.0F, -2.0F));

		PartDefinition legfrontleft = partdefinition.addOrReplaceChild("legfrontleft", CubeListBuilder.create().texOffs(33, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 19.0F, -2.0F));

		PartDefinition legbackleft = partdefinition.addOrReplaceChild("legbackleft", CubeListBuilder.create().texOffs(33, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 19.0F, 7.0F));

		PartDefinition legbackright = partdefinition.addOrReplaceChild("legbackright", CubeListBuilder.create().texOffs(33, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 19.0F, 7.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(27, 31).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -3.0F, -4.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, -3.0F, -4.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -5.0F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r1 = snout.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 21).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7467F, -5.9651F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(MosswineAnimations.walk, limbSwing, limbSwingAmount, 1f, 1f);
		this.animate(((MosswineEntity) entity).idleAnimationState, MosswineAnimations.idle_sniff, ageInTicks, 0.5f);
	}

	private void applyHeadRotation(Entity entity, float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		this.head.xRot = headPitch * (float) (Math.PI / 180.0);
	}

//	@Override
//	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		legfrontright.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		legfrontleft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		legbackleft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		legbackright.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		legfrontright.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		legfrontleft.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		legbackleft.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		legbackright.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

//	@Override
//	public ModelPart root() {
//		return body;
//	}
}