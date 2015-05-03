package robosphinx.mods.wtp.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class ResourceHandler {
    
    private static File fileAssets;
    private static File fileMods;
    private static File fileConfig;

    @SideOnly(Side.CLIENT)
    public static void init() {
        fileAssets = new File(Minecraft.getMinecraft().mcDataDir, "assets");
        fileMods = new File(Minecraft.getMinecraft().mcDataDir, "mods");
        fileConfig = new File(Minecraft.getMinecraft().mcDataDir, "config");
    }

    /**
     * To be honest, this is actually mostly unnecessary because most "assets" for mods are in the mod's zips.
     * @return /assets/ folder.
     */
    public static File getAssetsFolder() {
        return fileAssets;
    }

    public static File getModsFolder() {
        return fileMods;
    }

    public static File getConfigFolder() {
        return fileConfig;
    }
}