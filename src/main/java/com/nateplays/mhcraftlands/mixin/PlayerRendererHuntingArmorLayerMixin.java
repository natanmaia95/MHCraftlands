package com.nateplays.mhcraftlands.mixin;



import com.nateplays.mhcraftlands.hunter.armor.client.PlayerHuntingArmorLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererHuntingArmorLayerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo info) {
        PlayerRenderer playerRenderer = (PlayerRenderer) (Object) this;

        playerRenderer.addLayer(
                new PlayerHuntingArmorLayer<>(
                        playerRenderer,
                        context)
        );
    }
}