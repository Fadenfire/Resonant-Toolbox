package net.sparklepopprograms.resonanttoolbox.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.sparklepopprograms.core.util.FormatHelper;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class EyeOfInfinity extends Item {
	
	public EyeOfInfinity() {
		super();
		this.setTextureName(ResonantToolbox.modid + ":EyeOfInfinity");
		this.setUnlocalizedName("EyeOfInfinity");
		this.setCreativeTab(ResonantToolboxTab.tab);
		this.setHasSubtypes(true);
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean thing) {
		List text = new ArrayList();
		
		text.add(EnumChatFormatting.GREEN + "The point where all of infinity converges");
		if (item.getItemDamage() == 1) {
			text.add(EnumChatFormatting.GREEN + "Empowered, includes all dimensions.");
		}
		
		FormatHelper.addShiftTooltip(list, text);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item) {
		if (item.getItemDamage() == 1) {
			return EnumRarity.epic;
		} else {
			return EnumRarity.rare;
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 2; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

	@Override
	public boolean hasEffect(ItemStack item, int meta) {
		if (item.getItemDamage() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
