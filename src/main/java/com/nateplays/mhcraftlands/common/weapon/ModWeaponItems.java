package com.nateplays.mhcraftlands.common.weapon;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.item.ModToolTiers;
import com.nateplays.mhcraftlands.pet.item.weapon.PetHuntingWeaponItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModWeaponItems {

    public static final DeferredRegister.Items WEAPON_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);





    public static void register(IEventBus eventBus) {
        WEAPON_ITEMS.register(eventBus);
    }
}
