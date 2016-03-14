package net.sparklepopprograms.resonanttoolbox.items;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.ItemEnergyContainer;
import net.java.games.input.Component;
import net.java.games.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.sparklepopprograms.core.helpers.FormatHelper;
import net.sparklepopprograms.core.helpers.WorldHelper;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.util.ConfigHandler;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class MaterialRelocationEnforcer extends Item implements IEnergyContainerItem {
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public MaterialRelocationEnforcer() {
		super();
		this.setUnlocalizedName("MRE");
		this.setMaxStackSize(1);
		this.setTextureName(ResonantToolbox.modid + ":MRE");
		this.setMaxTransfer(10000000);
		this.setCapacity(60000000);
		this.setCreativeTab(ResonantToolboxTab.tab);
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		if (!player.isSneaking()) {
			if (item.stackTagCompound.getInteger("Energy") >= ConfigHandler.MaterialRelocationEnforcerEnergyUsage * 1000) {
				item.stackTagCompound.setInteger("Energy", item.stackTagCompound.getInteger("Energy") - ConfigHandler.MaterialRelocationEnforcerEnergyUsage * 1000);
				WorldHelper.breakBlock(world, x, y, z, true, false);
			} else {
				if (!world.isRemote) {
					player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Not enough power!"));
				}
			}
		} else if (player.isSneaking()) {
			if (item.stackTagCompound.getInteger("Energy") >= 2 * ConfigHandler.MaterialRelocationEnforcerEnergyUsage * 1000) {
				item.stackTagCompound.setInteger("Energy", item.stackTagCompound.getInteger("Energy") - 2 * ConfigHandler.MaterialRelocationEnforcerEnergyUsage * 1000);
				WorldHelper.breakBlock(world, x, y, z, true, true);
			} else {
				if (!world.isRemote) {
					player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Not enough power!"));
				}
			}
		}
		
		return true;
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		List<String> text = new ArrayList<String>();
		
		text.add("Charge: " + FormatHelper.shortenNumber(item.stackTagCompound.getInteger("Energy")) + " / " + FormatHelper.shortenNumber(this.capacity) + " RF");
		text.add(EnumChatFormatting.GREEN + "Destroys blocks instantly.");
		text.add(EnumChatFormatting.GREEN + "Uses " + FormatHelper.shortenNumber(ConfigHandler.MaterialRelocationEnforcerEnergyUsage * 1000) + " RF per block.");
		
		FormatHelper.addShiftTooltip(list, text);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	public MaterialRelocationEnforcer setCapacity(int capacity) {

		this.capacity = capacity;
		return this;
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return capacity;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

}
