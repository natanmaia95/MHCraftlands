package com.nateplays.my_neoforge_mod.entity.pets.gui;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.concurrent.atomic.AtomicReference;

public class PalicoInventoryScreen extends AbstractContainerScreen<PalicoInventoryMenu> {

    public static final Component TITLE = Component.translatable("gui." + MyNeoForgeMod.MODID + ".palico_inventory.title");

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/gui/palico_inventory.png");
    private static final ResourceLocation HEART_CONTAINER_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/container");
    private static final ResourceLocation HEART_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/full");
    private static final ResourceLocation HEART_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/half");
    private static final ResourceLocation HEART_KO_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/withered_full");
    private static final ResourceLocation HEART_KO_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/withered_half");

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
            int remainingHealth = maxHealth - maxHealthIter;
            int remainingHearts = remainingHealth / 2;
            if (maxHealthIter >= 2) {
                guiGraphics.blitSprite(HEART_CONTAINER_SPRITE, x + (remainingHealth * sprSize / 2) - remainingHearts, y, sprSize, sprSize);
                maxHealthIter -= 2;
            } else if (maxHealthIter >= 1) {
                guiGraphics.blitSprite(HEART_CONTAINER_SPRITE, x + (remainingHealth * sprSize / 2) - remainingHearts, y, 5, sprSize);
                maxHealthIter -= 1;
            }
        }

        while (roundedHealthIter > 0) {
            int remainingHealth = roundedHealth - roundedHealthIter;
            int remainingHearts = remainingHealth / 2;
            ResourceLocation heartSprite;
            if (roundedHealthIter >= 2) {
                heartSprite = this.palicoEntity.isKOed() ? HEART_KO_FULL_SPRITE : HEART_FULL_SPRITE;
                guiGraphics.blitSprite(heartSprite, x + (remainingHealth * sprSize / 2) - remainingHearts, y, sprSize, sprSize);
                roundedHealthIter -= 2;
            } else if (roundedHealthIter >= 1) {
                heartSprite = this.palicoEntity.isKOed() ? HEART_KO_HALF_SPRITE : HEART_HALF_SPRITE;
                guiGraphics.blitSprite(heartSprite, x + (remainingHealth * sprSize / 2) - remainingHearts, y, sprSize, sprSize);
                roundedHealthIter -= 1;
            }
        }

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//        super.renderLabels(guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(this.font, this.palicoEntity.getDisplayName(), this.titleLabelX, 7, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, 87, 4210752, false);
//        guiGraphics.drawString(this.font, "Health: %.1f/%.1f".formatted(palicoEntity.getHealth(), palicoEntity.getMaxHealth()), this.titleLabelX, this.titleLabelY + 18, 4210752, false);

        double heldItemDamage = palicoEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        ItemStack mainHandStack = palicoEntity.getMainHandItem();
        if (!mainHandStack.isEmpty()) {
            heldItemDamage = getFinalAttackDamage(mainHandStack);
        }
        guiGraphics.drawString(this.font, "%.1f".formatted((float) heldItemDamage), 92,  39, 4210752, false);
        guiGraphics.drawString(this.font, "%.1f".formatted((float) palicoEntity.getAttributeValue(ModAttributes.DEFENSE)), 92,  49, 4210752, false);
        guiGraphics.drawString(this.font, "%d".formatted(palicoEntity.getExpLevel()), 92,  59, 4210752, false);
        guiGraphics.drawString(this.font, "%d".formatted(palicoEntity.getExp()), 92,  69, 4210752, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderPalicoHealth(guiGraphics, this.palicoEntity, this.leftPos + 7, this.topPos + 20);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }



    private double getFinalAttackDamage(ItemStack itemStack) {
        double base = this.palicoEntity.getAttributeValue(Attributes.ATTACK_DAMAGE); //unsynced
        AtomicReference<Double> finalValue = new AtomicReference<>(base);

        ItemAttributeModifiers itemattributemodifiers = itemStack.getAttributeModifiers();
        MutableBoolean mutableboolean = new MutableBoolean(true);
        itemStack.forEachModifier(EquipmentSlotGroup.MAINHAND, (attributeHolder, attributeModifier) -> {
            if (attributeHolder != Attributes.ATTACK_DAMAGE) return;

            double modifierAmount = attributeModifier.amount();
//            switch (attributeModifier.operation()) {
//                case AttributeModifier.Operation.ADD_VALUE:
//                    finalValue += modifierAmount;
//                    break;
//                case AttributeModifier.Operation.ADD_MULTIPLIED_BASE:
//                    finalValue += modifierAmount * base;
//                    break;
//                case AttributeModifier.Operation.ADD_VALUE:
//                    finalValue += modifierAmount * finalValue;
//                    break;
//            }
            finalValue.updateAndGet(v -> (double) (v + switch (attributeModifier.operation()) {
                case ADD_VALUE -> modifierAmount;
                case ADD_MULTIPLIED_BASE -> modifierAmount * base;
                case ADD_MULTIPLIED_TOTAL -> modifierAmount * finalValue.get();
            }));
        });
        return finalValue.get();
    }
}
