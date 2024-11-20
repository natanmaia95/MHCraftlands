package com.nateplays.mhcraftlands.component;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(MHMod.MOD_ID);



    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES =
            register("coordinates", builder -> builder.persistent(BlockPos.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HumanoidArm>> FAKE_RENDER_HAND =
            register("fake_render_hand", builder -> builder.persistent(HumanoidArm.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> HAND_SWING =
            register("hand_swing", builder -> builder.persistent(ExtraCodecs.POSITIVE_INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> WEAPON_CHARGE =
            register("weapon_charge", builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT));



    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
