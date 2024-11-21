package com.nateplays.mhcraftlands.pet.item;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.ModEntities;
import com.nateplays.mhcraftlands.item.custom.SummonFelyneItem;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class MHPetItems {
    public static final DeferredRegister.Items PET_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);


    public static final DeferredItem<SpawnEggItem> FELYNE_SPAWN_EGG = PET_ITEMS.register("felyne_spawn_egg",
            () -> new SpawnEggItem(MHPetEntities.FELYNE.get(), 0xdecc99, 0x453e2a, new Item.Properties()));
    //TODO: learn how to use DeferredSpawnEggItem

    public static final DeferredItem<SummonFelyneItem> SUMMON_FELYNE_VOUCHER = PET_ITEMS.register("summon_felyne_voucher", SummonFelyneItem::new);
    public static final DeferredItem<Item> DISMISS_BUDDY_VOUCHER = PET_ITEMS.register("dismiss_buddy_voucher",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SCRAP_WOOD = registerScrapItem("wood");
    public static final DeferredItem<Item> SCRAP_BONE = registerScrapItem("bone");
    public static final DeferredItem<Item> SCRAP_ORE = registerScrapItem("ore");
    public static final DeferredItem<Item> SCRAP_FUR = registerScrapItem("fur");
    public static final DeferredItem<Item> SCRAP_HUMBLE = registerScrapItem("humble");
    public static final DeferredItem<Item> SCRAP_SINISTER = registerScrapItem("sinister");




    protected static DeferredItem<Item> registerScrapItem(String scrapName) {
        return PET_ITEMS.register(scrapName + "_scrap", () -> new Item(new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                TranslatableContents loreContents = null;
                String genericDescKey = "item.%s.generic_scrap.desc".formatted(MHMod.MOD_ID);
                String specificDescKey = "item.%s.%s_scrap.desc".formatted(MHMod.MOD_ID, scrapName);
                if (Language.getInstance().getOrDefault(specificDescKey, "") != "") {
                    loreContents = new TranslatableContents(specificDescKey, null, TranslatableContents.NO_ARGS);
                } else loreContents = new TranslatableContents(genericDescKey, null, TranslatableContents.NO_ARGS);

                MutableComponent loreComponent = MutableComponent.create(loreContents);
                loreComponent = loreComponent.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
                tooltipComponents.add(loreComponent);
            }
        });
    }




    public static void register(IEventBus eventBus) { PET_ITEMS.register(eventBus); }
}
