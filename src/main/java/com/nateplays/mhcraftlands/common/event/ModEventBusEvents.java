package com.nateplays.mhcraftlands.common.event;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.client.ModModelLayers;
import com.nateplays.mhcraftlands.entity.client.MosswineModel;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_LAYER, MosswineModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_GOLD_LAYER, MosswineModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.PLAYER_DEFAULT_HUNTING_ARMOR_LAYER,
                () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.3F)), 64, 32));
    }


    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

        ItemColor huntingArmorItemColorFunc = (itemStack, tintLayer) -> tintLayer != 1 ? -1 : DyedItemColor.getOrDefault(itemStack, 0);
//        event.register(huntingArmorItemColorFunc, ModArmorItems.F_GHOST_HELM);
//        event.register(huntingArmorItemColorFunc, ModArmorItems.F_GHOST_MAIL);
//        event.register(huntingArmorItemColorFunc, ModArmorItems.F_RED_HELM);
//        event.register(huntingArmorItemColorFunc, ModArmorItems.F_RED_MAIL);
        MHPetArmorItems.DYEABLE_ARMORS_LIST.forEach(itemHolder -> {
            event.register(huntingArmorItemColorFunc, itemHolder.value());
        });

//        Logger LOGGER = LogUtils.getLogger();
//        BuiltInRegistries.ITEM.getTagOrEmpty(ModTags.Items.DYEABLE_HUNTING_ARMORS).forEach(itemHolder -> {
//            LOGGER.debug("COL " + itemHolder.value().toString());
//            event.register(huntingArmorItemColorFunc, itemHolder.value());
//        });
    }
}
