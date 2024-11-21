package com.nateplays.mhcraftlands.hunter.weapon;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.component.ModDataComponents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

//@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MHMod.MOD_ID, value = Dist.CLIENT)
public class WeaponRenderArmHandler {

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        if (event.getRenderer().getModel() instanceof HumanoidModel<LivingEntity> humanoidModel) {
            LivingEntity livingEntity = event.getEntity();
            if (renderLivingGreatSword(event, livingEntity, humanoidModel)) return;
        }

    }

    public static boolean renderLivingGreatSword(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event,
                                                 LivingEntity livingEntity, HumanoidModel<LivingEntity> model) {
//        HumanoidModel<LivingEntity> model = (HumanoidModel<LivingEntity>) event.getRenderer().getModel();

        if (livingEntity.getMainHandItem().getItem() instanceof GreatSwordItem) {
            model.leftArmPose = HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            if (livingEntity.getMainHandItem().getOrDefault(ModDataComponents.WEAPON_CHARGE,0) > 0) {
                model.leftArmPose = HumanoidModel.ArmPose.CROSSBOW_HOLD;
//                model.rightArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
            }
            return true;
        }
        if (livingEntity.getOffhandItem().getItem() instanceof GreatSwordItem) {
            model.rightArmPose = HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            model.leftArmPose = HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            return true;
        }
        return false;
    }
}
