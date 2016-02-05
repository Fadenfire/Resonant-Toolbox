package net.sparklepopprograms.resonanttoolbox.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sparklepopprograms.resonanttoolbox.ResonantToolboxItems;

public class ResonantToolboxTab extends CreativeTabs {
	
	public static final CreativeTabs tab = new ResonantToolboxTab(CreativeTabs.getNextID(), "ResonantToolboxTab");

	public ResonantToolboxTab(int id, String name) {
		super(id, name);
	}

	@Override
	public Item getTabIconItem() {
		return ResonantToolboxItems.MRE;
	}

}
