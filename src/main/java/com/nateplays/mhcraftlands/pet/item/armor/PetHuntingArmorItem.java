package com.nateplays.mhcraftlands.pet.item.armor;

import com.nateplays.mhcraftlands.common.armor.HuntingArmorItem;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetHuntingArmorItem<T extends Animal, A extends Model> extends HuntingArmorItem {
    static final Logger LOGGER = LoggerFactory.getLogger(PetHuntingArmorItem.class);

    public final PetArmorMaterial petArmorMaterial;
    private final Class<T> entityClass;



    public PetHuntingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, Class<T> wearerClass) {
        super(material, type, properties);
        this.entityClass = wearerClass;
        this.petArmorMaterial = new PetArmorMaterial(material, null);
    }

    //Constructor that accepts custom models
    public PetHuntingArmorItem(PetArmorMaterial petArmorMat, Type type, Properties properties, Class<T> wearerClass) {
        super(petArmorMat.material, type, properties);
        this.entityClass = wearerClass;
        this.petArmorMaterial = petArmorMat;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
        LOGGER.debug("is instance? " + String.valueOf(entityClass.isInstance(entity)));
        if (entityClass.isInstance(entity)) return super.canEquip(stack, armorType, entity);
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (canEquip(stack, this.getEquipmentSlot(), player)) return this.swapWithEquipmentSlot(this, level, player, hand);
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }

    public A getArmorModel(EntityRendererProvider.Context context) {
        if (petArmorMaterial.modelFunction == null) return null;
        A armorModel = (A) petArmorMaterial.modelFunction.apply(context);
//        LogUtils.getLogger().debug(armorModel.toString());
        return armorModel;
    }

    public boolean hideHead() { return false;}
    public boolean hideEars() { return false; }
    public boolean hideArms() { return false; }
    public boolean hideLegs() { return false; }
    public boolean hideTail() { return false; }
}
