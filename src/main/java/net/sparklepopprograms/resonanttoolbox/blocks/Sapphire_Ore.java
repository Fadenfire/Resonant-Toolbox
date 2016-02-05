package net.sparklepopprograms.resonanttoolbox.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.ResonantToolboxItems;

public class Sapphire_Ore extends Block {

	public Sapphire_Ore(Material mat) {
		super(mat);
		this.setBlockName("Sapphire_Ore");
		this.setBlockTextureName(ResonantToolbox.modid + ":Sapphire_Ore");
		this.setHardness(3.0F);
		this.setHarvestLevel("pickaxe", 2);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return ResonantToolboxItems.Sapphire;
	}
	
	public int quantityDropped(Random rand) {
		return 1;
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) {
		return MathHelper.getRandomIntegerInRange(new Random(), 1, 3);
	}

}
