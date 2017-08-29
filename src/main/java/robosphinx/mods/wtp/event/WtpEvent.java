package robosphinx.mods.wtp.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import robosphinx.mods.wtp.handler.ConfigHandler;
import robosphinx.mods.wtp.util.LogHelper;

import java.io.IOException;
import java.util.Random;

import static net.minecraftforge.common.ForgeVersion.Status.BETA_OUTDATED;
import static net.minecraftforge.common.ForgeVersion.Status.OUTDATED;

public class WtpEvent extends GuiScreen {

    /*
     * These get an instance of Minecraft and it's FontRenderer, because we can't make a direct static reference to something that isn't static.
     */
    private Minecraft           mc           = Minecraft.getMinecraft();
    private ForgeVersion.Status status       = ForgeVersion.getStatus();
    private FontRenderer        fontRenderer = mc.fontRenderer;
    private LogHelper        log;
    private ScaledResolution scale;
    public static WtpEvent   instance     = new WtpEvent();
    private static Random    rand         = new Random();
    
    private int[]            xCoord;
    private int[]            yCoord;
    private int              line;
    private boolean          linePicked = false;
    
    /*
     * gets our values from the config.
     */
    private String[]         string       = ConfigHandler.message;
    private int[]            color        = ConfigHandler.color;
    private int              textPos      = ConfigHandler.guiPos;
    private int              lines        = ConfigHandler.lines;
    private boolean          allInOne     = ConfigHandler.allInOne;
    
    private void pickLine() {
        if (!linePicked) {
            line = rand.nextInt(lines);
            linePicked = true;
        }
    }
    
    private void initArrays() {
        xCoord = new int[lines];
        yCoord = new int[lines];
    }
    
    /*
     * This is the actual event we're tapping into - every time the GUI is an instance of the Main Menu, we will call to render our text.
     */
    @SubscribeEvent
    public void onRenderMainMenu(GuiScreenEvent event) throws IOException {
        pickLine();
        initArrays();
        scale = new ScaledResolution(mc);
        if (textPos == 0) {
            // Upper left
            if(allInOne) {
                for (int i = 0; i < lines; i++) {
                    xCoord[i] = 2;
                    if (i == 0) {
                        yCoord[i] = 2;
                    } else {
                        yCoord[i] = yCoord[i - 1] + 10;
                    }
                }
            }
            else {
                xCoord[line] = 2;
                yCoord[line] = 2;
            }
        }
        else if (textPos == 1) {
            // Upper right
            if (allInOne) {
                for (int i = 0; i < lines; i++) {
                    xCoord[i] = scale.getScaledWidth() - fontRenderer.getStringWidth(string[i]) - 2;
                    if (i == 0) {
                        yCoord[i] = 2;
                    } else {
                        yCoord[i] = yCoord[i - 1] + 10;
                    }
                }
            }
            else {
                xCoord[line] = scale.getScaledWidth() - fontRenderer.getStringWidth(string[line]) - 2;
                yCoord[line] = 2;
            }
        }
        else if (textPos == 2) {
            if (allInOne) {
                // Lower left
                for (int i = 0; i < lines; i++) {
                    xCoord[i] = 2;
                    yCoord[i] = scale.getScaledHeight() - 50 - (10 * (lines - i - 1));
                }
            }
            else {
                xCoord[line] = 2;
                yCoord[line] = scale.getScaledHeight() - 50 - 10;
            }
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
            if (allInOne) {
                // Lower right
                for (int i = 0; i < lines; i++) {
                    xCoord[i] = scale.getScaledWidth() - fontRenderer.getStringWidth(string[i]) - 2;
                    yCoord[i] = scale.getScaledHeight() - y - (10 * (lines - i));
                }
            }
            else {
                xCoord[line] = scale.getScaledWidth() - fontRenderer.getStringWidth(string[line]) - 2;
                yCoord[line] = scale.getScaledHeight() - y - 10;
            }
        }
        if (event.getGui() instanceof GuiMainMenu) {
            if (allInOne) {
                for (int i = 0; i < lines; i++) {
                    fontRenderer.drawStringWithShadow(string[i], xCoord[i], yCoord[i], color[i]);
                }
            }
            else {
                fontRenderer.drawStringWithShadow(string[line], xCoord[line], yCoord[line], color[line]);
            }
        }
        else {
            linePicked = false;
        }
    }
}
