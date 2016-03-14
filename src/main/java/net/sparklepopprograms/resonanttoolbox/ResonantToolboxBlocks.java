package net.sparklepopprograms.resonanttoolbox;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.sparklepopprograms.core.helpers.LogHelper;
import net.sparklepopprograms.resonanttoolbox.blocks.Sapphire_Ore;
import net.sparklepopprograms.resonanttoolbox.items.MaterialRelocationEnforcer;

public class ResonantToolboxBlocks {
	
	public static Block Sapphire_Ore;

	public static void RegisterBlocks() {
		
		Sapphire_Ore = new Sapphire_Ore(Material.rock);
		
		GameRegistry.registerBlock(Sapphire_Ore, "Sapphire_Ore");
		
		LogHelper.info("Registered Blocks", ResonantToolbox.modid);
	}

}
