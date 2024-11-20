package com.nateplays.mhcraftlands.mixin;

import net.minecraft.world.item.ArmorMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.world.item.ArmorMaterial$Layer")
public interface ArmorMaterialLayerAccessor {

    @Accessor("suffix")
    String suffix();
}
