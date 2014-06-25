package calisbeast.mods.wtp;

/**
 * @author robosphinx, calisbeast
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
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
@Mod(modid = Wtp.MODID, name = Wtp.NAME, version = Wtp.VERSION)
public class Wtp {
	
	/*
	 * References (just to keep them together).
	 */
	public static final String MODID = "WhatsThisPack";
	public static final String NAME = "What's This Pack";
	public static final String VERSION = "1.0";
	
	/*
	 * Makes this class accessible from other mods (makes addons and integration possible).
	 */
	@Instance(value = MODID)
	public static Wtp instance;
	
	/*
	 * Forge pre-initialization call, loads our config or creates a config if none exists.
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		WtpConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
	/*
	 * Registers our event handler so it will be able to tap into the MinecraftForge event classes.
	 */
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new WtpEvent());
	}
	
	/*
	 * Our event handler class - I do this if we have two very small classes that will only be used by each other.
	 */
	public class WtpEvent extends GuiScreen {
		
		/*
		 * These get an instance of Minecraft and it's FontRenderer, because we can't make a direct static reference to something that isn't static.
		 */
		private Minecraft mc = Minecraft.getMinecraft();
		private FontRenderer fontRenderer = mc.fontRenderer;
		
		/*
		 * gets our values from the config.
		 */
		private String string = WtpConfig.strings.get(0);
		private int color = WtpConfig.ints.get(0);
	
		/*
		 * This is the actual event we're tapping into - every time the GUI is an instance of the Main Menu, we will call to render our text.
		 */
		@SubscribeEvent
		public void onRenderMainMenu(GuiScreenEvent event) {
			if (event.gui instanceof GuiMainMenu) {
				fontRenderer.drawStringWithShadow(string, 2, 2, color);
			}
		}
	}
}
