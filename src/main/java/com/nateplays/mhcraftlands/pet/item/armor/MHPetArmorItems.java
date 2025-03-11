package com.nateplays.mhcraftlands.pet.item.armor;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.armor.HuntingArmorItem;
import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import com.nateplays.mhcraftlands.pet.client.armor.*;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponTiers;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

public class MHPetArmorItems {
    public static final DeferredRegister.Items PET_ARMOR_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);




    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_ACORN_HELM = PET_ARMOR_ITEMS.register("f_acorn_helm", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_ACORN, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_ACORN_MAIL = PET_ARMOR_ITEMS.register("f_acorn_mail", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_ACORN, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_KAMURA_HELM = PET_ARMOR_ITEMS.register("f_kamura_helm", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_KAMURA, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_KAMURA_MAIL = PET_ARMOR_ITEMS.register("f_kamura_mail", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_KAMURA, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_DUFFEL_HELM = PET_ARMOR_ITEMS.register("f_duffel_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_DUFFEL, (EntityRendererProvider.Context context) -> new FDuffelModel<>(context.bakeLayer(FDuffelModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_DUFFEL_MAIL = PET_ARMOR_ITEMS.register("f_duffel_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_DUFFEL, (EntityRendererProvider.Context context) -> new FDuffelModel<>(context.bakeLayer(FDuffelModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideTail() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_BONE_HELM = PET_ARMOR_ITEMS.register("f_bone_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_BONE, (EntityRendererProvider.Context context) -> new FBoneHelmModel<>(context.bakeLayer(FBoneHelmModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_BONE_MAIL = PET_ARMOR_ITEMS.register("f_bone_mail", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_BONE, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class)
    );

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_ALLOY_HELM = PET_ARMOR_ITEMS.register("f_alloy_helm", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_ALLOY, ArmorItem.Type.HELMET,
            new Item.Properties().durability(150), PalicoEntity.class
    ));
    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_ALLOY_MAIL = PET_ARMOR_ITEMS.register("f_alloy_mail", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_ALLOY, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class
    ));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_CREEPER_HELM = PET_ARMOR_ITEMS.register("f_creeper_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_CREEPER, (EntityRendererProvider.Context context) -> new FCreeperModel<>(context.bakeLayer(FCreeperModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideEars() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_CREEPER_MAIL = PET_ARMOR_ITEMS.register("f_creeper_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_CREEPER, (EntityRendererProvider.Context context) -> new FCreeperModel<>(context.bakeLayer(FCreeperModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_RED_HELM = PET_ARMOR_ITEMS.register("f_red_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_RED, (EntityRendererProvider.Context context) -> new FRedModel<>(context.bakeLayer(FRedModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_RED_MAIL = PET_ARMOR_ITEMS.register("f_red_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_RED, (EntityRendererProvider.Context context) -> new FRedModel<>(context.bakeLayer(FRedModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_GHOST_HELM = PET_ARMOR_ITEMS.register("f_ghost_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_GHOST, (EntityRendererProvider.Context context) -> new FGhostHelmModel<>(context.bakeLayer(FGhostHelmModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class){
                    @Override public boolean hideHead() { return true; }
    });

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_GHOST_MAIL = PET_ARMOR_ITEMS.register("f_ghost_mail", () -> new PetHuntingArmorItem<>(
            MHPetArmorMaterials.F_GHOST, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(150), PalicoEntity.class) {
                    @Override public boolean hideLegs() { return true; }
                    @Override public boolean hideArms() { return true; }
    });

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_MOSGHARL_HELM = PET_ARMOR_ITEMS.register("f_mosgharl_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_MOSGHARL, (EntityRendererProvider.Context context) -> new FMosgharlModel<>(context.bakeLayer(FMosgharlModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_MOSGHARL_MAIL = PET_ARMOR_ITEMS.register("f_mosgharl_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_MOSGHARL, (EntityRendererProvider.Context context) -> new FMosgharlModel<>(context.bakeLayer(FMosgharlModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_FRANKIE_HELM = PET_ARMOR_ITEMS.register("f_frankie_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_FRANKIE, (EntityRendererProvider.Context context) -> new FFrankieModel<>(context.bakeLayer(FFrankieModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(150), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_FRANKIE_MAIL = PET_ARMOR_ITEMS.register("f_frankie_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_FRANKIE, (EntityRendererProvider.Context context) -> new FFrankieModel<>(context.bakeLayer(FFrankieModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150), PalicoEntity.class));

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_DIAMOND_HELM = PET_ARMOR_ITEMS.register("f_diamond_helm", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_DIAMOND, (EntityRendererProvider.Context context) -> new FDiamondModel<>(context.bakeLayer(FDiamondModel.LAYER_LOCATION))),
            ArmorItem.Type.HELMET, new Item.Properties().durability(512), PalicoEntity.class
    ){ @Override public boolean hideHead() { return true; }});

    public static final DeferredItem<PetHuntingArmorItem<PalicoEntity, PalicoModel<PalicoEntity>>> F_DIAMOND_MAIL = PET_ARMOR_ITEMS.register("f_diamond_mail", () -> new PetHuntingArmorItem<>(
            new PetArmorMaterial<>(MHPetArmorMaterials.F_DIAMOND, (EntityRendererProvider.Context context) -> new FDiamondModel<>(context.bakeLayer(FDiamondModel.LAYER_LOCATION))),
            ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(512), PalicoEntity.class));


    public static final List<DeferredItem<? extends Item>> DYEABLE_ARMORS_LIST = List.of(
            F_GHOST_HELM, F_GHOST_MAIL, F_RED_HELM, F_RED_MAIL
    );




    public static void register(IEventBus eventBus) {
        PET_ARMOR_ITEMS.register(eventBus);
    }
}
