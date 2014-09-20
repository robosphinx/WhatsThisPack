package robosphinx.mods.wtp.client.renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import robosphinx.mods.wtp.util.LogHelper;

public class IconRenderer {

    private BufferedImage    image;
    private DynamicTexture   iconTexture;
    private ResourceLocation resourceLocation;
    private TextureManager   textureManager;
    private LogHelper        log;

    public IconRenderer(TextureManager textureManager, BufferedImage buffer) {
        this.textureManager = textureManager;
        this.image = buffer;
    }

    public void loadIcon() {
        iconTexture = new DynamicTexture(image);
        resourceLocation = textureManager.getDynamicTextureLocation("icon", iconTexture);
    }

    public void drawImage(int x, int y, int width, int height) {
        Tessellator t = Tessellator.instance;
        textureManager.bindTexture(resourceLocation);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        t.startDrawingQuads();
        t.addVertexWithUV(x, y + height, 0, 0.0, 1.0);
        t.addVertexWithUV(x + width, y + height, 0, 0.0, 1.0);
        t.addVertexWithUV(x + width, y, 0, 0.0, 1.0);
        t.addVertexWithUV(x, y, 0, 0.0, 1.0);
        t.draw();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
