package robosphinx.mods.wtp.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.util.LogHelper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class WtpEvent extends GuiScreen {

    /*
     * These get an instance of Minecraft and it's FontRenderer, because we can't make a direct static reference to something that isn't static.
     */
    private Minecraft        mc           = Minecraft.getMinecraft();
    private FontRenderer     fontRenderer = mc.standardGalacticFontRenderer;
    private LogHelper        log;
    private ScaledResolution scale;
    public static WtpEvent   instance     = new WtpEvent();
    
    private int              xCoord;
    private int              yCoord;
    private int              xCoord2;
    private int              yCoord2;
    private int              xCoord3;
    private int              yCoord3;
    
    /*
     * gets our values from the config.
     */
    private String           string       = ConfigHandler.message;
    private String           string2      = ConfigHandler.message2;
    private String           string3      = ConfigHandler.message3;
    private int              color        = ConfigHandler.color;
    private int              textPos      = ConfigHandler.guiPos;
    // private boolean          image        = ConfigHandler.image;
    // private boolean          shadow       = ConfigHandler.shadow;
    private int              lines        = ConfigHandler.lines;
    
    public static HashMap<String, Integer>         iconNames   = new HashMap<String, Integer>();
    public static HashMap<Integer, BufferedImage>  icons       = new HashMap<Integer, BufferedImage>();
    public static HashMap<BufferedImage, Integer>  iconImageId = new HashMap<BufferedImage, Integer>();
    
    /*
     * This is the actual event we're tapping into - every time the GUI is an instance of the Main Menu, we will call to render our text.
     */
    @SubscribeEvent
    public void onRenderMainMenu(GuiScreenEvent event) throws IOException {
        scale = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        if (textPos == 0) {
            // Upper left
            /* if (image) {
                xCoord  = 54;
                yCoord  = 2;
                xCoord2 = 54;
                yCoord2 = 12;
                xCoord3 = 54;
                yCoord3 = 22;
            }
            else { */
                xCoord  = 2;
                yCoord  = 2;
                xCoord2 = 2;
                yCoord2 = 12;
                xCoord3 = 2;
                yCoord3 = 22;
            // }
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
            /*if (shadow) {
                fontRenderer.drawStringWithShadow(string, xCoord, yCoord, color);
                if (lines == 2) {
                    fontRenderer.drawStringWithShadow(string2, xCoord2, yCoord2, color);
                }
                if (lines == 3) {
                    fontRenderer.drawStringWithShadow(string2, xCoord2, yCoord2, color);
                    fontRenderer.drawStringWithShadow(string3, xCoord3, yCoord3, color);
                }
            }
            else { */
                fontRenderer.drawString(string, xCoord, yCoord, color);
                if (lines == 2) {
                    fontRenderer.drawString(string2, xCoord2, yCoord2, color);
                }
                if (lines == 3) {
                    fontRenderer.drawString(string2, xCoord2, yCoord2, color);
                    fontRenderer.drawString(string3, xCoord3, yCoord3, color);
                }
            // }
            /*if (image) {
                File iconDir = new File(ResourceHandler.getModsFolder(), "/icons");
                if (iconDir.exists()) {
                    for (File file : iconDir.listFiles()) {
                        if (file.getName().endsWith(".png")) {
                            try {
                                BufferedImage img = ImageIO.read(file);
                                if (img != null) {
                                    iconNames.put(file.getName().substring(0,  file.getName().length() - 4).toLowerCase(), icons.size());
                                    icons.put(icons.size(), img);
                                    iconImageId.put(img, -1);
                                }
                            } catch (IOException e) {
                                
                            }
                        }
                    }
                }
                renderIcon();
                // Call for render here.
                // RenderCustomTexture(2, 2, 0, 0, 50, 50, new ResourceLocation(iconDir.getPath(), "icon.png"), 1);
            } */
        }
    }
    
    /* public void renderIcon() {
        GL11.glPushMatrix();
        BufferedImage image;
        Tessellator t = Tessellator.getInstance();
        
        Integer icon = iconNames.get(Wtp.config.favIcon.toLowerCase());
        image = icons.get(icon);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, iconImageId.get(image));
        
        t.startDrawingQuads();
        t.addVertexWithUV(2, 52, 0, 0.0, 1.0);
        t.addVertexWithUV(52, 52, 0, 0.0, 1.0);
        t.addVertexWithUV(52, 2, 0, 0.0, 1.0);
        t.addVertexWithUV(2, 2, 0, 0.0, 1.0);
        t.draw();
        
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    } */

    /*public void RenderCustomTexture(int x, int y, int u, int v, int width, int height, ResourceLocation resourceLocation, float scale) {
        x /= scale;
        y /= scale;
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glScalef(scale, scale, scale);
        
        if(resourceLocation != null) {
            mc.getTextureManager().bindTexture(resourceLocation);
            log.all("[WTP][INFO] Found icon file at " + resourceLocation.toString());
        } else {
            log.error("[WTP][ERROR] Searching for icon file...");
            log.error("[WTP][ERROR] No icon file found!");
        }
        
        mc.ingameGUI.drawTexturedModalRect(x, y, u, v, width, height);
        
        GL11.glPopMatrix();
    }*/
}
