package com.nateplays.my_neoforge_mod.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class InteractRidingEntityMixin {

//    @Inject(method = "canRiderInteract;", at = @At("RETURN"), cancellable = true)
//    private void my_neoforge_mod_canRiderInteract(CallbackInfoReturnable<Boolean> info) {
//        info.setReturnValue(true);
//    }

    public boolean canRiderInteract() {
        return true;
    }
}
