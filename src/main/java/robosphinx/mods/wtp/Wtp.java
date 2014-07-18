package calisbeast.mods.wtp;

/**
 * @author robosphinx, calisbeast
 */

import calisbeast.mods.wtp.event.WtpEvent;
import calisbeast.mods.wtp.references.WtpConfig;
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
}
