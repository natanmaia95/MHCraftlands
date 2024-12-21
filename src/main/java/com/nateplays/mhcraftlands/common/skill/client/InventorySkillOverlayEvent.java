package com.nateplays.mhcraftlands.common.skill.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.skill.ModEnchantmentHelper;
import com.nateplays.mhcraftlands.common.skill.ModEnchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@EventBusSubscriber(modid = MHMod.MOD_ID)
@EventBusSubscriber(modid = MHMod.MOD_ID, value = Dist.CLIENT)
public class InventorySkillOverlayEvent {

    @SubscribeEvent
    public static void onRenderInventoryScreen(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof EffectRenderingInventoryScreen inventoryScreen) {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            GuiGraphics guiGraphics = event.getGuiGraphics();
            ItemEnchantments skills = ModEnchantmentHelper.getAllPlayerSkills(player);
            if (!skills.isEmpty()) drawSkills(guiGraphics, inventoryScreen, skills);
        }
    }

    public static void drawSkills(GuiGraphics guiGraphics, EffectRenderingInventoryScreen screen, ItemEnchantments skills) {
        if (screen instanceof InventoryScreen survivalInventory) {
            if (survivalInventory.getRecipeBookComponent().isVisible()) return;
        }

        if (screen instanceof CreativeModeInventoryScreen creativeInventory) {
            if (!creativeInventory.isInventoryOpen()) return;
        }

        // Starting coordinates
        PoseStack poseStack = guiGraphics.pose();
        Font font = Minecraft.getInstance().font;

        int x = 4; // Adjust X position as needed
        int y = screen.getGuiTop();  // Adjust Y position as needed
        int width = screen.getGuiLeft() - x;
        int height = 0;

        if (Minecraft.getInstance().options.keyShift.isDown()) width *= 2;

        List<Component> componentList = new ArrayList<>();

        // Draw each enchantment
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : skills.entrySet()) {
            Holder<Enchantment> currentSkill = entry.getKey();
            int level = skills.getLevel(currentSkill);

            Component enchantmentText = Enchantment.getFullname(currentSkill, level);//.tryCollapseToString();
            componentList.add(enchantmentText);


//            Minecraft.getInstance().font.drawInBatch(enchantmentText, x, y, 0xFFFFFF, false, poseStack.last().pose(), screen.getMinecraft().renderBuffers().bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
//            y += 10;
        }
        List<ClientTooltipComponent> clientTooltips = ClientHooks.gatherTooltipComponents(ItemStack.EMPTY, componentList, x, width, guiGraphics.guiHeight(), font);

        for (ClientTooltipComponent tooltipComponent : clientTooltips) height += tooltipComponent.getHeight();
        y = screen.height/2 - height/2;

        TooltipRenderUtil.renderTooltipBackground(guiGraphics, x, y, width, height, 0);

        for (ClientTooltipComponent tooltipComponent : clientTooltips) {
            tooltipComponent.renderText(font, x, y, poseStack.last().pose(), guiGraphics.bufferSource());
            y += tooltipComponent.getHeight();
        }

//        guiGraphics.renderTooltip(font, componentList, Optional.empty(), x, y);
    }
}
