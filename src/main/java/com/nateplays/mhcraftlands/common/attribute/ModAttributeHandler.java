package com.nateplays.mhcraftlands.common.attribute;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.ModEntities;
import com.nateplays.mhcraftlands.entity.custom.MosswineEntity;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

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
//        BuiltInRegistries.ENTITY_TYPE.forEach((entityType) -> {
//            event.add((EntityType<? extends LivingEntity>) entityType, ModAttributes.DEFENSE);
//        });

        event.add(EntityType.PLAYER, ModAttributes.FIRE_DAMAGE);
//        event.add(EntityType., ModAttributes.DEFENSE);

        event.add(EntityType.PLAYER, ModAttributes.EATING_SPEED);
    }
}
