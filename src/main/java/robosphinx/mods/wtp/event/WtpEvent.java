package robosphinx.mods.wtp.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.util.LogHelper;

import java.io.IOException;

import static net.minecraftforge.common.ForgeVersion.Status.BETA_OUTDATED;
import static net.minecraftforge.common.ForgeVersion.Status.OUTDATED;

public class WtpEvent extends GuiScreen {

    /*
     * These get an instance of Minecraft and it's FontRenderer, because we can't make a direct static reference to something that isn't static.
     */
    private Minecraft           mc           = Minecraft.getMinecraft();
    private ForgeVersion.Status status       = ForgeVersion.getStatus();
    private FontRenderer        fontRenderer = mc.fontRendererObj;
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
    private int              color1        = ConfigHandler.color1;
    private int              color2        = ConfigHandler.color2;
    private int              color3        = ConfigHandler.color3;
    private int              textPos      = ConfigHandler.guiPos;
    private int              lines        = ConfigHandler.lines;
    
    /*
     * This is the actual event we're tapping into - every time the GUI is an instance of the Main Menu, we will call to render our text.
     */
    @SubscribeEvent
    public void onRenderMainMenu(GuiScreenEvent event) throws IOException {
        scale = new ScaledResolution(mc);
        if (textPos == 0) {
            // Upper left
            xCoord  = 2;
            yCoord  = 2;
            xCoord2 = 2;
            yCoord2 = 12;
            xCoord3 = 2;
            yCoord3 = 22;
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
            // IS forge out of date? If it is, another line is drawn by forge
            int y;
            if (status == OUTDATED || status == BETA_OUTDATED) {
                y = 20;
            }
            else {
                y = 10;
            }
            // Lower right
            xCoord  = scale.getScaledWidth() - fontRenderer.getStringWidth(string) - 2;
            yCoord  = scale.getScaledHeight() - y - (10 * lines);
            xCoord2 = scale.getScaledWidth() - fontRenderer.getStringWidth(string2) - 2;
            yCoord2 = scale.getScaledHeight() - y - (10 * (lines - 1));
            xCoord3 = scale.getScaledWidth() - fontRenderer.getStringWidth(string3) - 2;
            yCoord3 = scale.getScaledHeight() - y - (10 * (lines - 2));
        }
        if (event.getGui() instanceof GuiMainMenu) {
            fontRenderer.drawStringWithShadow(string, xCoord, yCoord, color1);
            if (lines == 2 || lines == 3) {
                fontRenderer.drawStringWithShadow(string2, xCoord2, yCoord2, color2);
                if (lines == 3) {
                    fontRenderer.drawStringWithShadow(string3, xCoord3, yCoord3, color3);
                }
            }
        }
    }
}
