package com.nateplays.my_neoforge_mod.enchantment.event;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.world.level.Explosion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ExplosionEvent;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class BombardierEventHandler {


    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        Explosion explosion = event.getExplosion();
    }
}
