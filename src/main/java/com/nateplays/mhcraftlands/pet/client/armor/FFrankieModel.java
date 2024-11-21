package com.nateplays.mhcraftlands.pet.client.armor;
// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;

public class FFrankieModel<T extends PalicoEntity> extends PalicoModel<T> {
	private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "f_frankie_layer"), "main");

	private final ModelPart jaw;
	private final ModelPart screws;
	public final AnimationState jawAnimation = new AnimationState();

	public FFrankieModel(ModelPart root) {
		super(root);
//		init new parts here
		this.jaw = this.armorhead.getChild("jaw");
		this.screws = this.armorhead.getChild("screws");
		this.jawAnimation.start(0);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition armorbody = body.addOrReplaceChild("armorbody", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
				.texOffs(15, 16).addBox(-2.0F, -5.5F, -1.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition armortorso = torso.addOrReplaceChild("armortorso", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -5.75F, -1.5F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arml = torso.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(8, 0).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarml = arml.addOrReplaceChild("armorarml", CubeListBuilder.create().texOffs(38, 7).mirror().addBox(-0.25F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armr = torso.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.5F, -1.2217F, 0.0F, 0.0F));

		PartDefinition armorarmr = armr.addOrReplaceChild("armorarmr", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(11, 17).addBox(-1.5F, 0.0F, -3.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earr = head.addOrReplaceChild("earr", CubeListBuilder.create().texOffs(26, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.5F, -2.5F, -0.5F));

		PartDefinition earl = head.addOrReplaceChild("earl", CubeListBuilder.create().texOffs(20, 12).addBox(-1.0F, -2.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -2.5F, -0.5F));

		PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -7.5F, -2.75F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.1F))
				.texOffs(0, 49).addBox(-2.5F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
				.texOffs(6, 49).mirror().addBox(0.5F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition jaw = armorhead.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 45).addBox(-3.0F, 1.0F, -4.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 54).addBox(-3.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 54).addBox(2.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.5F));

		PartDefinition screws = armorhead.addOrReplaceChild("screws", CubeListBuilder.create().texOffs(18, 54).addBox(4.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 54).addBox(-5.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 52).addBox(-4.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 52).addBox(3.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 0.25F, 0.7854F, 0.0F, 0.0F));

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
		//DONT CALL SUPER, IT WILL CHANGE THE ANIMATION STATES
		//POSES ARE ALREADY INHERITED
		this.animate(this.jawAnimation, FFrankieAnimations.jaw_cycle, ageInTicks);
	}
}