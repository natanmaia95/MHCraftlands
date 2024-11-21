package com.nateplays.mhcraftlands.pet;

import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import com.nateplays.mhcraftlands.pet.gui.MHPetMenuTypes;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorMaterials;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.neoforged.bus.api.IEventBus;

public class MHPetSubRegistry {
    public static void register(IEventBus eventBus) {
        MHPetArmorMaterials.register(eventBus);
        MHPetMenuTypes.register(eventBus);

        MHPetItems.register(eventBus);
        MHPetArmorItems.register(eventBus);
        MHPetWeaponItems.register(eventBus);

        MHPetEntities.register(eventBus);
    }
}
