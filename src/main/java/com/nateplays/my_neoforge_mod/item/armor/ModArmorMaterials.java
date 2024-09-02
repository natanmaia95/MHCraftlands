package com.nateplays.my_neoforge_mod.item.armor;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
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
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MyNeoForgeMod.MODID);

    // We place copper somewhere between chainmail and iron.
    public static final Holder<ArmorMaterial> COPPER_ARMOR_MATERIAL =
            ARMOR_MATERIALS.register("copper", () -> new ArmorMaterial(
                    // Determines the defense value of this armor material, depending on what armor piece it is.
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 4);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 4);
                    }),
                    // Determines the enchantability of the tier. This represents how good the enchantments on this armor will be.
                    // Gold uses 25, we put copper slightly below that.
                    20,
                    // Determines the sound played when equipping this armor.
                    // This is wrapped with a Holder.
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    // Determines the repair item for this armor.
                    () -> Ingredient.of(Tags.Items.INGOTS_COPPER),
                    // Determines the texture locations of the armor to apply when rendering
                    // This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
                    List.of(
                            // Creates a new armor texture that will be located at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "copper")
                            ),
                            // Creates a new armor texture that will be rendered on top of the previous at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
                            // 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "copper"), "_overlay", true
                            )
                    ),
                    // Returns the toughness value of the armor. The toughness value is an additional value included in
                    // damage calculation, for more information, refer to the Minecraft Wiki's article on armor mechanics:
                    // https://minecraft.wiki/w/Armor#Armor_toughness
                    // Only diamond and netherite have values greater than 0 here, so we just return 0.
                    0,
                    // Returns the knockback resistance value of the armor. While wearing this armor, the player is
                    // immune to knockback to some degree. If the player has a total knockback resistance value of 1 or greater
                    // from all armor pieces combined, they will not take any knockback at all.
                    // Only netherite has values greater than 0 here, so we just return 0.
                    0
            ));


    public static final Holder<ArmorMaterial> HUNTER = registerMaterial(
            "hunter", 1, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.LEATHER)
    );

    public static final Holder<ArmorMaterial> CREEPER = registerMaterial(
            "creeper", 1, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.GUNPOWDER)
    );






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

    public static Holder<ArmorMaterial> registerMaterial(String name, int baseDefense, Holder<SoundEvent> soundEvent,
                                                         Supplier<Ingredient> repairItem) {

        return ARMOR_MATERIALS.register(name, makeHuntingArmorMaterial(
                baseDefense, soundEvent, repairItem,
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, name)))
        ));
    }

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
}
