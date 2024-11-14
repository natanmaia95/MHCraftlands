package com.nateplays.my_neoforge_mod.item;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.item.custom.ChiselItem;
import com.nateplays.my_neoforge_mod.item.custom.HammerItem;
import com.nateplays.my_neoforge_mod.item.custom.SummonFelyneItem;
import com.nateplays.my_neoforge_mod.item.weapons.*;
import com.nateplays.my_neoforge_mod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyNeoForgeMod.MODID);

    public static final DeferredItem<Item> EARTH_CRYSTAL = ITEMS.register("earth_crystal",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_CHUNK = ITEMS.register("machalite_chunk",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_INGOT = ITEMS.register("machalite_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NULBERRY = ITEMS.register("nulberry",
            () -> new NulberryItem(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("Eat to remove status effects.").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }

    );

    public static final DeferredItem<Item> MUSIC_DISC_HUNTERGOFORTH = ITEMS.register("music_disc_huntergoforth",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DISCKEY_HUNTERGOFORTH).stacksTo(1)));

    public static final DeferredItem<SwordItem> MACHALITE_SWORD = ITEMS.register("machalite_sword",
            () -> new SwordItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.MACHALITE, 5f, -3f))));

    public static final DeferredItem<PickaxeItem> MACHALITE_PICKAXE = ITEMS.register("machalite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.MACHALITE, 1.4f, -2.8f))));

    public static final DeferredItem<ShovelItem> MACHALITE_SHOVEL = ITEMS.register("machalite_shovel",
            () -> new ShovelItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.MACHALITE, 1.0f, -2.8f))));

    public static final DeferredItem<AxeItem> MACHALITE_AXE = ITEMS.register("machalite_axe",
            () -> new AxeItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.MACHALITE, 6f, -3.2f))));

    public static final DeferredItem<HoeItem> MACHALITE_HOE = ITEMS.register("machalite_hoe",
            () -> new HoeItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.MACHALITE, 0f, -0.3f))));

    public static final DeferredItem<HammerItem> MACHALITE_HAMMER = ITEMS.register("machalite_hammer",
            () -> new HammerItem(ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.MACHALITE, 7f, -4.0f))));



    public static final DeferredItem<SpawnEggItem> MOSSWINE_SPAWN_EGG = ITEMS.register("mosswine_spawn_egg",
            () -> new SpawnEggItem(ModEntities.MOSSWINE.get(), 0x456296, 0xEF6915, new Item.Properties()));

    public static final DeferredItem<SpawnEggItem> FELYNE_SPAWN_EGG = ITEMS.register("felyne_spawn_egg",
            () -> new SpawnEggItem(ModEntities.FELYNE.get(), 0xdecc99, 0x453e2a, new Item.Properties()));
    //TODO: learn how to use DeferredSpawnEggItem

    public static final DeferredItem<SummonFelyneItem> SUMMON_FELYNE_VOUCHER = ITEMS.register("summon_felyne_voucher", SummonFelyneItem::new);
    public static final DeferredItem<Item> DISMISS_BUDDY_VOUCHER = ITEMS.register("dismiss_buddy_voucher",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));


//    public static final DeferredItem<Item> SCRAP_WOOD = ITEMS.register("wood_scrap", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SCRAP_BONE = ITEMS.register("bone_scrap", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SCRAP_ORE = ITEMS.register("ore_scrap", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SCRAP_FUR = ITEMS.register("fur_scrap", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SCRAP_HUMBLE = ITEMS.register("humble_scrap", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SCRAP_SINISTER = ITEMS.register("sinister_scrap", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCRAP_WOOD = registerScrapItem("wood");
    public static final DeferredItem<Item> SCRAP_BONE = registerScrapItem("bone");
    public static final DeferredItem<Item> SCRAP_ORE = registerScrapItem("ore");
    public static final DeferredItem<Item> SCRAP_FUR = registerScrapItem("fur");
    public static final DeferredItem<Item> SCRAP_HUMBLE = registerScrapItem("humble");
    public static final DeferredItem<Item> SCRAP_SINISTER = registerScrapItem("sinister");


    public static final DeferredItem<SwordAndShieldItem> MACHALITE_SNS = ITEMS.register("machalite_sns",
            () -> new SwordAndShieldItem(
                    ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(SwordAndShieldItem.createAttributes(ModToolTiers.MACHALITE))));

    public static final DeferredItem<DualBladesItem> MACHALITE_DB = ITEMS.register("machalite_db",
            () -> new DualBladesItem(
                    ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(DualBladesItem.createAttributes(ModToolTiers.MACHALITE))));

    public static final DeferredItem<GreatSwordItem> MACHALITE_GS = ITEMS.register("machalite_gs",
            () -> new GreatSwordItem(
                    ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(GreatSwordItem.createAttributes(ModToolTiers.MACHALITE))));

    public static final DeferredItem<GlaiveItem> MACHALITE_GV = ITEMS.register("machalite_gv",
            () -> new GlaiveItem(
                    ModToolTiers.MACHALITE, new Item.Properties()
                    .attributes(GlaiveItem.createAttributes(ModToolTiers.MACHALITE))));







    protected static DeferredItem<Item> registerScrapItem(String scrapName) {
        return ITEMS.register(scrapName + "_scrap", () -> new Item(new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                TranslatableContents loreContents = null;
                String genericDescKey = "item.%s.generic_scrap.desc".formatted(MyNeoForgeMod.MODID);
                String specificDescKey = "item.%s.%s_scrap.desc".formatted(MyNeoForgeMod.MODID, scrapName);
                if (Language.getInstance().getOrDefault(specificDescKey, "") != "") {
                    loreContents = new TranslatableContents(specificDescKey, null, TranslatableContents.NO_ARGS);
                } else loreContents = new TranslatableContents(genericDescKey, null, TranslatableContents.NO_ARGS);

                MutableComponent loreComponent = MutableComponent.create(loreContents);
                loreComponent = loreComponent.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
                tooltipComponents.add(loreComponent);
            }
        });

    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
