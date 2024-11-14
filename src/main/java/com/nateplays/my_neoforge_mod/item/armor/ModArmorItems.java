package com.nateplays.my_neoforge_mod.item.armor;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
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


    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_ACORN_HELM = ARMOR_ITEMS.register("f_acorn_helm", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_ACORN, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_ACORN_MAIL = ARMOR_ITEMS.register("f_acorn_mail", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_ACORN, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_KAMURA_HELM = ARMOR_ITEMS.register("f_kamura_helm", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_KAMURA, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_KAMURA_MAIL = ARMOR_ITEMS.register("f_kamura_mail", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_KAMURA, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_BONE_HELM = ARMOR_ITEMS.register("f_bone_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<FBoneHelmModel>(ModArmorMaterials.F_BONE, (EntityRendererProvider.Context context) -> new FBoneHelmModel<>(context.bakeLayer(ModModelLayers.F_BONE_HELM_LAYER))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_BONE_MAIL = ARMOR_ITEMS.register("f_bone_mail",
            () -> new PetHuntingArmorItem<PalicoEntity, PalicoModel>(
                    ModArmorMaterials.F_BONE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(150), PalicoEntity.class)
//            {
//                @Override
//                public boolean showLegs() { return false; }
//            }
    );

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_ALLOY_HELM = ARMOR_ITEMS.register("f_alloy_helm", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_ALLOY, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_ALLOY_MAIL = ARMOR_ITEMS.register("f_alloy_mail", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_ALLOY, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_RED_HELM = ARMOR_ITEMS.register("f_red_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_RED, (EntityRendererProvider.Context context) -> new FRedModel<>(context.bakeLayer(FRedModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_RED_MAIL = ARMOR_ITEMS.register("f_red_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_RED, (EntityRendererProvider.Context context) -> new FRedModel<>(context.bakeLayer(FRedModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_GHOST_HELM = ARMOR_ITEMS.register("f_ghost_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<FGhostHelmModel>(ModArmorMaterials.F_GHOST, (EntityRendererProvider.Context context) -> new FGhostHelmModel<>(context.bakeLayer(ModModelLayers.F_GHOST_HELM_LAYER))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel>> F_GHOST_MAIL = ARMOR_ITEMS.register("f_ghost_mail", () -> new PetHuntingArmorItem<>(
            ModArmorMaterials.F_GHOST, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class)
    {
        @Override public boolean hideLegs() { return true; }
        @Override public boolean hideArms() { return true; }
    }
    );

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_MOSGHARL_HELM = ARMOR_ITEMS.register("f_mosgharl_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_MOSGHARL, (EntityRendererProvider.Context context) -> new FMosgharlModel<>(context.bakeLayer(ModModelLayers.F_MOSGHARL_LAYER))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_MOSGHARL_MAIL = ARMOR_ITEMS.register("f_mosgharl_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_MOSGHARL, (EntityRendererProvider.Context context) -> new FMosgharlModel<>(context.bakeLayer(ModModelLayers.F_MOSGHARL_LAYER))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_FRANKIE_HELM = ARMOR_ITEMS.register("f_frankie_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_FRANKIE, (EntityRendererProvider.Context context) -> new FFrankieModel<>(context.bakeLayer(FFrankieModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_FRANKIE_MAIL = ARMOR_ITEMS.register("f_frankie_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(ModArmorMaterials.F_FRANKIE, (EntityRendererProvider.Context context) -> new FFrankieModel<>(context.bakeLayer(FFrankieModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));


    public static final List<DeferredItem<? extends Item>> DYEABLE_ARMORS_LIST = List.of(
            F_GHOST_HELM, F_GHOST_MAIL, F_RED_HELM, F_RED_MAIL
    );


    public static void register(IEventBus eventBus) {
        ARMOR_ITEMS.register(eventBus);
    }

}
