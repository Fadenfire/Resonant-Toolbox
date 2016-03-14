package net.sparklepopprograms.resonanttoolbox;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.sparklepopprograms.core.helpers.LogHelper;
import net.sparklepopprograms.resonanttoolbox.util.ConfigHandler;

public class ResonantToolboxRecipes {
	
	public static void RegisterRecipes() {
		
		ItemStack enderiumIngot = GameRegistry.findItemStack("ThermalFoundation", "ingotEnderium", 1);
		ItemStack enderiumIngot16 = GameRegistry.findItemStack("ThermalFoundation", "ingotEnderium", 16);
		ItemStack bronzeIngot = GameRegistry.findItemStack("ThermalFoundation", "ingotBronze", 1);
		
		ThermalExpansionHelper.addSmelterRecipe(120000, new ItemStack(ResonantToolboxItems.EyeOfInfinity, 32, 0),  new ItemStack(ResonantToolboxItems.Sapphire, 16, 1), new ItemStack(ResonantToolboxItems.EyeOfInfinity, 1, 1));
		ThermalExpansionHelper.addSmelterRecipe(120000, enderiumIngot16,  new ItemStack(ResonantToolboxItems.Sapphire, 32, 1), new ItemStack(ResonantToolboxItems.EyeOfInfinity, 1, 0));
		ThermalExpansionHelper.addSmelterRecipe(120000, new ItemStack(ResonantToolboxItems.Sapphire, 4, 0),  new ItemStack(Items.nether_star, 32, 0),  new ItemStack(ResonantToolboxItems.Sapphire, 1, 1));
		ThermalExpansionHelper.addPulverizerRecipe(2400, new ItemStack(ResonantToolboxBlocks.Sapphire_Ore), new ItemStack(ResonantToolboxItems.Sapphire, 2));
		
		if (ConfigHandler.enableMaterialRelocationEnforcer) {
		GameRegistry.addRecipe(new ShapedOreRecipe(ResonantToolboxItems.MRE, new Object[] {" OS", " EO", "I  ", 'S', new ItemStack(ResonantToolboxItems.Sapphire, 1, 1), 'I', ResonantToolboxItems.Iron_Rod, 'O', Blocks.obsidian, 'E', ResonantToolboxItems.Resonating_Engine}));
		}
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ResonantToolboxItems.Resonating_Engine, new Object[] {"ESE", "STS", "ESE", 'S', new ItemStack(ResonantToolboxItems.Sapphire, 1, 1), 'T', new ItemStack(ResonantToolboxItems.EyeOfInfinity, 1, 0), 'E', enderiumIngot}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ResonantToolboxItems.Iron_Rod, 4), new Object[] {"I", "I", 'I', Items.iron_ingot}));
		
		if (ConfigHandler.enableImortalityEngine) {
		GameRegistry.addRecipe(new ShapedOreRecipe(ResonantToolboxItems.Immortality_Engine, new Object[] {"ISI", "SES", "ISI", 'S', new ItemStack(ResonantToolboxItems.Sapphire, 1, 1), 'E', ResonantToolboxItems.Resonating_Engine, 'I', new ItemStack(ResonantToolboxItems.EyeOfInfinity, 1, 1)}));
		}
		
		if (ConfigHandler.enableInfinitySword) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ResonantToolboxItems.InfinitySword), new Object[] {" S ", " S ", "ERE", 'S', new ItemStack(ResonantToolboxItems.Sapphire, 1, 1), 'E', ResonantToolboxItems.Resonating_Engine, 'R', ResonantToolboxItems.Iron_Rod}));
		}
		
		LogHelper.info("Registered Recipes", ResonantToolbox.modid);
	}

}
