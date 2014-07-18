package calisbeast.mods.wtp.event;

import java.io.File;

import org.lwjgl.opengl.GL11;

import calisbeast.mods.wtp.references.WtpConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WtpEvent extends GuiScreen {
	
	static File mcDir = new File(Minecraft.getMinecraft().mcDataDir, "mods");
	static File iconDir = new File(mcDir, "/WTP Icon/icon.png");
	public static final ResourceLocation icon = new ResourceLocation(iconDir.getAbsolutePath());
	
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
			fontRenderer.drawStringWithShadow(string, 54, 2, color);
			this.mc.getTextureManager().bindTexture(icon);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(2, 2, 0, 0, 50, 50);
		}
	}
}