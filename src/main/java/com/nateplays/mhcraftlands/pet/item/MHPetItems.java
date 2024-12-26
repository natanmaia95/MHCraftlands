package com.nateplays.mhcraftlands.pet.item;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.effect.ModEffects;
import com.nateplays.mhcraftlands.item.custom.SummonFelyneItem;
import com.nateplays.mhcraftlands.pet.entity.HuntingBuddyEntity;
import com.nateplays.mhcraftlands.pet.entity.MHPetEntities;
import com.nateplays.mhcraftlands.pet.entity.PalicoEntity;
import com.nateplays.mhcraftlands.pet.item.custom.PetTrainingBookItem;
import com.nateplays.mhcraftlands.pet.item.tool.*;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.List;

public class MHPetItems {
    public static final DeferredRegister.Items PET_ITEMS = DeferredRegister.createItems(MHMod.MOD_ID);


    public static final DeferredItem<SpawnEggItem> FELYNE_SPAWN_EGG = PET_ITEMS.register("felyne_spawn_egg",
            () -> new SpawnEggItem(MHPetEntities.FELYNE.get(), 0xdecc99, 0x453e2a, new Item.Properties()));
    //TODO: learn how to use DeferredSpawnEggItem

    public static final DeferredItem<SummonFelyneItem> SUMMON_FELYNE_VOUCHER = PET_ITEMS.register("summon_felyne_voucher", SummonFelyneItem::new);
    public static final DeferredItem<Item> DISMISS_BUDDY_VOUCHER = PET_ITEMS.register("dismiss_buddy_voucher",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<PetTrainingBookItem> TRAINING_BOOK = PET_ITEMS.register("training_book",
            () -> new PetTrainingBookItem(new Item.Properties()));

    public static final DeferredItem<HornPetTool> F_HERB_HORN = PET_ITEMS.register("f_herb_horn",
            () -> new HornPetTool(PalicoEntity.class, 150, new Item.Properties()));
    public static final DeferredItem<HornPetTool> F_DEMON_HORN = PET_ITEMS.register("f_demon_horn",
            () -> new HornPetTool(PalicoEntity.class, 150, new Item.Properties()) {
                @Override public void applyActualEffectToEntity(LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(ModEffects.ATTACK_BOOST_BUFF, 20*60*3, 2));
                }
            });
    public static final DeferredItem<HornPetTool> F_ARMOR_HORN = PET_ITEMS.register("f_armor_horn",
            () -> new HornPetTool(PalicoEntity.class, 150, new Item.Properties()) {
                @Override public void applyActualEffectToEntity(LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60*3, 0));
                }
            });
    public static final DeferredItem<HornPetTool> F_PLUNDER_HORN = PET_ITEMS.register("f_plunder_horn",
            () -> new HornPetTool(PalicoEntity.class, 20, new Item.Properties()) {
                @Override public void applyActualEffectToEntity(LivingEntity livingEntity) {
                    if (livingEntity.level() instanceof ServerLevel serverLevel) {
                        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(livingEntity.getLootTable());
                        LootParams.Builder builder = new LootParams.Builder(serverLevel)
                                .withParameter(LootContextParams.THIS_ENTITY, livingEntity)
                                .withParameter(LootContextParams.DAMAGE_SOURCE, livingEntity.damageSources().genericKill())
                                .withParameter(LootContextParams.ORIGIN, livingEntity.position());

//                                .withParameter(LootContextParams.ATTACKING_ENTITY, livingEntity)
//                                .withRandom(mob.getRandom());

                        List<ItemStack> dropsList = lootTable.getRandomItems(builder.create(LootContextParamSets.ENTITY), serverLevel.getRandom());
                        Collections.shuffle(dropsList);
//                        System.out.println(dropsList.toString());
                        if (!dropsList.isEmpty()) {
                            ItemStack drop = dropsList.getFirst();
                            drop.setCount(1);
                            livingEntity.spawnAtLocation(drop);
                        }
                    }
                }

                @Override
                public boolean isValidTarget(LivingEntity entity) {
                    if (!(entity instanceof Mob mob)) return false;
                    if (HuntingBuddyEntity.ALLIED_TO_HUNTERS_SELECTOR.test(entity)) return false;
                    return true;
                }
            });

    public static final DeferredItem<EmergencyRetreatPetTool> F_EMERGENCY_RETREAT_KIT = PET_ITEMS.register("f_emergency_retreat_kit",
            () -> new EmergencyRetreatPetTool<>(PalicoEntity.class, 10, new Item.Properties()));

    public static final DeferredItem<TauntPetTool> F_TAUNT_SHIELD = PET_ITEMS.register("f_taunt_shield",
            () -> new TauntPetTool<>(PalicoEntity.class, 10, new Item.Properties()));
    public static final DeferredItem<FurbiddenAcornPetTool> F_FURBIDDEN_ACORN = PET_ITEMS.register("f_furbidden_acorn",
            () -> new FurbiddenAcornPetTool<>(PalicoEntity.class, new Item.Properties().stacksTo(4)));
    public static final DeferredItem<SumoStompPetTool> F_SUMO_STOMP = PET_ITEMS.register("f_sumo_stomp",
            () -> new SumoStompPetTool<>(PalicoEntity.class, 100, new Item.Properties()));


    public static final DeferredItem<Item> SCRAP_WOOD = registerScrapItem("wood");
    public static final DeferredItem<Item> SCRAP_BONE = registerScrapItem("bone");
    public static final DeferredItem<Item> SCRAP_ORE = registerScrapItem("ore");
    public static final DeferredItem<Item> SCRAP_FUR = registerScrapItem("fur");
    public static final DeferredItem<Item> SCRAP_HUMBLE = registerScrapItem("humble");
    public static final DeferredItem<Item> SCRAP_SINISTER = registerScrapItem("sinister");




    protected static DeferredItem<Item> registerScrapItem(String scrapName) {
        return PET_ITEMS.register(scrapName + "_scrap", () -> new Item(new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                TranslatableContents loreContents = null;
                String genericDescKey = "item.%s.generic_scrap.desc".formatted(MHMod.MOD_ID);
                String specificDescKey = "item.%s.%s_scrap.desc".formatted(MHMod.MOD_ID, scrapName);
                if (Language.getInstance().getOrDefault(specificDescKey, "") != "") {
                    loreContents = new TranslatableContents(specificDescKey, null, TranslatableContents.NO_ARGS);
                } else loreContents = new TranslatableContents(genericDescKey, null, TranslatableContents.NO_ARGS);

                MutableComponent loreComponent = MutableComponent.create(loreContents);
                loreComponent = loreComponent.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
                tooltipComponents.add(loreComponent);
            }
        });
    }




    public static void register(IEventBus eventBus) { PET_ITEMS.register(eventBus); }
}
