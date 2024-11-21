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
        event.registerLayerDefinition(ModModelLayers.PALICO_LAYER, PalicoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PALICO_ARMOR_LAYER, PalicoModel::createBodyLayer);

        event.registerLayerDefinition(FRedModel.LAYER_LOCATION, FRedModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_BONE_HELM_LAYER, FBoneHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_GHOST_HELM_LAYER, FGhostHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_MOSGHARL_LAYER, FMosgharlModel::createBodyLayer);
        event.registerLayerDefinition(FFrankieModel.LAYER_LOCATION, FFrankieModel::createBodyLayer);
    }
}
