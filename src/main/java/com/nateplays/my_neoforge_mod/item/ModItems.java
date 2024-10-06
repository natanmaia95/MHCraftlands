package com.nateplays.my_neoforge_mod.item;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.item.custom.ChiselItem;
import com.nateplays.my_neoforge_mod.item.custom.HammerItem;
import com.nateplays.my_neoforge_mod.item.weapons.DualBladesItem;
import com.nateplays.my_neoforge_mod.item.weapons.GlaiveItem;
import com.nateplays.my_neoforge_mod.item.weapons.GreatSwordItem;
import com.nateplays.my_neoforge_mod.item.weapons.SwordAndShieldItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
            () -> new SpawnEggItem(ModEntities.MOSSWINE.get(), 0, 2, new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));



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



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
