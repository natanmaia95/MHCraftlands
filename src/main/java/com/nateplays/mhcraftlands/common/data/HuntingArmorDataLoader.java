package com.nateplays.mhcraftlands.common.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nateplays.mhcraftlands.MHMod;
import com.nateplays.mhcraftlands.common.skill.HuntingSkillData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MyNeoForgeMod.MODID)
@EventBusSubscriber(modid = MHMod.MOD_ID)
public class HuntingArmorDataLoader extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuntingArmorDataLoader.class);
    private static final Gson GSON = new Gson();

    // Map to store the loaded data
    private static final Map<ResourceLocation, HuntingArmorData> HUNTING_ARMOR_DATA_MAP = new HashMap<>();

    public HuntingArmorDataLoader() {
        super(GSON, "hunting_armor");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profiler) {
        HUNTING_ARMOR_DATA_MAP.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : object.entrySet()) {
            ResourceLocation id = entry.getKey();
            try {
                JsonObject json = GsonHelper.convertToJsonObject(entry.getValue(), "hunting_armor_data");
                HuntingArmorData data = HuntingArmorData.fromJson(id, json);
                HUNTING_ARMOR_DATA_MAP.put(id, data);
            } catch (Exception e) {
                LOGGER.error("Failed to load armor skill data for {}", id, e);
            }
        }

        LOGGER.info("Loaded {} armor skill data files", HUNTING_ARMOR_DATA_MAP.size());
    }

    public static HuntingArmorData getArmorData(ResourceLocation id) {
        return HUNTING_ARMOR_DATA_MAP.get(id);
    }

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new HuntingArmorDataLoader());
    }
}
