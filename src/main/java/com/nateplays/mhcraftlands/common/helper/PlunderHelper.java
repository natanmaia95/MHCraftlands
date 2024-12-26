package com.nateplays.mhcraftlands.common.helper;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class PlunderHelper {

    public static List<ItemStack> getMobDrops(LivingEntity target) {
        return getMobDrops(target, null);
    }

    public static List<ItemStack> getMobDrops(LivingEntity target, @Nullable LivingEntity attacker) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return List.of();

        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(target.getLootTable());
        LootParams.Builder builder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.THIS_ENTITY, target)
                .withParameter(LootContextParams.ORIGIN, target.position());

        if (attacker == null) {
            builder = builder.withParameter(LootContextParams.DAMAGE_SOURCE, target.damageSources().genericKill());
        } else {
            DamageSource damageSource;
            if (attacker instanceof Player player) damageSource = attacker.damageSources().playerAttack(player);
            else damageSource = attacker.damageSources().mobAttack(attacker);
            builder = builder
                    .withParameter(LootContextParams.ATTACKING_ENTITY, attacker)
                    .withParameter(LootContextParams.DAMAGE_SOURCE, damageSource);
        }

        List<ItemStack> dropsList = lootTable.getRandomItems(builder.create(LootContextParamSets.ENTITY), serverLevel.getRandom());
        Collections.shuffle(dropsList);
        return dropsList;
    }
}
