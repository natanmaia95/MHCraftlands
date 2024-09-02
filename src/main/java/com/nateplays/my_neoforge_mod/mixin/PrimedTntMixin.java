package com.nateplays.my_neoforge_mod.mixin;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.enchantment.ModEnchantmentHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimedTnt.class)
public abstract class PrimedTntMixin {

    @Shadow
    private boolean usedPortal; // Shadow the usedPortal field

    @Shadow
    private static ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR; // Shadow the static USED_PORTAL_DAMAGE_CALCULATOR field

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void modifyExplosionStrength(CallbackInfo info) {
        PrimedTnt tnt = (PrimedTnt) (Object) this;
        if (tnt.getOwner() instanceof Player player) {
            Holder<Enchantment> enchantmentHolder = ModEnchantmentHelper.getEnchantmentFromLocation(
                    ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, "bombardier"), player.level());

            int enchantmentLevel = ModEnchantmentHelper.getTotalEnchantmentLevel(player, enchantmentHolder);

            if (enchantmentLevel > 0) {
                // Get the world's explosion
                float baseStrength = 4.0F; // Default TNT explosion strength
                float newStrength = baseStrength + enchantmentLevel * 2.0F; // Example: Increase by 0.5 per level

                // Modify the explosion
                tnt.level()
                        .explode(
                        tnt,
                        Explosion.getDefaultDamageSource(tnt.level(), tnt),
                        this.usedPortal ? USED_PORTAL_DAMAGE_CALCULATOR : null,
                        tnt.getX(),
                        tnt.getY(0.0625),
                        tnt.getZ(),
                        newStrength,
                        false,
                        Level.ExplosionInteraction.TNT
                );

                // Cancel the original explosion
                info.cancel();
            }
        }
    }
}