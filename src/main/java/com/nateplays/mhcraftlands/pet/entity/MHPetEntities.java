package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MHPetEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MHMod.MOD_ID);




    public static final DeferredHolder<EntityType<?>, EntityType<Felyne>> FELYNE =
            ENTITY_TYPES.register("felyne", () -> EntityType.Builder.of(Felyne::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.8f).build("felyne")
            );




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
