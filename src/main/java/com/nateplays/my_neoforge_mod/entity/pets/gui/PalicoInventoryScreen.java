package com.nateplays.my_neoforge_mod.entity.pets.gui;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;

public class PalicoInventoryScreen extends AbstractContainerScreen<PalicoInventoryMenu> {

    public static final Component TITLE = Component.translatable("gui." + MyNeoForgeMod.MODID + ".palico_inventory.title");

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/gui/palico_inventory.png");
    private static final ResourceLocation HEART_VEHICLE_CONTAINER_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/vehicle_container");
    private static final ResourceLocation HEART_VEHICLE_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/vehicle_full");
    private static final ResourceLocation HEART_VEHICLE_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/vehicle_half");

    private final PalicoEntity palicoEntity;
    private float xMouse;
    private float yMouse;

    public PalicoInventoryScreen(PalicoInventoryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, TITLE);
        this.palicoEntity = menu.palicoEntity;
        this.imageWidth = 176; // Width of the GUI
        this.imageHeight = 181; // Height of the GUI
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 26, j + 33, i + 75, j + 85, 35, 0.25F, this.xMouse, this.yMouse, this.palicoEntity);

    }

    public void renderPalicoHealth(GuiGraphics guiGraphics, LivingEntity entity, int x, int y) {
        if (entity == null || entity.isRemoved()) return;
//        int x = 0; int y = 0;
        int sprSize = 9;

        int maxHealth = Math.round(entity.getMaxHealth());
        int maxHealthIter = maxHealth;
        int roundedHealth = Math.round(entity.getHealth());
        int roundedHealthIter = roundedHealth;
        int painCounter = 0;

        while (maxHealthIter > 0) {
            if (maxHealthIter >= 2) {
                guiGraphics.blitSprite(HEART_VEHICLE_CONTAINER_SPRITE, x + (maxHealth - maxHealthIter)*sprSize, y, sprSize, sprSize);
                maxHealthIter -= 2;
            } else if (maxHealthIter >= 1) {
                guiGraphics.blitSprite(HEART_VEHICLE_CONTAINER_SPRITE, x + (maxHealth - maxHealthIter)*sprSize, y, sprSize, sprSize);
                maxHealthIter -= 1;
            }
        }

        while (roundedHealthIter > 0) {
            if (roundedHealthIter >= 2) {
                guiGraphics.blitSprite(HEART_VEHICLE_FULL_SPRITE, x + (roundedHealth - roundedHealthIter)*sprSize, y, sprSize, sprSize);
                roundedHealthIter -= 2;
            } else if (roundedHealthIter >= 1) {
                guiGraphics.blitSprite(HEART_VEHICLE_HALF_SPRITE, x + (roundedHealth - roundedHealthIter)*sprSize, y, sprSize, sprSize);
                roundedHealthIter -= 1;
            }
        }

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//        super.renderLabels(guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(this.font, this.palicoEntity.getDisplayName(), this.titleLabelX, 7, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, 87, 4210752, false);
        guiGraphics.drawString(this.font, "Health: %.1f/%.1f".formatted(palicoEntity.getHealth(), palicoEntity.getMaxHealth()), this.titleLabelX, this.titleLabelY + 18, 4210752, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderPalicoHealth(guiGraphics, this.palicoEntity, this.leftPos + 7, this.topPos + 20);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
