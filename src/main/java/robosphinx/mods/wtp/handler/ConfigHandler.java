package robosphinx.mods.wtp.handler;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import robosphinx.mods.wtp.reference.Reference;
import robosphinx.mods.wtp.util.LogHelper;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;
    public static int           guiPos   = 0;
    public static int           color    = 0xffffff;
    public static String        message  = "";
    public static String        message2 = "";
    public static String        message3 = "";
    // public static String        favIcon  = "icon";
    public static int           lines    = 1;
    // public static boolean       image    = false;
    // public static boolean       shadow   = false;
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
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfig();
        }
    }

    public static void loadConfig() {
        // Load the config file.
        config.load();
        // Read in our values.
        guiPos      = config.get(Configuration.CATEGORY_GENERAL, "Text Position", 0, "Positions: 0 - Top Left corner, 1 - Top right corner, 2 - Bottom left corner, 3 - Bottom right corner.").getInt(0);
        color       = config.get(Configuration.CATEGORY_GENERAL, "Text Color", 0xffffff, "Text color - This can be Hex or RGB ( 0xffffff / 16777215 )").getInt(0xffffff);
        message     = config.get(Configuration.CATEGORY_GENERAL, "Text Line 1", "** Default Line 1 **", "This is the text that will be displayed on the main menu.").getString();
        message2    = config.get(Configuration.CATEGORY_GENERAL, "Text Line 2", "** Default Line 2 **", "Text for line 2.").getString();
        message3    = config.get(Configuration.CATEGORY_GENERAL, "Text Line 3", "** Default Line 3 **", "Text for line 3.").getString();
        // favIcon     = config.get("WIP", "Favorite Icon", "icon", "Which icon to use most often (possibly).").getString();
        lines       = config.get(Configuration.CATEGORY_GENERAL, "Lines", 1, "How many lines you are using. Max of 3 right now.").getInt(1);
        // image       = config.get("WIP", "Icon", false, "Whether or not to display an icon in the top left corner of the Main Menu. NOT YET IMPLEMENTED!").getBoolean(false);
        // shadow      = config.get(Configuration.CATEGORY_GENERAL, "Text Shadow", true, "Whether or not the text has a shadow.").getBoolean(true);
        // If the config file has changed, only THEN save it.
        if (config.hasChanged()) {
            config.save();
        }
        log.info("+++++ WTP Configuration options: +++++");
        log.info("++  guiPos: " + guiPos);
        log.info("++  color : " + color);
        log.info("++  lines : " + lines);
        log.info("++  line 1: " + message);
        log.info("++  line 2: " + message2);
        log.info("++  line 3: " + message3);
        // log.info("++  icon  : " + favIcon);
        // log.info("++  image : " + image);
        // log.info("++  shadow: " + shadow);
        log.info("++++++++++++++++++++++++++++++++++++++");
    }
}
