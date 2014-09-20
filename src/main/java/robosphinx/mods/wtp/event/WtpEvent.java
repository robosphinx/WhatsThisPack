package robosphinx.mods.wtp.event;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;

import org.lwjgl.opengl.GL11;

import robosphinx.mods.wtp.client.renderer.IconRenderer;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WtpEvent extends GuiScreen {

    /*
     * These get an instance of Minecraft and it's FontRenderer, because we can't make a direct static reference to something that isn't static.
     */
    private Minecraft        mc           = Minecraft.getMinecraft();
    private FontRenderer     fontRenderer = mc.fontRenderer;
    private LogHelper        log;
    private ScaledResolution scale;
    
    private int              xCoord;
    private int              yCoord;
    private int              xCoord2;
    private int              yCoord2;
    private int              xCoord3;
    private int              yCoord3;
    private IconRenderer     renderer;
    
    /*
     * gets our values from the config.
     */
    private String           string       = ConfigHandler.message;
    private String           string2      = ConfigHandler.message2;
    private String           string3      = ConfigHandler.message3;
    private int              color        = ConfigHandler.color;
    private int              textPos      = ConfigHandler.guiPos;
    private boolean          image        = ConfigHandler.image;
    private boolean          shadow       = ConfigHandler.shadow;
    private int              lines        = ConfigHandler.lines;
    
    private static HashMap<Integer, BufferedImage> icons = new HashMap<Integer, BufferedImage>();
    
    /*
     * This is the actual event we're tapping into - every time the GUI is an instance of the Main Menu, we will call to render our text.
     */
    @SubscribeEvent
    public void onRenderMainMenu(GuiScreenEvent event) throws IOException {
        scale = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        if (textPos == 0) {
            // Upper left
            if (image) {
                xCoord  = 54;
                yCoord  = 2;
                xCoord2 = 54;
                yCoord2 = 12;
                xCoord3 = 54;
                yCoord3 = 22;
            }
            else {
                xCoord  = 2;
                yCoord  = 2;
                xCoord2 = 2;
                yCoord2 = 12;
                xCoord3 = 2;
                yCoord3 = 22;
            }
        }
        else if (textPos == 1) {
            // Upper right
            xCoord  = scale.getScaledWidth() - fontRenderer.getStringWidth(string) - 2;
            yCoord  = 2;
            xCoord2 = scale.getScaledWidth() - fontRenderer.getStringWidth(string2) - 2;
            yCoord2 = 12;
            xCoord3 = scale.getScaledWidth() - fontRenderer.getStringWidth(string3) - 2;
            yCoord3 = 22;
        }
        else if (textPos == 2) {
            // Lower left
            xCoord  = 2;
            yCoord  = scale.getScaledHeight() - 50 - (10 * lines);
            xCoord2 = 2;
            yCoord2 = scale.getScaledHeight() - 50 - (10 * (lines - 1));
            xCoord3 = 2;
            yCoord3 = scale.getScaledHeight() - 50 - (10 * (lines - 2));
        }
        else if (textPos == 3) {
            // Lower right
            xCoord  = scale.getScaledWidth() - fontRenderer.getStringWidth(string) - 2;
            yCoord  = scale.getScaledHeight() - 10 - (10 * lines);
            xCoord2 = scale.getScaledWidth() - fontRenderer.getStringWidth(string2) - 2;
            yCoord2 = scale.getScaledHeight() - 10 - (10 * (lines - 1));
            xCoord3 = scale.getScaledWidth() - fontRenderer.getStringWidth(string3) - 2;
            yCoord3 = scale.getScaledHeight() - 10 - (10 * (lines - 2));
        }
        if (event.gui instanceof GuiMainMenu) {
            if (shadow) {
                fontRenderer.drawStringWithShadow(string, xCoord, yCoord, color);
                if (lines == 2) {
                    fontRenderer.drawStringWithShadow(string2, xCoord2, yCoord2, color);
                }
                if (lines == 3) {
                    fontRenderer.drawStringWithShadow(string2, xCoord2, yCoord2, color);
                    fontRenderer.drawStringWithShadow(string3, xCoord3, yCoord3, color);
                }
            }
            else {
                fontRenderer.drawString(string, xCoord, yCoord, color);
                if (lines == 2) {
                    fontRenderer.drawString(string2, xCoord2, yCoord2, color);
                }
                if (lines == 3) {
                    fontRenderer.drawString(string2, xCoord2, yCoord2, color);
                    fontRenderer.drawString(string3, xCoord3, yCoord3, color);
                }
            }
            if (image) {
                File mcDir = new File(Minecraft.getMinecraft().mcDataDir, "mods");
                File iconDir = new File (mcDir, "wtp_icons");
                if (!iconDir.exists()) {
                    log.info("No icon dir to load from! Please place a 50px by 50px icon in 'mods/WTP Icons' (with the space and case-sensitive!)");
                }
                else {
                    for (File file : iconDir.listFiles()) {
                        if (file.getName().endsWith(".png")) {
                            try {
                                BufferedImage img = ImageIO.read(file);
                                if (img != null) {
                                    icons.put(icons.size(),  img);
                                }
                            }
                            catch (IOException e) {
                                log.info(e);
                            }
                        }
                    }
                }
                Random rand = new Random();
                renderer = new IconRenderer(mc.getTextureManager(), icons.get(0));
                renderer.loadIcon();
                renderer.drawImage(2, 2, 50, 50);
            }
        }
    }
}
