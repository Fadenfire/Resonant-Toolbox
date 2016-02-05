package net.sparklepopprograms.resonanttoolbox;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.sparklepopprograms.core.util.LogHelper;
import net.sparklepopprograms.resonanttoolbox.items.*;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class ResonantToolboxItems {
	
	public static Item MRE;
	public static Item Sapphire;
	public static Item Iron_Rod;
	public static Item Resonating_Engine;
	
	public static void RegisterItems() {
		
		MRE = new MaterialRelocationEnforcer();
		Sapphire = new Sapphire();
		Iron_Rod = new Item().setUnlocalizedName("Iron_Rod").setTextureName(ResonantToolbox.modid + ":Iron_Rod").setCreativeTab(ResonantToolboxTab.tab);
		Resonating_Engine = new Resonating_Engine();
		
		GameRegistry.registerItem(MRE, "MRE");
		GameRegistry.registerItem(Sapphire, "Sapphire");
		GameRegistry.registerItem(Iron_Rod, "Iron_Rod");
		GameRegistry.registerItem(Resonating_Engine, "Resonating_Engine");
		
		OreDictionary.registerOre("gemSapphire", new ItemStack(Sapphire, 1, 0));
		
		LogHelper.info("Registered Items", ResonantToolbox.modid);
	}

}
