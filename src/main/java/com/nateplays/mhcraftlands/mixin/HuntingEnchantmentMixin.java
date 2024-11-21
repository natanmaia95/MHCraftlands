package com.nateplays.mhcraftlands.mixin;

import com.nateplays.mhcraftlands.common.skill.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.enchantment.Enchantment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class HuntingEnchantmentMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger(HuntingEnchantmentMixin.class);

    @Inject(method = "getFullname", at = @At("TAIL"), cancellable = true)
    private static void mhcraftlands_getFullname(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> info) {

//        if (enchantment.is(ModEnchantments.TAG_SKILL_ENCHANTMENTS)) {
        MutableComponent newComponent = enchantment.value().description().copy();
//        ComponentUtils.mergeStyles(newComponent, Style.EMPTY.withColor(ChatFormatting.GOLD));
//        if (enchantment.is(EnchantmentTags.MINING_EXCLUSIVE)) {
//            newComponent.append(Component.literal("SKILL!"));
//        }
        if (enchantment.is(ModEnchantments.TAG_SKILL_ENCHANTMENTS)) {
            String enchantName = enchantment.unwrapKey().get().location().getPath();
            int remainingLevels = (int) Mth.absMax(enchantment.value().getMaxLevel() - level, 0);

            ComponentUtils.mergeStyles(newComponent, Style.EMPTY.withColor(ChatFormatting.GOLD));
            newComponent.append(Component.literal( " " + "★".repeat(level) + "☆".repeat(remainingLevels)));
            newComponent.append(Component.literal(" [%d/%d]".formatted(level, enchantment.value().getMaxLevel())));

            if (Screen.hasShiftDown()) {
                Language language = Language.getInstance();

                String skillDescKey = "tooltip.mhcraftlands.skill_description." + enchantName;
                String leveledSkillDescKey = skillDescKey + ".%d".formatted(level);
                TranslatableContents skillDescContents = null;
                if (language.getOrDefault(leveledSkillDescKey, "") != "") {
                    skillDescContents = new TranslatableContents(leveledSkillDescKey, null, TranslatableContents.NO_ARGS);
                } else {
                    skillDescContents = new TranslatableContents(skillDescKey, null, TranslatableContents.NO_ARGS);
                }

                MutableComponent skillDesc = MutableComponent.create(skillDescContents);
                skillDesc = skillDesc.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
                newComponent.append(CommonComponents.NEW_LINE);
                newComponent.append(skillDesc);
            }

            info.setReturnValue(newComponent);
        }

//        }

    }
}
