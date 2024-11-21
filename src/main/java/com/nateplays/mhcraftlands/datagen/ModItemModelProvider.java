package com.nateplays.mhcraftlands.datagen;

import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.block.ModBlocks;
import com.nateplays.mhcraftlands.common.weapon.*;
import com.nateplays.mhcraftlands.hunter.weapon.DualBladesItem;
import com.nateplays.mhcraftlands.hunter.weapon.GlaiveItem;
import com.nateplays.mhcraftlands.hunter.weapon.GreatSwordItem;
import com.nateplays.mhcraftlands.hunter.weapon.SwordAndShieldItem;
import com.nateplays.mhcraftlands.item.ModItems;
import com.nateplays.mhcraftlands.common.armor.ModArmorItems;
import com.nateplays.mhcraftlands.pet.item.MHPetItems;
import com.nateplays.mhcraftlands.pet.item.armor.MHPetArmorItems;
import com.nateplays.mhcraftlands.pet.item.weapon.MHPetWeaponItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MHMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.EARTH_CRYSTAL.get());
        basicItem(ModItems.MALACHITE_CHUNK.get());
        basicItem(ModItems.MALACHITE_INGOT.get());
        basicItem(ModItems.NULBERRY.get());
        basicItem(ModItems.MUSIC_DISC_HUNTERGOFORTH.get());
//        basicItem(ModItems.CHISEL.get()); //custom model in assets folder

        buttonItem(ModBlocks.MACHALITE_BUTTON, ModBlocks.MACHALITE_BLOCK);
        fenceItem(ModBlocks.MACHALITE_FENCE, ModBlocks.MACHALITE_BLOCK);
        wallItem(ModBlocks.MACHALITE_WALL, ModBlocks.MACHALITE_BLOCK);

        basicItem(ModBlocks.MACHALITE_DOOR.asItem());

        handheldItem(ModItems.MACHALITE_SWORD);
        handheldItem(ModItems.MACHALITE_PICKAXE);
        handheldItem(ModItems.MACHALITE_SHOVEL);
        handheldItem(ModItems.MACHALITE_AXE);
        handheldItem(ModItems.MACHALITE_HOE);

        handheldItem(ModItems.MACHALITE_HAMMER);

        snsItem(ModItems.MACHALITE_SNS);
        dbItem(ModItems.MACHALITE_DB);
        gsItem(ModItems.MACHALITE_GS);
        gvItem(ModItems.MACHALITE_GV);

        withExistingParent(ModItems.MOSSWINE_SPAWN_EGG.getRegisteredName(), mcLoc("item/template_spawn_egg"));
        withExistingParent(MHPetItems.FELYNE_SPAWN_EGG.getRegisteredName(), mcLoc("item/template_spawn_egg"));
        basicItem(MHPetItems.SUMMON_FELYNE_VOUCHER.get());
        basicItem(MHPetItems.DISMISS_BUDDY_VOUCHER.get());

        registerAllCraftingMaterials();
        registerAllPetArmorWeaponItems();
//        armorItem(ModArmorItems.F_FRANKIE_HELM);
//        armorItem(ModArmorItems.F_FRANKIE_MAIL);
    }

    private void registerAllPetArmorWeaponItems() {
        armorItem(MHPetArmorItems.F_ACORN_HELM);
        armorItem(MHPetArmorItems.F_ACORN_MAIL);
        armorItem(MHPetArmorItems.F_KAMURA_HELM);
        armorItem(MHPetArmorItems.F_KAMURA_MAIL);
        armorItem(MHPetArmorItems.F_BONE_HELM);
        armorItem(MHPetArmorItems.F_BONE_MAIL);
        armorItem(MHPetArmorItems.F_ALLOY_HELM);
        armorItem(MHPetArmorItems.F_ALLOY_MAIL);
        armorItem(MHPetArmorItems.F_RED_HELM, true);
        armorItem(MHPetArmorItems.F_RED_MAIL, true);
        armorItem(MHPetArmorItems.F_GHOST_HELM, true);
        armorItem(MHPetArmorItems.F_GHOST_MAIL, true);
        armorItem(MHPetArmorItems.F_FRANKIE_HELM);
        armorItem(MHPetArmorItems.F_FRANKIE_MAIL);
        armorItem(MHPetArmorItems.F_MOSGHARL_HELM);
        armorItem(MHPetArmorItems.F_MOSGHARL_MAIL);

        petWeaponItem(MHPetWeaponItems.F_KAMURA_BOKKEN);
        petWeaponItem(MHPetWeaponItems.F_BONE_PICK);
        petWeaponItem(MHPetWeaponItems.F_BONE_HAMMER);
        petWeaponItem(MHPetWeaponItems.F_IRON_SWORD);
        petWeaponItem(MHPetWeaponItems.F_RED_BASKET, modLoc("item/handheld_low"));
        petWeaponItem(MHPetWeaponItems.F_MOSGHARL_BROOM);
        petWeaponItem(MHPetWeaponItems.F_GHOST_LANTERN, modLoc("item/handheld_flipped"));
        petWeaponItem(MHPetWeaponItems.F_FRANKIE_BALL, modLoc("item/handheld_low"));
    }

    private void registerAllCraftingMaterials() {
        String prefix = "material/";
        basicItem(MHPetItems.SCRAP_WOOD, prefix);
        basicItem(MHPetItems.SCRAP_ORE, prefix);
        basicItem(MHPetItems.SCRAP_BONE, prefix);
        basicItem(MHPetItems.SCRAP_FUR, prefix);
        basicItem(MHPetItems.SCRAP_HUMBLE, prefix);
        basicItem(MHPetItems.SCRAP_SINISTER, prefix);
    }


    public void basicItem(DeferredItem<?> item, String prefix) {
        this.withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/generated"))
                .texture("layer0", modLoc("item/" + prefix + item.getId().getPath() ) );
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MHMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder armorItem(DeferredItem<?> item) { return armorItem(item, false); }

    private ItemModelBuilder armorItem(DeferredItem<?> item, boolean dyeable) {
        ItemModelBuilder builder = withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/armor/" + item.getId().getPath()));
        if (dyeable) {
            builder = builder.texture("layer1", modLoc("item/armor/" + item.getId().getPath() + "_color"));
        }
        return builder;
    }

    private ItemModelBuilder petWeaponItem(DeferredItem<?> item) {
        return petWeaponItem(item, mcLoc("item/handheld"));
    }

    private ItemModelBuilder petWeaponItem(DeferredItem<?> item, ResourceLocation parentModelPath) {
        return withExistingParent(item.getId().getPath(), parentModelPath)
                .texture("layer0", modLoc("item/weapon/" + item.getId().getPath()));
    }

    private void dbItem(DeferredItem<DualBladesItem> item) {
        String itemId = item.getId().getPath();
        String modelPath = "item/weapon/";

        withExistingParent(modelPath + itemId + "_mainhand", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + itemId + "_1"));

        withExistingParent(modelPath + itemId + "_offhand", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + itemId + "_2"));

        withExistingParent(itemId, ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + itemId))
                .override()
                    .predicate(modLoc("fake_render_hand"), 1.0F)
                    .model(new ModelFile.UncheckedModelFile(modLoc(modelPath + itemId + "_mainhand")))
                    .end()
                .override()
                    .predicate(modLoc("fake_render_hand"), 2.0F)
                    .model(new ModelFile.UncheckedModelFile(modLoc(modelPath + itemId + "_offhand")))
                    .end();
    }

    private void snsItem(DeferredItem<SwordAndShieldItem> item) {
        String itemId = item.getId().getPath();
        String modelPath = "item/weapon/";

        withExistingParent(modelPath + itemId + "_mainhand_blocking", modLoc("item/weapon/sns_shield_blocking"))
                .texture("layer0", modLoc("item/" + itemId + "_shield"));

        withExistingParent(modelPath + itemId + "_mainhand", modLoc("item/weapon/sns_shield"))
                .texture("layer0", modLoc("item/" + itemId + "_shield"));

        withExistingParent(modelPath + itemId + "_offhand", ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + itemId));

        withExistingParent(itemId, ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + itemId + "_shield"))
                .texture("layer1", modLoc("item/" + itemId))

                //RENDER PRIORITY IS BOTTOM FIRST, TOP LAST, EXIT ON FIRST MATCH.
                .override()
                    .predicate(modLoc("fake_render_hand"), 1.0F)
                    .model(new ModelFile.UncheckedModelFile(modLoc(modelPath + itemId + "_mainhand")))
                    .end()
                .override()
                    .predicate(modLoc("fake_render_hand"), 2.0F)
                    .model(new ModelFile.UncheckedModelFile(modLoc(modelPath + itemId + "_offhand")))
                    .end()
                .override()
                    .predicate(modLoc("blocking"), 1.0F)
                    .model(new ModelFile.UncheckedModelFile(modLoc(modelPath + itemId + "_mainhand_blocking")))
                    .end();
    }

    private void gsItem(DeferredItem<GreatSwordItem> item) {
        withExistingParent(item.getId().getPath(), modLoc("item/weapon/gs"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private void gvItem(DeferredItem<GlaiveItem> item) {
        withExistingParent(item.getId().getPath(), modLoc("item/handheld_big"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}
