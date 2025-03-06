package com.nateplays.mhcraftlands.common.attribute;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.ModEntities;
import com.nateplays.mhcraftlands.entity.custom.MosswineEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MHMod.MOD_ID)
public class ModAttributeHandler {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MOSSWINE.get(), MosswineEntity.createAttributes().build());
    }

    public static AttributeSupplier.Builder createPlayerHuntAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(ModAttributes.EATING_SPEED, ModAttributes.EATING_SPEED.get().getDefaultValue())
                .add(ModAttributes.DEFENSE, ModAttributes.DEFENSE.get().getDefaultValue());

    }

    @SubscribeEvent
    public static void entityAttributeModificationEvent(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.DEFENSE);
        event.add(EntityType.PLAYER, ModAttributes.EATING_SPEED);


        //add to all living entities.
//        BuiltInRegistries.ENTITY_TYPE.forEach((entityType) -> {
//            System.out.println("entity type:" + entityType.toString());
//            if (entityType.getBaseClass().isAssignableFrom(LivingEntity.class)) {
//                EntityType<? extends LivingEntity> livingType = (EntityType<? extends LivingEntity>) entityType;
//                if (DefaultAttributes.getSupplier(livingType) == null) return;
//
//                //add all elemental weaknesses at default 1.0
//                for (DeferredHolder<Attribute, Attribute> weaknessHolder : ModAttributes.allElemWeaknesses()) {
//                    if (!event.has(livingType, weaknessHolder)) {
//                        event.add(livingType, weaknessHolder);
//                    }
//                }
//            }
//        });

        for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE) {
            // Check if the entity is a LivingEntity (only LivingEntities have attributes)
            System.out.println("entity type:" + entityType.toString());
            if (entityType.getBaseClass().isAssignableFrom(LivingEntity.class)) {
                EntityType<? extends LivingEntity> livingType = (EntityType<? extends LivingEntity>) entityType;
                // Add your custom attributes to the entity type
//                System.out.println("living type:" + entityType.toString());
                    event.add(livingType, ModAttributes.DEFENSE);
                    event.add(livingType, ModAttributes.FIRE_WEAKNESS);
                    event.add(livingType, ModAttributes.WATER_WEAKNESS);
                    event.add(livingType, ModAttributes.FIRE_DAMAGE);
//                event.add(entityType, ModAttributes.THUNDER_WEAKNESS);
                    // Add more attributes as needed
            }
        }

        event.add(EntityType.ZOMBIE, ModAttributes.DEFENSE);
        event.add(EntityType.ZOMBIE, ModAttributes.FIRE_WEAKNESS);
    }
}
