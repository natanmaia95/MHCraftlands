package com.nateplays.mhcraftlands.common.armor;

import com.nateplays.mhcraftlands.entity.client.ModModelLayers;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Function;

public class PlayerHuntingArmorItem<T extends Player, M extends HumanoidModel<T>> extends HuntingArmorItem implements IClientItemExtensions {

    Function<EntityRendererProvider.Context, ? extends M> modelFunction; //initializes as null

    public PlayerHuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, Function<EntityRendererProvider.Context, ? extends M> modelFunc) {
        super(material, type, properties);
        this.modelFunction = modelFunc;
    }

    public M getArmorModel(EntityRendererProvider.Context context) {
        return this.modelFunction.apply(context);
    }
}
