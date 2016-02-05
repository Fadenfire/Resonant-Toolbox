package net.sparklepopprograms.resonanttoolbox.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class Resonating_Engine extends Item {
	
	public Resonating_Engine() {
		super();
		this.setTextureName(ResonantToolbox.modid + ":Resonating_Engine");
		this.setUnlocalizedName("Resonating_Engine");
		this.setCreativeTab(ResonantToolboxTab.tab);
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean thing) {
		
	}

}
