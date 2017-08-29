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
    public static int           lines      = 1;
    private static String[]     colorTemp;
    public static int[]         color;
    public static String[]      message;
    public static boolean       allInOne   = true;
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
    
    private static void initArrays() {
        colorTemp = new String[lines];
        color = new int[lines];
        message = new String[lines];
    }

    public static void loadConfig() {
        // Load the config file.
        config.load();
        // Read in our values.
        lines       = config.get(Configuration.CATEGORY_GENERAL, "Lines", 1, "How many lines you are using. Use come common sense here :P - Run minecraft once after changing this to generate options for more lines.").getInt(1);
        guiPos      = config.get(Configuration.CATEGORY_GENERAL, "Text Position", 0, "Positions: 0 - Top Left corner, 1 - Top right corner, 2 - Bottom left corner, 3 - Bottom right corner.").getInt(0);
        
        initArrays();
        
        for (int i = 0; i < lines; i++) {
            message[i]    = config.get(Configuration.CATEGORY_GENERAL, "Text Line " + (i + 1), "** Default Line " + (i + 1) + " **", "Text - Line " + (i + 1)).getString();
            colorTemp[i]  = config.get(Configuration.CATEGORY_GENERAL, "Text Color - Line " + (i + 1), "FFFFFF", "Text color line " + (i + 1) + " - This must be hexadecimal ( RRGGBB )").getString();
            color[i]      = Integer.parseInt(colorTemp[i], 16);
        }
        
        allInOne    = config.get(Configuration.CATEGORY_GENERAL, "All In One", true, "Are these messages displayed at the same time? False to cycle through them randomly.").getBoolean();
        // If the config file has changed, only THEN save it.
        if (config.hasChanged()) {
            config.save();
        }
        log.info("+++++ WTP Configuration options: +++++");
        log.info("++  guiPos  : " + guiPos);
        log.info("++  lines   : " + lines);
        for (int i = 0; i < lines; i++) {
            log.info("++  line " + (i + 1) + "  : " + message[i]);
            log.info("++  color   : " + colorTemp[i]);
        }
        log.info("++  allInOne: " + allInOne);
        log.info("++++++++++++++++++++++++++++++++++++++");
    }
}
