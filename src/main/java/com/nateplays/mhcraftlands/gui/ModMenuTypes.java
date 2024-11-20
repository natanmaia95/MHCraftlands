package com.nateplays.mhcraftlands.gui;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.entity.pets.gui.PalicoInventoryMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU, MHMod.MOD_ID);


    public static final Supplier<MenuType<PalicoInventoryMenu>> PALICO_INVENTORY = MENU_TYPES.register(
            "palico_inventory", () -> IMenuTypeExtension.create(PalicoInventoryMenu::new));


    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
