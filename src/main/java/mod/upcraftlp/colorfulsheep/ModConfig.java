package mod.upcraftlp.colorfulsheep;

import com.google.common.collect.Maps;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

import static mod.upcraftlp.colorfulsheep.Reference.MODID;

/**
 * @author UpcraftLP
 */
@Config(modid = MODID, name = "craftdevmods/" + MODID) //--> /config/craftdevmods/colorful-sheep.cfg
@Config.LangKey("config." + MODID + ".title")
public class ModConfig {

    @Config.Comment({"A list of all colors plus their relative spawn weight"})
    public static Map<String, Double> WEIGHTS = Maps.newConcurrentMap();

    static {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            String name = color.name();
            if(color == EnumDyeColor.WHITE) WEIGHTS.put(name, 50.0D);
            else if(color == EnumDyeColor.BLACK || color == EnumDyeColor.GRAY ||color == EnumDyeColor.SILVER) WEIGHTS.put(name, 5.0D);
            else if(color == EnumDyeColor.BROWN) WEIGHTS.put(name, 3.0D);
            else if(color == EnumDyeColor.PINK) WEIGHTS.put(name, 1.0D); //WEIGHTS.put(color, 0.164D);
            else WEIGHTS.put(name, 2.0D);
        }
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class Handler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }
    
}
