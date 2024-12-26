package com.nateplays.mhcraftlands.pet.gui;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.concurrent.atomic.AtomicReference;

public class PalicoInventoryScreen extends AbstractContainerScreen<PalicoInventoryMenu> {

    public static final Component TITLE = Component.translatable("gui." + MHMod.MOD_ID + ".palico_inventory.title");

    private static final ResourceLocation BACKGROUND_TEXTURE_INVENTORY =
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/gui/palico_inventory.png");
    private static final ResourceLocation BACKGROUND_TEXTURE_SKILLS =
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/gui/palico_skills.png");
    private static final ResourceLocation BACKGROUND_TEXTURE_TOOLS =
            ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/gui/palico_tools.png");


    private static final ResourceLocation HEART_CONTAINER_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/container");
    private static final ResourceLocation HEART_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/full");
    private static final ResourceLocation HEART_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/half");
    private static final ResourceLocation HEART_KO_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/withered_full");
    private static final ResourceLocation HEART_KO_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/heart/withered_half");

    private final PalicoEntity palicoEntity;
    private float mouseX;
    private float mouseY;

    private int currentTab = 0;

    public PalicoInventoryScreen(PalicoInventoryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, TITLE);
        this.palicoEntity = menu.palicoEntity;
        this.imageWidth = 176; // Width of the GUI
        this.imageHeight = 181; // Height of the GUI
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        addButtons();
    }

    private void addButtons() {
        // Add the Inventory Tab Button
        this.addRenderableWidget(Button.builder(
                                Component.literal("Inv"), // Button label
                                button -> switchTab(0)    // OnClick action
                        )
                        .bounds(this.leftPos + 10, this.topPos - 20, 40, 20) // Position and size
                        .build()
        );

        // Add the Skills Tab Button
        this.addRenderableWidget(Button.builder(
                                Component.literal("Tools"),
                                button -> switchTab(1)
                        )
                        .bounds(this.leftPos + 60,  this.topPos - 20, 40, 20)
                        .build()
        );
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        if (currentTab == 0) { // Inventory Screen
            guiGraphics.blit(BACKGROUND_TEXTURE_INVENTORY, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

            int i = (this.width - this.imageWidth) / 2;
            int j = (this.height - this.imageHeight) / 2;
            InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 26, j + 33, i + 75, j + 85,
                    35, 0.25F, this.mouseX, this.mouseY, this.palicoEntity);

        }

        else if (currentTab == 1) {
            guiGraphics.blit(BACKGROUND_TEXTURE_TOOLS, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int textColorBlack = 4210752;

        guiGraphics.drawString(this.font, this.palicoEntity.getDisplayName(), this.titleLabelX, 7, textColorBlack, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, 87, textColorBlack, false);



        if (currentTab == 0) { // Inventory Screen
//            renderPalicoHealth(guiGraphics, this.palicoEntity, this.leftPos + 7, this.topPos + 20);

            double heldItemDamage = palicoEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
            ItemStack mainHandStack = palicoEntity.getMainHandItem();
            if (!mainHandStack.isEmpty()) {
                heldItemDamage = getFinalAttackDamage(mainHandStack);
            }
            guiGraphics.drawString(this.font, "%.1f".formatted((float) heldItemDamage), 92,  39, textColorBlack, false);
            guiGraphics.drawString(this.font, "%.1f".formatted((float) palicoEntity.getAttributeValue(ModAttributes.DEFENSE)), 92,  49, textColorBlack, false);
            guiGraphics.drawString(this.font, "%d".formatted(palicoEntity.getExpLevel()), 92,  59, textColorBlack, false);
            guiGraphics.drawString(this.font, "%d".formatted(palicoEntity.getExp()), 92,  69, textColorBlack, false);


        }


        else if (currentTab == 1) { // Skills Screen
//            guiGraphics.drawString(this.font, "Skills go here!", 92,  39, 4210752, false);

            guiGraphics.drawString(this.font, palicoEntity.getToolPreferenceName(), 90, 45, textColorBlack, false);
            guiGraphics.drawString(this.font, "%2d / %2d".formatted(palicoEntity.getUsedToolPoints(), palicoEntity.getMaxToolPoints()),
                    88,  58, textColorBlack, false);

        }

    }



    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.mouseX = (float)mouseX;
        this.mouseY = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderPalicoHealth(guiGraphics, this.palicoEntity, this.leftPos + 7, this.topPos + 20);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        if (currentTab == 1) currentTab = 0;
//        else currentTab = 1;
//        updateSlotVisibility();

//        System.out.println("%.2f %.2f".formatted(mouseX, mouseY));
        return super.mouseClicked(mouseX, mouseY, button);
    }



    protected void switchTab(int tab) {
        this.currentTab = tab;
        this.getMenu().currentTab = this.currentTab; // only syncs client side
    }

    public void renderPalicoHealth(GuiGraphics guiGraphics, LivingEntity entity, int x, int y) {
        if (entity == null || entity.isRemoved()) return;
//        int x = 0; int y = 0;
        guiGraphics.flush();
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

        guiGraphics.flush();

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
