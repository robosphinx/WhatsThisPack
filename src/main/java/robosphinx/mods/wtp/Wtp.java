package robosphinx.mods.wtp;

/**
 * @author robosphinx, calisbeast
 */
import robosphinx.mods.wtp.event.WtpEvent;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/*
 * Defines our mod for FML and Forge to load.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Wtp {

    private ConfigHandler config;
    /*
     * Makes this class accessible from other mods (makes addons and integration possible).
     */
    @Instance(value = Reference.MOD_ID)
    public static Wtp     instance;

    /*
     * Forge pre-initialization call, loads our config or creates a config if none exists.
     */
    @EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        // Sends the event call to our Config Handler.
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }

    /*
     * Registers our event handler so it will be able to tap into the MinecraftForge event classes.
     */
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new WtpEvent());
    }
}
