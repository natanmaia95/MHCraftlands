package com.nateplays.mhcraftlands.common.armor;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MHMod.MOD_ID);

    public static final Holder<ArmorMaterial> HUNTER = registerMaterial(ARMOR_MATERIALS,
            "hunter", 4, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.LEATHER)
    );

//    public static final Holder<ArmorMaterial> HUNTER_TEST = registerMaterial(ARMOR_MATERIALS,
//            "hunter_test", 4, SoundEvents.ARMOR_EQUIP_LEATHER,
//            () -> Ingredient.of(Items.LEATHER)
//    );

    public static final Holder<ArmorMaterial> CREEPER = registerMaterial(ARMOR_MATERIALS,
            "creeper", 8, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.TNT)
    );


    public static final Holder<ArmorMaterial> CHAOSHROOM_HELMET_A = registerHunterMaterial(ARMOR_MATERIALS,
            "chaoshroom_helmet_a", 12, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.BROWN_MUSHROOM)
    );

    public static final Holder<ArmorMaterial> CHAOSHROOM_HELMET_B = registerHunterMaterial(ARMOR_MATERIALS,
            "chaoshroom_helmet_b", 12, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.BROWN_MUSHROOM)
    );







    // NOTE: The actual defense is 1/10th the baseDefense supplied here, for balancing
    public static Supplier<ArmorMaterial> makeHuntingArmorMaterial(
            int baseDefense, Holder<SoundEvent> soundEvent,
            Supplier<Ingredient> repairItem, List<ArmorMaterial.Layer> layers) {

        return () -> new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, baseDefense);
                    map.put(ArmorItem.Type.LEGGINGS, baseDefense);
                    map.put(ArmorItem.Type.CHESTPLATE, baseDefense);
                    map.put(ArmorItem.Type.HELMET, baseDefense);
                    map.put(ArmorItem.Type.BODY, baseDefense);
                }),
                0, soundEvent, repairItem, layers, 0, 0
        );
    }


    public static Holder<ArmorMaterial> registerHunterMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                            Supplier<Ingredient> repairItem) {
        return ModArmorMaterials.registerMaterial(register, "hunter/"+name, baseDefense, soundEvent, repairItem);
    }

    public static Holder<ArmorMaterial> registerHunterMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                            Supplier<Ingredient> repairItem, boolean hasEmissiveLayer, boolean hasDyeableLayer) {
        return ModArmorMaterials.registerMaterial(register, "hunter/"+name, baseDefense, soundEvent, repairItem, hasEmissiveLayer, hasDyeableLayer);
    }



    public static Holder<ArmorMaterial> registerMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                         Supplier<Ingredient> repairItem) {

        return register.register(name, makeHuntingArmorMaterial(
                baseDefense, soundEvent, repairItem,
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name)))
        ));
    }

    public static Holder<ArmorMaterial> registerMaterial(DeferredRegister<ArmorMaterial> register, String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                         Supplier<Ingredient> repairItem, boolean hasEmissiveLayer, boolean hasDyeableLayer) {

        List<ArmorMaterial.Layer> armorLayers = new ArrayList<>();
        armorLayers.add(
                new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name)));
        if (hasDyeableLayer) armorLayers.add(
                new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name), "_color", true));
        if (hasEmissiveLayer) armorLayers.add(
                new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, name), "_emissive", false));

        return register.register(name, makeHuntingArmorMaterial(baseDefense, soundEvent, repairItem, armorLayers));
    }

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
}
