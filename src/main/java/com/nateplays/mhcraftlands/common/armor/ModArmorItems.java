package com.nateplays.mhcraftlands.common.armor;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.client.rendering.BaseHuntingArmorModel;
import com.nateplays.mhcraftlands.hunter.armor.PlayerHuntingArmorItem;
import com.nateplays.mhcraftlands.hunter.armor.model.ChaoshroomHelmetBModel;
import com.nateplays.mhcraftlands.hunter.armor.model.ChaoshroomHelmetAModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModArmorItems {

    public static final DeferredRegister.Items ARMOR_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);




    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> HUNTER_HELMET =
            ARMOR_ITEMS.register("hunter_helmet", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.HUNTER, ArmorItem.Type.HELMET, new Item.Properties().durability(100),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> HUNTER_CHESTPLATE =
            ARMOR_ITEMS.register("hunter_chestplate", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.HUNTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(100),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> HUNTER_LEGGINGS =
            ARMOR_ITEMS.register("hunter_leggings", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.HUNTER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(100),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> HUNTER_BOOTS =
            ARMOR_ITEMS.register("hunter_boots", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.HUNTER, ArmorItem.Type.BOOTS, new Item.Properties().durability(100),
                    makeDefaultModel()
    ));

    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> CREEPER_HELMET =
            ARMOR_ITEMS.register("creeper_helmet", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CREEPER, ArmorItem.Type.HELMET, new Item.Properties().durability(150),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> CREEPER_CHESTPLATE =
            ARMOR_ITEMS.register("creeper_chestplate", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CREEPER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(150),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> CREEPER_LEGGINGS =
            ARMOR_ITEMS.register("creeper_leggings", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CREEPER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(150),
                    makeDefaultModel()
    ));
    public static final Supplier<PlayerHuntingArmorItem<Player, BaseHuntingArmorModel<Player>>> CREEPER_BOOTS =
            ARMOR_ITEMS.register("creeper_boots", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CREEPER, ArmorItem.Type.BOOTS, new Item.Properties().durability(150),
                    makeDefaultModel()
    ));




    public static final Supplier<PlayerHuntingArmorItem<Player, ChaoshroomHelmetAModel<Player>>> CHAOSHROOM_HELMET_A =
            ARMOR_ITEMS.register("chaoshroom_helmet_a", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CHAOSHROOM_HELMET_A, ArmorItem.Type.HELMET, new Item.Properties().durability(150),
                    ChaoshroomHelmetAModel.class
            ));

    public static final Supplier<PlayerHuntingArmorItem<Player, ChaoshroomHelmetBModel<Player>>> CHAOSHROOM_HELMET_B =
            ARMOR_ITEMS.register("chaoshroom_helmet_b", () -> new PlayerHuntingArmorItem<>(
                    ModArmorMaterials.CHAOSHROOM_HELMET_B, ArmorItem.Type.HELMET, new Item.Properties().durability(150),
                    ChaoshroomHelmetBModel.class
            ));




    public static Function<EntityRendererProvider.Context, BaseHuntingArmorModel<Player>> makeDefaultModel() {
        return (EntityRendererProvider.Context context) -> new BaseHuntingArmorModel<>(context.bakeLayer(BaseHuntingArmorModel.DEFAULT_HUNTING_ARMOR_LAYER));
    }

    public static void register(IEventBus eventBus) {
        ARMOR_ITEMS.register(eventBus);
    }

}
