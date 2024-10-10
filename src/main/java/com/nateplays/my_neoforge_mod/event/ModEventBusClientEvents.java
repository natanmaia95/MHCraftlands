package com.nateplays.my_neoforge_mod.event;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.client.MosswineModel;
import com.nateplays.my_neoforge_mod.entity.interfaces.ILevelableEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.FBoneHelmModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.FFrankieModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.FGhostHelmModel;
import com.nateplays.my_neoforge_mod.entity.pets.client.armor.FMosgharlModel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;
import java.util.function.Predicate;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_LAYER, MosswineModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOSSWINE_GOLD_LAYER, MosswineModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.PALICO_LAYER, PalicoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PALICO_ARMOR_LAYER, PalicoModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.F_BONE_HELM_LAYER, FBoneHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_GHOST_HELM_LAYER, FGhostHelmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.F_MOSGHARL_LAYER, FMosgharlModel::createBodyLayer);
        event.registerLayerDefinition(FFrankieModel.LAYER_LOCATION, FFrankieModel::createBodyLayer);
    }
}
