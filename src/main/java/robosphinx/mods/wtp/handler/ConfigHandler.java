package robosphinx.mods.wtp.handler;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import robosphinx.mods.wtp.reference.Reference;
import robosphinx.mods.wtp.util.LogHelper;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;
    public static int           guiPos     = 0;
    private static String       colorTemp1 = "";
    private static String       colorTemp2 = "";
    private static String       colorTemp3 = "";
    public static int           color1     = 0xffffff;
    public static int           color2     = 0xffffff;
    public static int           color3     = 0xffffff;
    public static String        message    = "";
    public static String        message2   = "";
    public static String        message3   = "";
    public static int           lines      = 1;
    private static LogHelper    log;

    /*
     * This loads the config file, adds/gets each value on the ArrayList, and saves the file.
     */
    public static void init(File configFile) {
        // Create our config object from the given file.
        if (config == null) {
            config = new Configuration(configFile);
        }
        loadConfig();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfig();
        }
    }

    public static void loadConfig() {
        // Load the config file.
        config.load();
        // Read in our values.
        guiPos      = config.get(Configuration.CATEGORY_GENERAL, "Text Position", 0, "Positions: 0 - Top Left corner, 1 - Top right corner, 2 - Bottom left corner, 3 - Bottom right corner.").getInt(0);
        message     = config.get(Configuration.CATEGORY_GENERAL, "Text Line 1", "** Default Line 1 **", "Text - Line 1").getString();
        colorTemp1  = config.get(Configuration.CATEGORY_GENERAL, "Text Color - Line 1", "FFFFFF", "Text color line 1 - This must be hexadecimal ( RRGGBB )").getString();
        color1      = Integer.parseInt(colorTemp1, 16);
        message2    = config.get(Configuration.CATEGORY_GENERAL, "Text Line 2", "** Default Line 2 **", "Text - Line 2.").getString();
        colorTemp2  = config.get(Configuration.CATEGORY_GENERAL, "Text Color - Line 2", "FFFFFF", "Text color line 2- This must be hexadecimal ( RRGGBB )").getString();
        color2      = Integer.parseInt(colorTemp2, 16);
        message3    = config.get(Configuration.CATEGORY_GENERAL, "Text Line 3", "** Default Line 3 **", "Text - Line 3.").getString();
        colorTemp3  = config.get(Configuration.CATEGORY_GENERAL, "Text Color - Line 3", "FFFFFF", "Text color line 3- This must be hexadecimal ( RRGGBB )").getString();
        color3      = Integer.parseInt(colorTemp3, 16);
        lines       = config.get(Configuration.CATEGORY_GENERAL, "Lines", 1, "How many lines you are using. Max of 3 right now.").getInt(1);
        // If the config file has changed, only THEN save it.
        if (config.hasChanged()) {
            config.save();
        }
        log.info("+++++ WTP Configuration options: +++++");
        log.info("++  guiPos: " + guiPos);
        log.info("++  lines : " + lines);
        log.info("++  line 1: " + message);
        log.info("++  color : " + colorTemp1);
        if (lines == 2 || lines == 3) {
            log.info("++  line 2: " + message2);
            log.info("++  color : " + colorTemp2);
            if (lines == 3) {
                log.info("++  line 3: " + message3);
                log.info("++  color : " + colorTemp3);
            }
        }
        log.info("++++++++++++++++++++++++++++++++++++++");
    }
}
