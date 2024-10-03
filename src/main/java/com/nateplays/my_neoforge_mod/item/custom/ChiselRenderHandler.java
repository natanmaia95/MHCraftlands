package com.nateplays.my_neoforge_mod.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.event.LivingRenderHandItemEvent;
import com.nateplays.my_neoforge_mod.item.ModItems;
import com.nateplays.my_neoforge_mod.mixin.ItemInHandRendererAccessor;
//import com.nateplays.my_neoforge_mod.mixin.ItemInHandRendererMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.joml.Matrix4fStack;
import org.joml.Quaternionf;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class ChiselRenderHandler {

    public static boolean IS_ALREADY_RENDERING_HAND = false;

    @SubscribeEvent
    public static void onRenderHandFirstPerson(RenderHandEvent event) {

        if (event.getItemStack().getItem() instanceof ChiselItem) {
            if (IS_ALREADY_RENDERING_HAND) {
                return; //dont cancel! this is the new render call below.
            }
            ItemStack itemStack = new ItemStack(Items.SHIELD);
            ItemInHandRenderer itemInHandRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
            LocalPlayer localPlayer = Minecraft.getInstance().player;

            PoseStack poseStack = event.getPoseStack();
            MultiBufferSource bufferSource = event.getMultiBufferSource();
            int packedLight = event.getPackedLight();
            IS_ALREADY_RENDERING_HAND = true;
            float mainHandHeight = ((ItemInHandRendererAccessor) itemInHandRenderer).mainHandHeight();
            ((ItemInHandRendererAccessor) itemInHandRenderer).setOffHandItem(itemStack);
            ((ItemInHandRendererAccessor) itemInHandRenderer).setOffHandHeight(mainHandHeight);
            itemInHandRenderer.renderHandsWithItems(event.getPartialTick(), poseStack, (MultiBufferSource.BufferSource) bufferSource, localPlayer, packedLight);
            IS_ALREADY_RENDERING_HAND = false;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderHandThirdPerson(LivingRenderHandItemEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.getMainHandItem().getItem() instanceof ChiselItem && event.getArm() != livingEntity.getMainArm()) {
            event.setModifiedItemStack(new ItemStack(Items.SHIELD));
        }
    }
}

