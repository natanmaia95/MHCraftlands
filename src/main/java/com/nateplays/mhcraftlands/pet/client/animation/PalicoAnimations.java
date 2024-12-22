package com.nateplays.mhcraftlands.pet.client.animation;// Save this class in your mod and generate all required imports

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Made with Blockbench 4.11.1
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class PalicoAnimations {
		public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(5.12F).looping()
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.68F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.36F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.88F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.04F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.56F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.24F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.4F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.92F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.08F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.68F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.36F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.88F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.04F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.32F, KeyframeAnimations.degreeVec(-4.31F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.6F, KeyframeAnimations.degreeVec(-1.1072F, 26.5035F, -22.4292F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.76F, KeyframeAnimations.degreeVec(-5.8735F, 32.384F, -26.8929F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.12F, KeyframeAnimations.degreeVec(-7.6533F, -34.2661F, 28.5755F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.4F, KeyframeAnimations.degreeVec(-5.8735F, -32.384F, 26.8929F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.08F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		public static final AnimationDefinition jump_excited = AnimationDefinition.Builder.withLength(1.2F)
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.36F, KeyframeAnimations.posVec(0.0F, 12.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.68F, KeyframeAnimations.posVec(0.0F, 12.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.88F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.degreeVec(19.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("arml", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, -72.5F, -55.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, -47.5F, 22.5F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, -72.5F, -55.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, -72.5F, -55.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 87.5F, 35.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 57.5F, -17.5F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 87.5F, 35.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 87.5F, 35.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.28F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.76F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.04F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.28F, KeyframeAnimations.degreeVec(-40.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.84F, KeyframeAnimations.degreeVec(-40.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.28F, KeyframeAnimations.degreeVec(-52.5F, 35.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.84F, KeyframeAnimations.degreeVec(-52.5F, 35.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(0.64F).looping()
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("arml", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(-20.0F, -60.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.48F, KeyframeAnimations.degreeVec(-20.0F, -60.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(-20.0F, 60.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.48F, KeyframeAnimations.degreeVec(-20.0F, 60.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.48F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.48F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.64F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		public static final AnimationDefinition sit = AnimationDefinition.Builder.withLength(0.52F)
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.12F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.6F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, -3.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("arml", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(49.059F, 40.7895F, -120.3612F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(66.2662F, 25.9074F, -103.5056F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.28F, KeyframeAnimations.degreeVec(114.75F, 5.3F, -5.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(49.059F, -40.7895F, 120.3612F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.degreeVec(68.7061F, -26.1269F, 97.9467F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.28F, KeyframeAnimations.degreeVec(114.75F, -5.3F, 5.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.08F, KeyframeAnimations.degreeVec(-96.0434F, -24.1782F, -18.3225F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.36F, KeyframeAnimations.degreeVec(-51.04F, -24.18F, -18.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.08F, KeyframeAnimations.degreeVec(-97.8415F, 18.2688F, 13.3647F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.36F, KeyframeAnimations.degreeVec(-52.84F, 18.27F, 13.36F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.16F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		public static final AnimationDefinition sitting = AnimationDefinition.Builder.withLength(1.04F).looping()
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -3.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("arml", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(114.75F, 5.3F, -5.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(114.75F, -5.3F, 5.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-51.04F, -24.18F, -18.32F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(-60.6479F, -35.5272F, 0.7537F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(-60.6479F, -35.5272F, 0.7537F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.degreeVec(-51.04F, -24.18F, -18.32F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.72F, KeyframeAnimations.degreeVec(-46.2481F, -10.8779F, -34.0328F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.84F, KeyframeAnimations.degreeVec(-46.2481F, -10.8779F, -34.0328F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.04F, KeyframeAnimations.degreeVec(-51.04F, -24.18F, -18.32F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-52.84F, 18.27F, 13.36F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.2F, KeyframeAnimations.degreeVec(-60.2931F, 29.3912F, -4.8704F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.32F, KeyframeAnimations.degreeVec(-60.2931F, 29.3912F, -4.8704F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.degreeVec(-52.84F, 18.27F, 13.36F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.72F, KeyframeAnimations.degreeVec(-49.5055F, 5.6476F, 29.2567F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.84F, KeyframeAnimations.degreeVec(-49.5055F, 5.6476F, 29.2567F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.04F, KeyframeAnimations.degreeVec(-52.84F, 18.27F, 13.36F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		public static final AnimationDefinition faint = AnimationDefinition.Builder.withLength(3.25F)
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 720.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 540.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 360.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 180.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(2.2F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(2.3F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.35F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.45F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.65F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.05F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.25F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.55F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("arml", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, -70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.65F, KeyframeAnimations.degreeVec(20.0F, 52.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.9F, KeyframeAnimations.degreeVec(20.0F, 52.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.15F, KeyframeAnimations.degreeVec(-45.0F, -70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.6F, KeyframeAnimations.degreeVec(-45.0F, 70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.8F, KeyframeAnimations.degreeVec(-117.7394F, -10.8864F, -54.2614F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.9F, KeyframeAnimations.degreeVec(-108.6394F, -6.6731F, -54.5019F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.0F, KeyframeAnimations.degreeVec(-120.2877F, -8.5227F, -39.2314F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.1F, KeyframeAnimations.degreeVec(-110.421F, -6.7968F, -39.3975F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.25F, KeyframeAnimations.degreeVec(-121.596F, -6.6198F, -29.3271F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.35F, KeyframeAnimations.degreeVec(-110.421F, -6.7968F, -39.3975F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.6F, KeyframeAnimations.degreeVec(-121.6281F, -4.3971F, -27.0701F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.9F, KeyframeAnimations.degreeVec(-121.6281F, -4.3971F, -27.0701F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.15F, KeyframeAnimations.degreeVec(-45.0F, 70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("armr", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.95F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.05F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.15F, KeyframeAnimations.degreeVec(-70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(-70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(2.1F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(2.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.85F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.1F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.95F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
						new Keyframe(2.45F, KeyframeAnimations.degreeVec(-95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.3F, KeyframeAnimations.degreeVec(-47.7532F, -17.0468F, -14.9092F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.3F, KeyframeAnimations.degreeVec(-47.7532F, 17.0468F, 14.9092F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.POSITION,
						new Keyframe(0.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();
}