package com.nateplays.mhcraftlands.pet.item.weapon;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.attribute.ModAttributes;
import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MHPetWeaponItems {
    public static final DeferredRegister.Items WEAPON_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);




    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_KAMURA_BOKKEN = WEAPON_ITEMS.register("f_kamura_bokken",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_KAMURA_BOKKEN, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_KAMURA_BOKKEN)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_TREKKER_PECKAXE = WEAPON_ITEMS.register("f_trekker_peckaxe",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_TREKKER_PECKAXE,  PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_TREKKER_PECKAXE)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_BONE_PICK = WEAPON_ITEMS.register("f_bone_pick",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_BONE_PICK,  PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_BONE_PICK)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_BONE_HAMMER = WEAPON_ITEMS.register("f_bone_hammer",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_BONE_HAMMER,  PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_BONE_HAMMER)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_IRON_SWORD = WEAPON_ITEMS.register("f_iron_sword",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_IRON_SWORD, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_IRON_SWORD)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_CREEPER_CLOBBERER = WEAPON_ITEMS.register("f_creeper_clobberer",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_CREEPER_CLOBBERER,  PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_CREEPER_CLOBBERER)
                            .withModifierAdded(ModAttributes.FIRE_DAMAGE, new AttributeModifier(
                                    HuntingWeaponItem.ELEM_DAMAGE_MODIFIER_ID, 0.5, AttributeModifier.Operation.ADD_VALUE
                            ), EquipmentSlotGroup.MAINHAND)
                    ), PalicoEntity.class));


    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_RED_BASKET = WEAPON_ITEMS.register("f_red_basket",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_RED_BASKET, PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_RED_BASKET)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_MOSGHARL_BROOM = WEAPON_ITEMS.register("f_mosgharl_broom",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_MOSGHARL_BROOM, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_MOSGHARL_BROOM)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_GHOST_LANTERN = WEAPON_ITEMS.register("f_ghost_lantern",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_GHOST_LANTERN, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_GHOST_LANTERN)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_FRANKIE_BALL = WEAPON_ITEMS.register("f_frankie_ball",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_FRANKIE_BALL, PetHuntingWeaponItem.Range.MELEE,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_FRANKIE_BALL)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_DIAMOND_SWORD = WEAPON_ITEMS.register("f_diamond_sword",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_DIAMOND_SWORD, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_DIAMOND_SWORD)), PalicoEntity.class));

    public static final DeferredItem<PetHuntingWeaponItem<PalicoEntity>> F_VALKYRIE_SWORD = WEAPON_ITEMS.register("f_valkyrie_sword",
            () -> new PetHuntingWeaponItem<PalicoEntity>(MHPetWeaponTiers.F_VALKYRIE_SWORD, PetHuntingWeaponItem.Range.RANGED,
                    new Item.Properties().attributes(
                            HuntingWeaponItem.createAttrsWithElement(ModAttributes.ICE_DAMAGE, 1.0f,
                                    PetHuntingWeaponItem.createAttributes(MHPetWeaponTiers.F_VALKYRIE_SWORD)
                            )
                    ), PalicoEntity.class));



    public static void register(IEventBus eventBus) {
        WEAPON_ITEMS.register(eventBus);
    }
}
