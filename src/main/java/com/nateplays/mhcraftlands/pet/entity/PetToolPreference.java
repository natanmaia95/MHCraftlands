package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.util.RandomSource;

import java.util.Arrays;
import java.util.Comparator;

public enum PetToolPreference {
    NONE(0),
    HEALING(1),
    FIGHTING(2),
    PROTECTION(3),
    GATHERING(4),
    BOMBING(5),
    ASSISTANCE(6);

    /* Tool preferences:
    *
    * Healing buddies keep hunters and other buddies in good shape.
    *
    * Fighting buddies will keep up the pressure on enemies.
    *
    * Protection buddies try to pull monsters and their aggro away from the hunter.
    *
    * Gathering buddies attempt to gather extra materials from monsters and the field.
    *
    * Bombing buddies will use a variety of explosives during hunts.
    *
    * Assistance buddies make the hunt easier, with buffs and traps.
    *
    * Other types may be added.
    * */

    private static final PetToolPreference[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PetToolPreference::getId)).toArray(PetToolPreference[]::new);
    private final int id;

    public static PetToolPreference byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static PetToolPreference getRandom(RandomSource randomSource) {
        return BY_ID[randomSource.nextInt(BY_ID.length-1)+1]; //-1 +1 to remove 0
    }

    public static String getTranslationKey(PetToolPreference pref) {
        return "entity.%s.tool_preference.%d".formatted(MHMod.MOD_ID, pref.id);
    }

    PetToolPreference(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }
}
