package com.nateplays.mhcraftlands.common.weapon;

import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class WeaponTooltipHandler {

    @SubscribeEvent
    public static void onTooltipRender(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof HuntingWeaponItem) {
            var elemDamageTooltips = event.getTooltipElements().stream().filter(element -> {
                if (element instanceof TooltipComponent tooltipComponent) {
                    return true;
                }
                return false;
            }).toList();
        }
    }


}