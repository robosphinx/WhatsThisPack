package robosphinx.mods.wtp.plugins;

import net.minecraftforge.fml.common.event.FMLInterModComms;
import robosphinx.mods.wtp.reference.Reference;


/**
 * Supports Dynious's Version Checker mod. Support will only be added if the Version Checker mod
 * is installed. To find out more information about the Version Checker mod, visit
 * http://www.minecraftforum.net/topic/2721902
 */

public class VersionCheckPlugin {
    
    public VersionCheckPlugin(boolean enabled) {
        if (enabled) {
            String infoLink = "https://dl.dropboxusercontent.com/u/80106524/files/versions/WTP_Version_Info.txt";
            FMLInterModComms.sendRuntimeMessage(Reference.MOD_ID, "VersionChecker", "addVersionCheck", infoLink);
        }
    }
}
