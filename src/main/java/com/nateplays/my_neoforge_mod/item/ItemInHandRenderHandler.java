package com.nateplays.my_neoforge_mod.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.component.ModDataComponents;
import com.nateplays.my_neoforge_mod.event.LivingRenderHandItemEvent;
import com.nateplays.my_neoforge_mod.item.weapons.DualBladesItem;
import com.nateplays.my_neoforge_mod.item.weapons.SwordAndShieldItem;
import com.nateplays.my_neoforge_mod.mixin.ItemInHandRendererAccessor;
//import com.nateplays.my_neoforge_mod.mixin.ItemInHandRendererMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class ItemInHandRenderHandler {

    public static boolean IS_ALREADY_RENDERING_HAND = false;

    @SubscribeEvent
    public static void onRenderHandFirstPerson(RenderHandEvent event) {
        Item item = event.getItemStack().getItem();

        if (item instanceof DualBladesItem || item instanceof SwordAndShieldItem) {
            if (IS_ALREADY_RENDERING_HAND) {
                event.setCanceled(false);
                return; //dont cancel! this is the event thrown by the new render call below.
            }
            if (event.getHand() != InteractionHand.MAIN_HAND) return; //dont call this event twice

            //if shouldCauseReequipAnimation isn't overriden, it will keep lowering the hand ad infinitum
            ItemStack newMainHandStack = event.getItemStack().copy();
            newMainHandStack.set(ModDataComponents.FAKE_RENDER_HAND, HumanoidArm.RIGHT);
            ItemStack newOffHandStack = event.getItemStack().copy();
            newOffHandStack.set(ModDataComponents.FAKE_RENDER_HAND, HumanoidArm.LEFT);

            ItemInHandRenderer itemInHandRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
            LocalPlayer localPlayer = Minecraft.getInstance().player;

            PoseStack poseStack = event.getPoseStack();
            MultiBufferSource bufferSource = event.getMultiBufferSource();
            int packedLight = event.getPackedLight();
            IS_ALREADY_RENDERING_HAND = true;
            float mainHandHeight = ((ItemInHandRendererAccessor) itemInHandRenderer).mainHandHeight();
            ((ItemInHandRendererAccessor) itemInHandRenderer).setMainHandItem(newMainHandStack);
            ((ItemInHandRendererAccessor) itemInHandRenderer).setOffHandItem(newOffHandStack);
            ((ItemInHandRendererAccessor) itemInHandRenderer).setOffHandHeight(mainHandHeight);
            itemInHandRenderer.renderHandsWithItems(event.getPartialTick(), poseStack, (MultiBufferSource.BufferSource) bufferSource, localPlayer, packedLight);
            IS_ALREADY_RENDERING_HAND = false;
            event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public static void onRenderHandThirdPerson(LivingRenderHandItemEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Item mainHandItem = livingEntity.getMainHandItem().getItem();

        if (mainHandItem instanceof DualBladesItem || mainHandItem instanceof SwordAndShieldItem) {
            ItemStack newItemStack = livingEntity.getMainHandItem().copy();
            if (event.getArm() == livingEntity.getMainArm()) {
                newItemStack.set(ModDataComponents.FAKE_RENDER_HAND, HumanoidArm.RIGHT);
            } else {
                newItemStack.set(ModDataComponents.FAKE_RENDER_HAND, HumanoidArm.LEFT);
            }
            event.setModifiedItemStack(newItemStack);
            return;
        }
    }
}

