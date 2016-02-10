package net.sparklepopprograms.resonanttoolbox.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.sparklepopprograms.core.util.FormatHelper;
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
		List text = new ArrayList();
		
		text.add(EnumChatFormatting.GREEN + "Creates a Resonating Field when powered.");
		text.add(EnumChatFormatting.GREEN + "Has been know to make your head explode.");
		
		FormatHelper.addShiftTooltip(list, text);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

}
