package com.nateplays.mhcraftlands.common.attribute;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.slf4j.Logger;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class CommonAttributeHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    // Default values

    @SubscribeEvent
    public static void entityJoinAttributeHandler(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
//            if (!livingEntity.getAttributes().hasAttribute(ModAttributes.FIRE_WEAKNESS)) {
                //this adds temporary modifiers (that go away? for some reason?) every time that entity spawns.
                //so that it triggers EntityAttributeModificationEvent and can receive the base attrs.
                livingEntity.getAttributes().addTransientAttributeModifiers(createElementalAttributeMap());
//            }
        }
    }

    private static Multimap<Holder<Attribute>, AttributeModifier> createElementalAttributeMap() {
        Multimap<Holder<Attribute>, AttributeModifier> attributes = HashMultimap.create();
        // all elemental weaknesses
        for (DeferredHolder<Attribute, Attribute> weaknessHolder : ModAttributes.allElemWeaknesses()) {
            attributes.put(weaknessHolder, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "default." + weaknessHolder.getId().getPath()),
                    0.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
        for (Holder<Attribute> resistanceHolder : ModAttributes.ELEMENT_DAMAGE_TO_RESISTANCE.values()) {
            attributes.put(resistanceHolder, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "default." + resistanceHolder.getKey().location().getPath()),
                    0.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
        // add more attributes as needed
        return attributes;
    }


    // Execution events


    @SubscribeEvent
    public static void livingEntityUseItemTick(LivingEntityUseItemEvent.Tick event) {
        handleAttributeEatingSpeed(event);
    }

    public static void handleAttributeEatingSpeed(LivingEntityUseItemEvent.Tick event) {
        LivingEntity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        if (entity instanceof Player player) {
            if (player.level().isClientSide()) return;
            double eatingSpeed = player.getAttributeValue(ModAttributes.EATING_SPEED);
            int adjustedDuration = itemStack.getUseDuration(entity) - (int) (itemStack.getUseDuration(entity) / eatingSpeed);
            //make use animation start ahead
            if (event.getDuration() == itemStack.getUseDuration(entity)) {
                if (!itemStack.useOnRelease()) {
                    event.setDuration((int) (itemStack.getUseDuration(entity) / eatingSpeed));
                }
            }
        }
    }

}
