package com.nateplays.my_neoforge_mod.item.armor;

import com.nateplays.my_neoforge_mod.entity.client.ModModelLayers;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import com.nateplays.my_neoforge_mod.entity.pets.client.PalicoModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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
