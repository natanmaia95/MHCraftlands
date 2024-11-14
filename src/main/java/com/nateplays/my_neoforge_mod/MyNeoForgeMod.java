package com.nateplays.my_neoforge_mod;

import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.component.ModDataComponents;
import com.nateplays.my_neoforge_mod.effect.ModEffects;
import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.entity.client.MosswineRenderer;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoRenderer;
import com.nateplays.my_neoforge_mod.gui.ModMenuTypes;
import com.nateplays.my_neoforge_mod.item.ModCreativeModeTabs;
import com.nateplays.my_neoforge_mod.item.ModItems;
import com.nateplays.my_neoforge_mod.item.armor.ModArmorItems;
import com.nateplays.my_neoforge_mod.item.armor.ModArmorMaterials;
import com.nateplays.my_neoforge_mod.item.weapons.DualBladesItem;
import com.nateplays.my_neoforge_mod.item.weapons.ModWeaponItems;
import com.nateplays.my_neoforge_mod.item.weapons.SwordAndShieldItem;
import com.nateplays.my_neoforge_mod.sound.ModSounds;
import com.nateplays.my_neoforge_mod.tags.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ItemLike;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MyNeoForgeMod.MODID)
public class MyNeoForgeMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "my_neoforge_mod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MyNeoForgeMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModArmorMaterials.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModSounds.register(modEventBus);

        ModItems.register(modEventBus);
        ModArmorItems.register(modEventBus);
        ModWeaponItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.FELYNE_SPAWN_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.MOSSWINE.get(), MosswineRenderer::new);
//            EntityRenderers.register(ModEntities.PALICO.get(), PalicoRenderer::new);
            EntityRenderers.register(ModEntities.FELYNE.get(), PalicoRenderer::new);

            // Predicates
            ModItems.ITEMS.getEntries().forEach((itemDeferredHolder) -> {
                Item item = itemDeferredHolder.get();
                if (item instanceof DualBladesItem || item instanceof SwordAndShieldItem) {
                    ItemProperties.register(item, ResourceLocation.fromNamespaceAndPath(MODID, "fake_render_hand"),
                        (itemStack, clientLevel, livingEntity, seed) -> {
                            if (livingEntity == null) return 0.0F;
                            HumanoidArm arm = itemStack.get(ModDataComponents.FAKE_RENDER_HAND);
                            if (arm == null) return 0.0F;
                            else if (arm == HumanoidArm.RIGHT) return 1.0F; //main
                            else if (arm == HumanoidArm.LEFT) return 2.0F; //off
                            return 0.0F;
                        });
                }
                if (item instanceof SwordAndShieldItem) {
                    ItemProperties.register(item, ResourceLocation.fromNamespaceAndPath(MODID, "blocking"),
                        (itemStack, clientLevel, livingEntity, seed) -> {
                            if (livingEntity == null) return 0.0F;
                            if (itemStack.get(ModDataComponents.FAKE_RENDER_HAND) == HumanoidArm.RIGHT) {
                                if (livingEntity.isUsingItem() && livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                                    return 1.0F;
                                }
                            }
                            return 0.0F;
                        });
                }
            });
        }
    }
}
