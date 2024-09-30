package com.nateplays.my_neoforge_mod.item.armor;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModArmorItems {

    public static final DeferredRegister.Items ARMOR_ITEMS = DeferredRegister.createItems(MyNeoForgeMod.MODID);

//    public static final Supplier<HuntingArmorItem> COPPER_HELMET = ARMOR_ITEMS.register("copper_helmet", () -> new HuntingArmorItem(
//            // The armor material to use.
//            ModArmorMaterials.COPPER_ARMOR_MATERIAL,
//            // The armor type to use.
//            ArmorItem.Type.HELMET,
//            // The item properties where we set the durability.
//            // ArmorItem.Type is an enum of five values: HELMET, CHESTPLATE, LEGGINGS, BOOTS, and BODY.
//            // BODY is used for non-player entities like wolves or horses.
//            // Vanilla armor materials determine this by using a base value and multiplying it with a type-specific constant.
//            // The constants are 13 for BOOTS, 15 for LEGGINGS, 16 for CHESTPLATE, 11 for HELMET, and 16 for BODY.
//            // If we don't want to use these ratios, we can set the durability normally.
//            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15))
//    ));
//    public static final Supplier<HuntingArmorItem> COPPER_CHESTPLATE = ARMOR_ITEMS.register(
//            "copper_chestplate", () -> new HuntingArmorItem(
//                    ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15))
//            ));

    public static final Supplier<HuntingArmorItem> HUNTER_HELMET = ARMOR_ITEMS.register("hunter_helmet", () -> new HuntingArmorItem(
            ModArmorMaterials.HUNTER, ArmorItem.Type.HELMET,
            new Item.Properties().durability(100)
    ));
    public static final Supplier<HuntingArmorItem> HUNTER_CHESTPLATE = ARMOR_ITEMS.register("hunter_chestplate", () -> new HuntingArmorItem(
            ModArmorMaterials.HUNTER, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(100)
    ));
    public static final Supplier<HuntingArmorItem> HUNTER_LEGGINGS = ARMOR_ITEMS.register("hunter_leggings", () -> new HuntingArmorItem(
            ModArmorMaterials.HUNTER, ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(100)
    ));
    public static final Supplier<HuntingArmorItem> HUNTER_BOOTS = ARMOR_ITEMS.register("hunter_boots", () -> new HuntingArmorItem(
            ModArmorMaterials.HUNTER, ArmorItem.Type.BOOTS,
            new Item.Properties().durability(100)
    ));

    public static final Supplier<HuntingArmorItem> CREEPER_HELMET = ARMOR_ITEMS.register("creeper_helmet", () -> new HuntingArmorItem(
            ModArmorMaterials.CREEPER, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150)
    ));
    public static final Supplier<HuntingArmorItem> CREEPER_CHESTPLATE = ARMOR_ITEMS.register("creeper_chestplate", () -> new HuntingArmorItem(
            ModArmorMaterials.CREEPER, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150)
    ));
    public static final Supplier<HuntingArmorItem> CREEPER_LEGGINGS = ARMOR_ITEMS.register("creeper_leggings", () -> new HuntingArmorItem(
            ModArmorMaterials.CREEPER, ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(150)
    ));
    public static final Supplier<HuntingArmorItem> CREEPER_BOOTS = ARMOR_ITEMS.register("creeper_boots", () -> new HuntingArmorItem(
            ModArmorMaterials.CREEPER, ArmorItem.Type.BOOTS,
            new Item.Properties().durability(150)
    ));


    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity>> F_ACORN_HELM = ARMOR_ITEMS.register("f_acorn_helm", () -> new PetHuntingArmorItem<PalicoEntity>(
            ModArmorMaterials.F_ACORN, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity>> F_ACORN_MAIL = ARMOR_ITEMS.register("f_acorn_mail", () -> new PetHuntingArmorItem<PalicoEntity>(
            ModArmorMaterials.F_ACORN, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));


    public static void register(IEventBus eventBus) {
        ARMOR_ITEMS.register(eventBus);
    }

}
