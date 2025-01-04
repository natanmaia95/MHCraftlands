package com.nateplays.mhcraftlands.pet.item;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.PetToolPreference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class PetToolItem<T extends HuntingBuddyEntity> extends Item {
    public enum UseBehavior {
        STAY, FLEE_ENEMY, REACH_ENEMY, REACH_OWNER
    }

    protected final Class<T> entityClass;

    protected List<PetToolPreference> preferences;
    protected int basePointCost;

    public PetToolItem(Class<T> entityClass, List<PetToolPreference> preferences, int basePointCost, int durability, Properties properties) {
        super(durability > 0 ? properties.durability(durability) : properties);
        this.entityClass = entityClass;
        this.preferences = preferences;
        this.basePointCost = basePointCost;
    }

    public UseBehavior getUseBehavior() {
        return UseBehavior.FLEE_ENEMY;
    }

    public boolean canUsePetTool(ItemStack stack, LivingEntity entity) {
        boolean isCorrectEntity = false;
        isCorrectEntity = (entity instanceof Player) || (entityClass.isInstance(entity));

        if (!isCorrectEntity) return false;
        return this.isDamageable(stack);
    }

    public int getPointCost(LivingEntity entity) {
        int cost = basePointCost;
        if (entity instanceof HuntingBuddyEntity buddyEntity) {
            if (preferences.contains(buddyEntity.getToolPreference())) cost /= 2;
        }
        return cost;
    }

    //    Tool can't break past 1 durability
    @Override
    public boolean isDamageable(ItemStack stack) {
        return super.isDamageable(stack) && (stack.getDamageValue() < stack.getMaxDamage()-1);
    }

    public boolean canEquipPetTool(ItemStack stack, LivingEntity entity) {
        return entityClass.isInstance(entity);
    }



    //Test Implementations

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 60;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        this.hurtUsedItem(1, livingEntity);

        if (livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 20);
        }

        System.out.println("Finished using pet tool.");
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.GUST,
                    livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(),
            3, livingEntity.getRandom().nextDouble(),0,livingEntity.getRandom().nextDouble(),1.0);
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (this.canUsePetTool(stack, player)) {
            player.startUsingItem(usedHand);
            return InteractionResultHolder.consume(stack);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(),
                    5, livingEntity.getRandom().nextDouble(),0,livingEntity.getRandom().nextDouble(),1.0);
        }

        System.out.println("using tool");
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
//        return super.getUseAnimation(stack);
    }

    public void hurtUsedItem(int amount, LivingEntity livingEntity) {
        ItemStack usedStack = livingEntity.getUseItem();
        if (this.isDamageable(usedStack)) {
            livingEntity.getUseItem().hurtAndBreak(amount, livingEntity,
                    livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        MutableComponent affinityComponent = Component.empty();
        affinityComponent.append(Component.literal("[%d◆] ".formatted(this.basePointCost)));

        if (this.preferences.isEmpty()) affinityComponent.append(Component.translatable("entity.mhcraftlands.tool_preference.no_prefs"));
        else {
            affinityComponent.append(Component.translatable("entity.mhcraftlands.tool_preference.prefs")
                    .append(Component.literal(": ")));
            Iterator<PetToolPreference> iter = this.preferences.iterator();
            while (iter.hasNext()) {
                affinityComponent.append(Component.translatable(PetToolPreference.getTranslationKey(iter.next())));
                if (iter.hasNext()) affinityComponent.append(Component.literal(" "));
            }
        }
        affinityComponent = affinityComponent.withStyle(ChatFormatting.BLUE);
        tooltipComponents.add(affinityComponent);

        MutableComponent loreComponent;
        TranslatableContents loreContents = null;
        String specificDescKey = "item.%s.desc".formatted(BuiltInRegistries.ITEM.getKey(this));
        if (!Language.getInstance().getOrDefault(specificDescKey, "").equals("")) {
            loreContents = new TranslatableContents(specificDescKey, null, TranslatableContents.NO_ARGS);
            loreComponent = MutableComponent.create(loreContents);
        } else loreComponent = Component.literal("No lore.");

        loreComponent = loreComponent.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
        tooltipComponents.add(loreComponent);
    }
}
