package com.nateplays.mhcraftlands.common.skill;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nateplays.mhcraftlands.MHMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MyNeoForgeMod.MODID)
@EventBusSubscriber(modid = MHMod.MOD_ID)
public class HuntingSkillDataLoader extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuntingSkillDataLoader.class);
    private static final Gson GSON = new Gson();

    // Map to store the loaded data
    private static final Map<ResourceLocation, HuntingSkillData> ARMOR_SKILL_DATA_MAP = new HashMap<>();

    public HuntingSkillDataLoader() {
        super(GSON, "armor_skills");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        ARMOR_SKILL_DATA_MAP.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : object.entrySet()) {
            ResourceLocation id = entry.getKey();
            try {
                JsonObject json = GsonHelper.convertToJsonObject(entry.getValue(), "armor_skill");
                HuntingSkillData data = HuntingSkillData.fromJson(id, json);
                ARMOR_SKILL_DATA_MAP.put(id, data);
            } catch (Exception e) {
                LOGGER.error("Failed to load armor skill data for {}", id, e);
            }
        }

        LOGGER.info("Loaded {} armor skill data files", ARMOR_SKILL_DATA_MAP.size());
    }

    public static HuntingSkillData getArmorEnchantmentData(ResourceLocation id) {
        return ARMOR_SKILL_DATA_MAP.get(id);
    }

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new HuntingSkillDataLoader());
    }
}
