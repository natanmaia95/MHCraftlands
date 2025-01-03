package com.nateplays.mhcraftlands.pet.item.custom;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ScrapItem extends Item {
    public ScrapItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        TranslatableContents loreContents = null;
        String genericDescKey = "item.%s.generic_scrap.desc".formatted(MHMod.MOD_ID);
        String specificDescKey = "item.%s.%s_scrap.desc".formatted(MHMod.MOD_ID, BuiltInRegistries.ITEM.getKey(this).getPath());
        if (Language.getInstance().getOrDefault(specificDescKey, "") != "") {
            loreContents = new TranslatableContents(specificDescKey, null, TranslatableContents.NO_ARGS);
        } else loreContents = new TranslatableContents(genericDescKey, null, TranslatableContents.NO_ARGS);

        MutableComponent loreComponent = MutableComponent.create(loreContents);
        loreComponent = loreComponent.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
        tooltipComponents.add(loreComponent);
    }
}
