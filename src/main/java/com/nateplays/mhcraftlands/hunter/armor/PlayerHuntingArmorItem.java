package com.nateplays.mhcraftlands.hunter.armor;

import com.nateplays.mhcraftlands.common.armor.HuntingArmorItem;
import com.nateplays.mhcraftlands.common.client.rendering.BaseHuntingArmorModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Function;

public class PlayerHuntingArmorItem<T extends Player, M extends BaseHuntingArmorModel<T>> extends HuntingArmorItem implements IClientItemExtensions {

    Function<EntityRendererProvider.Context, ? extends M> modelFunction; //initializes as null

    public PlayerHuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, Function<EntityRendererProvider.Context, ? extends M> modelFunc) {
        super(material, type, properties);
        this.modelFunction = modelFunc;
    }

    public PlayerHuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, Class<? extends M> modelClass) {
        super(material, type, properties);
        this.modelFunction = createModelFactory(modelClass);
    }

//    public static makeModelFunc(Class class) {
//
//    }

    //for making models
    public static <T extends BaseHuntingArmorModel<?>> Function<EntityRendererProvider.Context, T> createModelFactory(Class<T> modelClass) {
        return context -> {
            try {
                return modelClass.getDeclaredConstructor(ModelPart.class)
                        .newInstance(context.bakeLayer((ModelLayerLocation) modelClass.getField("LAYER_LOCATION").get(null)));
                // Instantiate the model and get its layer location
//                return modelClass.getDeclaredConstructor(ModelPart.class)
//                        .newInstance(context.bakeLayer(modelClass.getDeclaredConstructor().newInstance().getLayerLocation()));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create model instance for " + modelClass.getName(), e);
            }
        };
    }

    public M getArmorModel(EntityRendererProvider.Context context) {
        return this.modelFunction.apply(context);
    }
}
