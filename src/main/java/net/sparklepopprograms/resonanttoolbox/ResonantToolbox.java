package net.sparklepopprograms.resonanttoolbox;

import scala.Int;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sparklepopprograms.core.util.LogHelper;
import net.sparklepopprograms.resonanttoolbox.util.SapphireOreGenerator;
import cpw.mods.fml.common.Mod.EventHandler;


@Mod(modid = ResonantToolbox.modid, name = "Resonant Toolbox", version = ResonantToolbox.version , dependencies = ResonantToolbox.dependencies)

public class ResonantToolbox {
	
	public static final String modid = "ResonantToolbox";
	public static final String version = "1.0.1";
	public static final String dependencies = "required-after:DimensionalCore@[1.0.1,);required-after:ThermalExpansion@[1.7.10R4.1.1B237,)";

	@EventHandler
	public void load(FMLPreInitializationEvent event) {
		
		GameRegistry.registerWorldGenerator(new SapphireOreGenerator(), 0);
		LogHelper.info("Registered Sapphire World Generator", modid);
		
		LogHelper.info("Finished Pre Initialization", modid);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		ResonantToolboxItems.RegisterItems();
		ResonantToolboxBlocks.RegisterBlocks();
		ResonantToolboxRecipes.RegisterRecipes();
		
		LogHelper.info("Finished Initialization", modid);
	}
	
	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		
		
		LogHelper.info("Finished Post Initialization", modid);
	}
}