package com.nateplays.my_neoforge_mod.mixin;

import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemInHandRenderer.class)
public interface ItemInHandRendererAccessor {


    @Accessor("mainHandItem")
    void setMainHandItem(ItemStack value);

    // This creates a setter for the private field exampleValue
    @Accessor("offHandItem")
    void setOffHandItem(ItemStack value);

    @Accessor("offHandHeight")
    void setOffHandHeight(float value);

    @Accessor("mainHandHeight")
    float mainHandHeight();
}