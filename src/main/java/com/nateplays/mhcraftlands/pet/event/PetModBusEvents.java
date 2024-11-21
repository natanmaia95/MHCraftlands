package com.nateplays.mhcraftlands.pet.event;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.ModEntities;
import com.nateplays.mhcraftlands.entity.client.ModModelLayers;
import com.nateplays.mhcraftlands.entity.client.MosswineModel;
import com.nateplays.mhcraftlands.pet.client.armor.*;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import com.nateplays.mhcraftlands.pet.gui.MHPetMenuTypes;
import com.nateplays.mhcraftlands.pet.gui.PalicoInventoryScreen;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PetModBusEvents {


    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(MHPetEntities.FELYNE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MHPetMenuTypes.PALICO_INVENTORY.get(), PalicoInventoryScreen::new);
    }
}
