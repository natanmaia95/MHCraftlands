package com.nateplays.mhcraftlands.pet.entity;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.weapon.HuntingWeaponItem;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@EventBusSubscriber(modid = MHMod.MOD_ID)
public class Melynx extends PalicoEntity {
    public Melynx(EntityType<? extends HuntingBuddyEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID, "textures/entity/pet/palico_melynx.png");
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //aggressive towards humans to steal items.
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 60, true, false, (Predicate) null) {
            @Override
            public boolean canUse() {
                if (Melynx.this.isTame()) return false;
                //has free slot from the 6 available? using an unstackable item to check
                if (!Melynx.this.getInventory().canAddItem(new ItemStack(Items.WOODEN_PICKAXE))) return false;
                return super.canUse();
            }
        });
        //flees if inventory is almost full or when just stole an item; use weakness to track item stealing
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 8.0F, 3.0, 2.0,
                e ->  this.hasEffect(MobEffects.WEAKNESS) || !(this.getInventory().getItem(4) == ItemStack.EMPTY)));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (!level.isClientSide()) {
            this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(MHPetWeaponItems.F_MELYNX_TOOL.get()));
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MHPetArmorItems.F_MELYNX_HELM.get()));
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MHPetArmorItems.F_MELYNX_MAIL.get()));
            this.setToolPreference(PetToolPreference.ASSISTANCE);

            if (this.random.nextFloat() < 0.1) this.getInventory().addItem(new ItemStack(Items.EMERALD).copyWithCount(this.random.nextInt(1,3)));
            if (this.random.nextFloat() < 0.2) this.getInventory().addItem(new ItemStack(Items.TNT).copyWithCount(this.random.nextInt(1,3)));
            if (this.random.nextFloat() < 0.3) this.getInventory().addItem(new ItemStack(Items.COOKED_COD).copyWithCount(this.random.nextInt(1,5)));
            if (this.random.nextFloat() < 0.3) this.getInventory().addItem(new ItemStack(Items.COOKED_SALMON).copyWithCount(this.random.nextInt(1,5)));
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }



    @Override
    public boolean isFood(ItemStack stack) { return false; }

    @Override
    public boolean isTameItem(ItemStack stack) { return stack.getItem() == Items.EMERALD; }

    @SubscribeEvent
    public static void onMelynxAttackEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player targetPlayer && event.getAmount() > 0) {
            if (event.getSource().getEntity() instanceof Melynx melynx) {
                if (melynx.random.nextFloat() > 0.3) return;
                //steal random item!
                List<ItemStack> validStealingItems = targetPlayer.getInventory().items.stream().filter((stack) -> {
                    if (stack.has(DataComponents.DAMAGE) || stack.has(DataComponents.TOOL)) return false;
                    if (stack.getItem() instanceof HuntingWeaponItem || stack.getItem() instanceof ArmorItem) return false;
                    if (stack.getRarity() == Rarity.RARE || stack.getRarity() == Rarity.EPIC) return false;
                    if (stack.has(DataComponents.FOOD)) return true;
                    return (stack.getMaxStackSize() >= 64);
                }).toList();
                if (validStealingItems.isEmpty()) return;
                ItemStack stackToSteal = validStealingItems.get(melynx.random.nextInt(validStealingItems.size()));
                //int slotToSteal = targetPlayer.getInventory().findSlotMatchingItem(stackToSteal);
                ItemStack stolenStack = stackToSteal.copyWithCount(1);
                if (!melynx.getInventory().canAddItem(stolenStack)) return;
                melynx.getInventory().addItem(stolenStack);
                stackToSteal.shrink(1);
                targetPlayer.displayClientMessage(Component.translatable("message.mhcraftlands.item_stolen", stolenStack.getDisplayName(), melynx.getDisplayName()), false);

                melynx.setTarget(null);
                melynx.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 0, false, false));
            }
        }
    }
}
