package com.nateplays.my_neoforge_mod.event;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.client.MosswineModel;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.*;
import com.nateplays.my_neoforge_mod.entity.pets.gui.PalicoInventoryScreen;
import com.nateplays.my_neoforge_mod.gui.ModMenuTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_LAYER, MosswineModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_GOLD_LAYER, MosswineModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.PALICO_LAYER, PalicoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PALICO_ARMOR_LAYER, PalicoModel::createBodyLayer);

        event.registerLayerDefinition(FRedModel.LAYER_LOCATION, FRedModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_BONE_HELM_LAYER, FBoneHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_GHOST_HELM_LAYER, FGhostHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_MOSGHARL_LAYER, FMosgharlModel::createBodyLayer);
        event.registerLayerDefinition(FFrankieModel.LAYER_LOCATION, FFrankieModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.FELYNE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PALICO_INVENTORY.get(), PalicoInventoryScreen::new);
    }
}
