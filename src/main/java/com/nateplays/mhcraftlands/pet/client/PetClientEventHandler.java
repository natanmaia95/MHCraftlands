package com.nateplays.mhcraftlands.pet.client;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.client.ModModelLayers;
import com.nateplays.mhcraftlands.entity.client.MosswineModel;
import com.nateplays.mhcraftlands.pet.client.armor.*;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoRenderer;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PetClientEventHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MHPetEntities.FELYNE.get(), PalicoRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(PalicoModel.LAYER_LOCATION, PalicoModel::createBodyLayer);

        event.registerLayerDefinition(PalicoModel.LAYER_LOCATION, PalicoModel::createBodyLayer);
        event.registerLayerDefinition(PalicoModel.ARMOR_LAYER_LOCATION, PalicoModel::createBodyLayer);

        event.registerLayerDefinition(FDuffelModel.LAYER_LOCATION, FDuffelModel::createBodyLayer);
        event.registerLayerDefinition(FBoneHelmModel.LAYER_LOCATION, FBoneHelmModel::createBodyLayer);
        event.registerLayerDefinition(FCreeperModel.LAYER_LOCATION, FCreeperModel::createBodyLayer);
        event.registerLayerDefinition(FGhostHelmModel.LAYER_LOCATION, FGhostHelmModel::createBodyLayer);
        event.registerLayerDefinition(FRedModel.LAYER_LOCATION, FRedModel::createBodyLayer);
        event.registerLayerDefinition(FMosgharlModel.LAYER_LOCATION, FMosgharlModel::createBodyLayer);
        event.registerLayerDefinition(FFrankieModel.LAYER_LOCATION, FFrankieModel::createBodyLayer);
        event.registerLayerDefinition(FDiamondModel.LAYER_LOCATION, FDiamondModel::createBodyLayer);
    }
}
