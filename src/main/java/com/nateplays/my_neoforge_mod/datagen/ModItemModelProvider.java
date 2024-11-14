package com.nateplays.my_neoforge_mod.datagen;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.block.ModBlocks;
import com.nateplays.my_neoforge_mod.item.ModItems;
import com.nateplays.my_neoforge_mod.item.armor.ModArmorItems;
import com.nateplays.my_neoforge_mod.item.weapons.*;
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
        super(output, MyNeoForgeMod.MODID, existingFileHelper);
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
        withExistingParent(ModItems.FELYNE_SPAWN_EGG.getRegisteredName(), mcLoc("item/template_spawn_egg"));
        basicItem(ModItems.SUMMON_FELYNE_VOUCHER.get());
        basicItem(ModItems.DISMISS_BUDDY_VOUCHER.get());

        registerAllCraftingMaterials();
        registerAllPetArmorWeaponItems();
//        armorItem(ModArmorItems.F_FRANKIE_HELM);
//        armorItem(ModArmorItems.F_FRANKIE_MAIL);
    }

    private void registerAllPetArmorWeaponItems() {
        armorItem(ModArmorItems.F_ACORN_HELM);
        armorItem(ModArmorItems.F_ACORN_MAIL);
        armorItem(ModArmorItems.F_KAMURA_HELM);
        armorItem(ModArmorItems.F_KAMURA_MAIL);
        armorItem(ModArmorItems.F_BONE_HELM);
        armorItem(ModArmorItems.F_BONE_MAIL);
        armorItem(ModArmorItems.F_ALLOY_HELM);
        armorItem(ModArmorItems.F_ALLOY_MAIL);
        armorItem(ModArmorItems.F_RED_HELM, true);
        armorItem(ModArmorItems.F_RED_MAIL, true);
        armorItem(ModArmorItems.F_GHOST_HELM, true);
        armorItem(ModArmorItems.F_GHOST_MAIL, true);
        armorItem(ModArmorItems.F_FRANKIE_HELM);
        armorItem(ModArmorItems.F_FRANKIE_MAIL);
        armorItem(ModArmorItems.F_MOSGHARL_HELM);
        armorItem(ModArmorItems.F_MOSGHARL_MAIL);

        petWeaponItem(ModWeaponItems.F_KAMURA_BOKKEN);
        petWeaponItem(ModWeaponItems.F_BONE_PICK);
        petWeaponItem(ModWeaponItems.F_BONE_HAMMER);
        petWeaponItem(ModWeaponItems.F_IRON_SWORD);
    }

    private void registerAllCraftingMaterials() {
        String prefix = "material/";
        basicItem(ModItems.SCRAP_WOOD, prefix);
        basicItem(ModItems.SCRAP_ORE, prefix);
        basicItem(ModItems.SCRAP_BONE, prefix);
        basicItem(ModItems.SCRAP_FUR, prefix);
        basicItem(ModItems.SCRAP_HUMBLE, prefix);
        basicItem(ModItems.SCRAP_SINISTER, prefix);
    }


    public void basicItem(DeferredItem<?> item, String prefix) {
        this.withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/generated"))
                .texture("layer0", modLoc("item/" + prefix + item.getId().getPath() ) );
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID,
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
        return petWeaponItem(item, "item/handheld");
    }

    private ItemModelBuilder petWeaponItem(DeferredItem<?> item, String parentModelPath) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse(parentModelPath))
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
