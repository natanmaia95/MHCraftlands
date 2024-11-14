package com.nateplays.my_neoforge_mod.item.weapons;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.item.ModToolTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModWeaponItems {

    public static final DeferredRegister.Items WEAPON_ITEMS = DeferredRegister.createItems(MyNeoForgeMod.MODID);




    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_KAMURA_BOKKEN = WEAPON_ITEMS.register("f_kamura_bokken",
            () -> new PetHuntingWeaponItem<PalicoEntity>(ModToolTiers.F_KAMURA_BOKKEN,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(ModToolTiers.F_KAMURA_BOKKEN)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_BONE_PICK = WEAPON_ITEMS.register("f_bone_pick",
            () -> new PetHuntingWeaponItem<PalicoEntity>(ModToolTiers.F_BONE_PICK,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(ModToolTiers.F_BONE_PICK)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_BONE_HAMMER = WEAPON_ITEMS.register("f_bone_hammer",
            () -> new PetHuntingWeaponItem<PalicoEntity>(ModToolTiers.F_BONE_HAMMER,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(ModToolTiers.F_BONE_HAMMER)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_IRON_SWORD = WEAPON_ITEMS.register("f_iron_sword",
            () -> new PetHuntingWeaponItem<PalicoEntity>(ModToolTiers.F_IRON_SWORD,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(ModToolTiers.F_IRON_SWORD)), PalicoEntity.class));




    public static void register(IEventBus eventBus) {
        WEAPON_ITEMS.register(eventBus);
    }
}
