package com.nateplays.mhcraftlands.pet.item.armor;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.armor.ModArmorMaterials;
import com.nateplays.mhcraftlands.item.ModItems;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MHPetArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> PET_ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MHMod.MOD_ID);




    public static final Holder<ArmorMaterial> F_ACORN = registerPetMaterial(PET_ARMOR_MATERIALS, "f_acorn",
            5, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_WOOD));

    public static final Holder<ArmorMaterial> F_MELYNX = registerPetMaterial(PET_ARMOR_MATERIALS, "f_melynx",
            5, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_FUR));

    public static final Holder<ArmorMaterial> F_KAMURA = registerPetMaterial(PET_ARMOR_MATERIALS, "f_kamura",
            5, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MHPetItems.SCRAP_FUR));

    public static final Holder<ArmorMaterial> F_DUFFEL = registerPetMaterial(PET_ARMOR_MATERIALS, "f_duffel",
            5, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MHPetItems.SCRAP_FUR));

    public static final Holder<ArmorMaterial> F_BONE = registerPetMaterial(PET_ARMOR_MATERIALS, "f_bone",
            7, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_BONE));

    public static final Holder<ArmorMaterial> F_ALLOY = registerPetMaterial(PET_ARMOR_MATERIALS, "f_alloy",
            9, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(MHPetItems.SCRAP_ORE));

    public static final Holder<ArmorMaterial> F_CREEPER = registerPetMaterial(PET_ARMOR_MATERIALS, "f_creeper",
            5, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER));

    public static final Holder<ArmorMaterial> F_RED = registerPetMaterial(PET_ARMOR_MATERIALS, "f_red",
            7, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MHPetItems.SCRAP_FUR), false, true);

    public static final Holder<ArmorMaterial> F_GHOST = registerPetMaterial(PET_ARMOR_MATERIALS,"f_ghost",
            15, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER), true, true);

    public static final Holder<ArmorMaterial> F_MOSGHARL = registerPetMaterial(PET_ARMOR_MATERIALS,"f_mosgharl",
            9, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_HUMBLE), true, false);

    public static final Holder<ArmorMaterial> F_FRANKIE = registerPetMaterial(PET_ARMOR_MATERIALS, "f_frankie",
            15, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(MHPetItems.SCRAP_SINISTER), false, false);

    public static final Holder<ArmorMaterial> F_DIAMOND = registerPetMaterial(PET_ARMOR_MATERIALS, "f_diamond",
            40, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(MHPetItems.SCRAP_DIAMOND));


    public static Holder<ArmorMaterial> registerPetMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                         Supplier<Ingredient> repairItem) {
        return ModArmorMaterials.registerMaterial(register, "pet/"+name, baseDefense, soundEvent, repairItem);
    }

    public static Holder<ArmorMaterial> registerPetMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                         Supplier<Ingredient> repairItem, boolean hasEmissiveLayer, boolean hasDyeableLayer) {
        return ModArmorMaterials.registerMaterial(register, "pet/"+name, baseDefense, soundEvent, repairItem, hasEmissiveLayer, hasDyeableLayer);
    }



    public static void register(IEventBus eventBus) {
        PET_ARMOR_MATERIALS.register(eventBus);
    }
}
