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
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "wtp", name = "WTP", version = "1.0")
public class Wtp {
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		WtpConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new WimmpEvent());
	}
	
	public class WimmpEvent extends GuiScreen {
		
		private Minecraft mc = Minecraft.getMinecraft();
		private FontRenderer fontRenderer = mc.fontRenderer;
		
		private String string = WtpConfig.strings.get(0);
		private int color = WtpConfig.ints.get(0);
	
		@SubscribeEvent
		public void onRenderMainMenu(GuiScreenEvent event) {
			if (event.gui instanceof GuiMainMenu) {
				fontRenderer.drawStringWithShadow(string, 2, 2, color);
			}
		}
	}
}
