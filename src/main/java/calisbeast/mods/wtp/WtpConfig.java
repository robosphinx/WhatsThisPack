package calisbeast.mods.wtp;

/**
 * @author robosphinx, calisbeast
 */

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;

public class WtpConfig {
	
	/*
	 * This is where we load the config file, set and/or get the values we need, and then save the file.
	 */
	public static void loadConfig(Configuration config) {
		config.load();
		strings.add(config.get(Configuration.CATEGORY_GENERAL, "Modpack Name", "Change this in your configs!!").getString());
		ints.add(config.get("Hex or MSAccess value - Go to www[dot]color-hex[dot]com - default 16777215 or 0xffffff (both white)", "String color", 16777215).getInt());
		config.save();
	}
	
	/*
	 * These array lists are where we store the values to be accessed elsewhere in the mod.
	 */
	public static ArrayList<String> strings = new ArrayList<String>();
	public static ArrayList<Integer> ints = new ArrayList<Integer>();

}
