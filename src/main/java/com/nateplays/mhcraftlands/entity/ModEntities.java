package com.nateplays.mhcraftlands.entity;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.custom.MosswineEntity;
import com.nateplays.mhcraftlands.entity.pets.Felyne;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MHMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<MosswineEntity>> MOSSWINE =
            ENTITY_TYPES.register("mosswine", () -> EntityType.Builder.of(MosswineEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("mosswine")
            );

//    public static final DeferredHolder<EntityType<?>, EntityType<PalicoEntity>> PALICO =
//            ENTITY_TYPES.register("palico", () -> EntityType.Builder.of(PalicoEntity::new, MobCategory.CREATURE)
//                    .sized(0.5f, 0.8f).build("palico")
//            );

    public static final DeferredHolder<EntityType<?>, EntityType<Felyne>> FELYNE =
            ENTITY_TYPES.register("felyne", () -> EntityType.Builder.of(Felyne::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.8f).build("felyne")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
