package com.nateplays.mhcraftlands.pet.event;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MHMod.MOD_ID)
public class PetAttributeHandler {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MHPetEntities.FELYNE.get(), PalicoEntity.createAttributes().build()); //TODO: try moving this to entity itself?
    }
}
