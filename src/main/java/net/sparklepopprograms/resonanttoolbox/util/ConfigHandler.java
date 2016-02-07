package net.sparklepopprograms.resonanttoolbox.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	public static final String CATEGORY_IDS = "PotionIds";
	
	public static int ImmortalityPotionID;
	public static int ImortalityEngineEnergyUsage;
	public static int MaterialRelocationEnforcerEnergyUsage;
	
	public static boolean enableMaterialRelocationEnforcer;
	public static boolean enableImortalityEngine;
	public static boolean enableSapphireOreGen;
	
	public static void initProps() {
		File mainFile = new File("config/Silly511/ResonantToolbox.cfg");
		
		Configuration config = new Configuration(mainFile);
		config.load();
		
		enableSapphireOreGen = config.get(Configuration.CATEGORY_GENERAL, "enableSapphireOreGen", true).getBoolean(true);
		enableMaterialRelocationEnforcer = config.get(Configuration.CATEGORY_GENERAL, "enableMaterialRelocationEnforcer", true).getBoolean(true);
		enableImortalityEngine = config.get(Configuration.CATEGORY_GENERAL, "enableImortalityEngine", true).getBoolean(true);
		
		ImortalityEngineEnergyUsage = config.get(Configuration.CATEGORY_GENERAL, "ImortalityEngineEnergyUsage", 50).getInt(50);
		MaterialRelocationEnforcerEnergyUsage = config.get(Configuration.CATEGORY_GENERAL, "MaterialRelocationEnforcerEnergyUsage", 200).getInt(200);
		
		ImmortalityPotionID = config.get(ConfigHandler.CATEGORY_IDS, "ImmortalityPotionID", 70).getInt(70);
		 
		
		if (config.hasChanged()) {
			config.save();
		}
	}
	

}
