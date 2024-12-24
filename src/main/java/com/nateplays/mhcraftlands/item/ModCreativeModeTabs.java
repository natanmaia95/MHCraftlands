package com.nateplays.mhcraftlands.item;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.armor.HuntingArmorItem;
import com.nateplays.mhcraftlands.common.armor.ModArmorItems;
import com.nateplays.mhcraftlands.common.weapon.ModWeaponItems;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MHMod.MOD_ID);

//    public static final Supplier<CreativeModeTab> MYNEOFORGEMOD_BLOCKS_TAB = CREATIVE_MODE_TABS.register("myneoforgemod_blocks_tab",
//            () -> CreativeModeTab.builder()
//                    .icon(() -> new ItemStack(ModBlocks.MACHALITE_ORE.get()))
//                    .title(Component.translatable("creativetab.mhcraftlands.blocks_tab"))
//                    .displayItems((itemDisplayParameters, output) -> {
//                        output.accept(ModBlocks.EARTH_CRYSTAL_ORE);
//                        output.accept(ModBlocks.MACHALITE_ORE);
//
//                        output.accept(ModBlocks.MACHALITE_BLOCK);
//                        output.accept(ModBlocks.MACHALITE_SLAB);
//                        output.accept(ModBlocks.MACHALITE_STAIRS);
//                        output.accept(ModBlocks.MACHALITE_PRESSURE_PLATE);
//                        output.accept(ModBlocks.MACHALITE_BUTTON);
//                        output.accept(ModBlocks.MACHALITE_FENCE);
//                        output.accept(ModBlocks.MACHALITE_FENCE_GATE);
//                        output.accept(ModBlocks.MACHALITE_WALL);
//                        output.accept(ModBlocks.MACHALITE_DOOR);
//                        output.accept(ModBlocks.MACHALITE_TRAPDOOR);
//
//                        output.accept(ModBlocks.NULBERRY_BUSH);
//                        output.accept(ModBlocks.MAGIC_BLOCK);
//                        output.accept(ModBlocks.MAGIC_LEAF);
//                    })
//                    .build());

    public static final Supplier<CreativeModeTab> PALICO_ITEMS_TAB = CREATIVE_MODE_TABS.register("mhcraftlands_palico_items_tab",
            () -> CreativeModeTab.builder()
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "myneoforgemod_blocks_tab"))
                    .icon(() -> new ItemStack(MHPetItems.SUMMON_FELYNE_VOUCHER.get()))
                    .title(Component.translatable("creativetab.mhcraftlands.palico_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
//                        output.accept(ModItems.EARTH_CRYSTAL);
//                        output.accept(ModItems.MALACHITE_CHUNK);
//                        output.accept(ModItems.MALACHITE_INGOT);
//                        output.accept(ModItems.NULBERRY);
//                        output.accept(ModItems.CHISEL);
//                        output.accept(ModItems.MUSIC_DISC_HUNTERGOFORTH);

//                        output.accept(ModItems.MOSSWINE_SPAWN_EGG);
                        output.accept(MHPetItems.FELYNE_SPAWN_EGG);
                        output.accept(MHPetItems.SUMMON_FELYNE_VOUCHER);
                        output.accept(MHPetItems.DISMISS_BUDDY_VOUCHER);
                        output.accept(MHPetItems.TRAINING_BOOK);

//                        output.accept(ModItems.MACHALITE_SWORD);
//                        output.accept(ModItems.MACHALITE_PICKAXE);
//                        output.accept(ModItems.MACHALITE_SHOVEL);
//                        output.accept(ModItems.MACHALITE_AXE);
//                        output.accept(ModItems.MACHALITE_HOE);
//                        output.accept(ModItems.MACHALITE_HAMMER);

                        output.accept(MHPetItems.SCRAP_WOOD);
                        output.accept(MHPetItems.SCRAP_ORE);
                        output.accept(MHPetItems.SCRAP_BONE);
                        output.accept(MHPetItems.SCRAP_FUR);
                        output.accept(MHPetItems.SCRAP_HUMBLE);
                        output.accept(MHPetItems.SCRAP_SINISTER);
                    })
//                    .backgroundTexture(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "textures/block/earth_crystal_ore.png"))
                    .build());

    public static final Supplier<CreativeModeTab> PALICO_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("mhcraftlands_palico_equipment_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "mhcraftlands_palico_items_tab"))
                    .icon(() -> new ItemStack(MHPetArmorItems.F_ACORN_MAIL.get()))
                    .title(Component.translatable("creativetab.mhcraftlands.palico_equipment_tab"))
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {
//                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_HELMET.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_CHESTPLATE.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_LEGGINGS.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_BOOTS.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.CREEPER_HELMET.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.CREEPER_CHESTPLATE.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.CREEPER_LEGGINGS.get(), itemDisplayParameters, output);
//                        outputAcceptHuntingArmorItem(ModArmorItems.CREEPER_BOOTS.get(), itemDisplayParameters, output);

                        output.accept(MHPetWeaponItems.F_KAMURA_BOKKEN);
                        output.accept(MHPetWeaponItems.F_BONE_PICK);
                        output.accept(MHPetWeaponItems.F_BONE_HAMMER);
                        output.accept(MHPetWeaponItems.F_IRON_SWORD);
                        output.accept(MHPetWeaponItems.F_RED_BASKET);
                        output.accept(MHPetWeaponItems.F_MOSGHARL_BROOM);
                        output.accept(MHPetWeaponItems.F_GHOST_LANTERN);
                        output.accept(MHPetWeaponItems.F_FRANKIE_BALL);

                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_ACORN_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_ACORN_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_KAMURA_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_KAMURA_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_BONE_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_BONE_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_ALLOY_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_ALLOY_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_RED_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_RED_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_GHOST_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_GHOST_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_MOSGHARL_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_MOSGHARL_MAIL.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_FRANKIE_HELM.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(MHPetArmorItems.F_FRANKIE_MAIL.get(), itemDisplayParameters, output);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> HUNTER_ARMOR_TAB = CREATIVE_MODE_TABS.register("mhcraftlands_hunter_armor_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "mhcraftlands_palico_equipment_tab"))
                    .icon(() -> new ItemStack(Items.LEATHER_CHESTPLATE))
                    .title(Component.translatable("creativetab.mhcraftlands.hunter_armor_tab"))
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {

                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_HELMET.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_CHESTPLATE.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_LEGGINGS.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.HUNTER_BOOTS.get(), itemDisplayParameters, output);

                        outputAcceptHuntingArmorItem(ModArmorItems.CHAOSHROOM_HELMET_A.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.CHAOSHROOM_HELMET_B.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.CHAOS_PLATE_A.get(), itemDisplayParameters, output);
                        outputAcceptHuntingArmorItem(ModArmorItems.CHAOS_PLATE_B.get(), itemDisplayParameters, output);

                    })
                    .build());



    public static void outputAcceptHuntingArmorItem(HuntingArmorItem item, CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        itemDisplayParameters.holders().lookup(Registries.ENCHANTMENT).ifPresent(enchantmentRegistryLookup -> {
            output.accept(HuntingArmorItem.makeItemStackWithLookup(item, enchantmentRegistryLookup));
        });
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
