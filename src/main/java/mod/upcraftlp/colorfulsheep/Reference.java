package mod.upcraftlp.colorfulsheep;

import com.google.common.collect.Lists;
import net.minecraft.util.text.TextFormatting;

import java.time.Year;
import java.util.List;

public class Reference {

    //VERSION
    public static final String MCVERSIONS = "[1.12, 1.13)";
    public static final String VERSION = "@VERSION@";

    //MISC INFORMATION
    public static final List<String> authors = Lists.newArrayList(
            "UpcraftLP"
    );
    public static final String MOD_DESCRIPTION = "Minecraft mod that adds collectable hearts";
    public static final String CREDITS = TextFormatting.GOLD + "(C)" + "2017-" + Year.now().getValue() + " UpcraftLP";

    //META Information
    public static final String MODNAME = "Colorful Sheep";
    public static final String MODID = "colorful-sheep";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.0.2489,)";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/UpcraftLP/Colorful-Sheep/master/versions.json";
    public static final String WEBSITE = "https://minecraft.curseforge.com/projects/colorful-sheep";

    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";
}
