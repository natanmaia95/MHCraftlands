package com.nateplays.mhcraftlands.mixin;


import com.nateplays.mhcraftlands.hunter.armor.client.PlayerHuntingArmorLayer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandRenderer.class)
public abstract class ArmorStandRendererHuntingArmorLayerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityRendererProvider.Context context, CallbackInfo info) {
        ArmorStandRenderer renderer = (ArmorStandRenderer) (Object) this;

        renderer.addLayer(
                new PlayerHuntingArmorLayer<>(
                        renderer,
                        context)
        );
    }
}