package com.nateplays.mhcraftlands.common.skill.event;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.skill.ModEnchantmentHelper;
import com.nateplays.mhcraftlands.common.skill.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class MushroomancerInteractEvent {

//    private static final Map<Block, Block> CHISEL_MAP = Map.of(
//            Blocks.STONE, Blocks.STONE_BRICKS,
//            ModBlocks.MACHALITE_ORE.get(), ModBlocks.MACHALITE_BLOCK.get()
//    );

    private static final List<Item> COMPATIBLE_ITEMS = List.of(
            Items.RED_MUSHROOM, Items.BROWN_MUSHROOM,
            Items.WARPED_FUNGUS, Items.CRIMSON_FUNGUS
    );

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (!COMPATIBLE_ITEMS.contains(item)) return; //gate clause

        Player player = event.getEntity();
        Holder<Enchantment> enchantmentHolder = ModEnchantmentHelper.getEnchantmentFromKey(ModEnchantments.MUSHROOMANCER, player.level());
        int mushroomancerLevel = ModEnchantmentHelper.getTotalEnchantmentLevel(player, enchantmentHolder);
        if (mushroomancerLevel == 0) return;
        boolean hasEaten = false;

        if (mushroomancerLevel >= 2) {
            if (item == Items.WARPED_FUNGUS) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60));
                hasEaten = true;
            } else if (item == Items.CRIMSON_FUNGUS) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20*60));
                hasEaten = true;
            }
        }

        if (mushroomancerLevel >= 1) {
            if (item == Items.RED_MUSHROOM) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300));
                hasEaten = true;
            } else if (item == Items.BROWN_MUSHROOM) {
                player.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));
                hasEaten = true;
            }
        }

        if (hasEaten) {
            if (!player.level().isClientSide()) {
                stack.consume(1, player);
                player.getCooldowns().addCooldown(item, 200);

            } else {
                player.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
            }
        }
    }
}
