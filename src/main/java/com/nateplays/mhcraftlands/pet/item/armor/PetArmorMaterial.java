package com.nateplays.mhcraftlands.pet.item.armor;

import com.nateplays.mhcraftlands.entity.client.ModModelLayers;
import com.nateplays.mhcraftlands.pet.client.rendering.PalicoModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Function;

public class PetArmorMaterial<A extends Model> {
    public Holder<ArmorMaterial> material;
    public Class<Model> modelClass;
    Function<EntityRendererProvider.Context, ? extends A> modelFunction;

    public PetArmorMaterial(Holder<ArmorMaterial> material, Function<EntityRendererProvider.Context, ? extends A> modelFunc) {
        this.material = material;
        this.modelFunction = modelFunc;
    }

    public static PetArmorMaterial newPalicoArmorMaterial(Holder<ArmorMaterial> material, Function<EntityRendererProvider.Context, ? extends Model> modelFunc) {
        if (modelFunc == null) {
            modelFunc = (EntityRendererProvider.Context context) -> new PalicoModel<>(context.bakeLayer(ModModelLayers.PALICO_LAYER));
        }
        return new PetArmorMaterial(material, modelFunc);
    }

}
