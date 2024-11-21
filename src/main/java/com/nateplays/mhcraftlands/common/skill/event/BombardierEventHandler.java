package com.nateplays.mhcraftlands.common.skill.event;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.world.level.Explosion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ExplosionEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class BombardierEventHandler {


    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        Explosion explosion = event.getExplosion();
    }
}
