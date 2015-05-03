package robosphinx.mods.wtp;

/**
 * @author robosphinx, calisbeast
 */
import net.minecraftforge.common.MinecraftForge;
import robosphinx.mods.wtp.event.WtpEvent;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.handler.ResourceHandler;
import robosphinx.mods.wtp.reference.Reference;
import robosphinx.mods.wtp.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/*
 * Defines our mod for FML and Forge to load.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Wtp {

    private LogHelper log;
    public static ConfigHandler config;
    /*
     * Makes this class accessible from other mods (makes addons and integration possible).
     */
    @Instance(value = Reference.MOD_ID)
    public static Wtp instance;

    /*
     * Forge pre-initialization call, loads our config or creates a config if none exists.
     */
    @EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            log.error("You're loading WTP on a server! This is a client-side only mod!");
        }
        // Sends the event call to our Config Handler.
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        // Loads resources for us to access later.
        ResourceHandler.init();
    }

    /*
     * Registers our event handler so it will be able to tap into the MinecraftForge event classes.
     */
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(WtpEvent.instance);
        FMLCommonHandler.instance().bus().register(WtpEvent.instance);
    }
}
