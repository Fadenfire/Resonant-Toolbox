package net.sparklepopprograms.resonanttoolbox.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.ItemEnergyContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.sparklepopprograms.core.util.FormatHelper;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.util.ConfigHandler;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class Immortality_Engine extends Item implements IEnergyContainerItem {
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public Immortality_Engine() {
		super();
		this.setTextureName(ResonantToolbox.modid + ":Immortality_Engine");
		this.setUnlocalizedName("Immortality_Engine");
		this.setCreativeTab(ResonantToolboxTab.tab);
		this.setMaxStackSize(1);
		this.setMaxTransfer(10000000);
		this.setCapacity(600000000);
	}

	@Override
	public void onUpdate(ItemStack item, World world, Entity player, int p_77663_4_, boolean p_77663_5_) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		
		if (item.getItem().getDamage(item) == 1) {
			if (item.stackTagCompound.getInteger("Energy") >= ConfigHandler.ImortalityEngineEnergyUsage * 1000) {
				item.stackTagCompound.setInteger("Energy", item.stackTagCompound.getInteger("Energy") - ConfigHandler.ImortalityEngineEnergyUsage * 1000);
				((EntityPlayer)player).addPotionEffect(new PotionEffect(ConfigHandler.ImmortalityPotionID, 3, 0, true));
			}
		}
		
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean thing) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		
		List text = new ArrayList();
		
		text.add("Charge: " + FormatHelper.shortenNumber(item.stackTagCompound.getInteger("Energy")) + " / " + FormatHelper.shortenNumber(this.capacity) + " RF");
		text.add(EnumChatFormatting.GREEN + "Grants Immortality to the user.");
		text.add(EnumChatFormatting.GREEN + "Uses " + FormatHelper.shortenNumber(ConfigHandler.ImortalityEngineEnergyUsage * 1000) + " RF per tick.");
		
		if (item.getItem().getDamage(item) == 1) {
			text.add(EnumChatFormatting.YELLOW + "Use while sneaking to deacitvate.");
		} else if (item.getItem().getDamage(item) == 0) {
			text.add(EnumChatFormatting.YELLOW + "Use while sneaking to acitvate.");
		}
		
		FormatHelper.addShiftTooltip(list, text);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		
		if (player.isSneaking()) {
			if (item.getItem().getDamage(item) == 1) {
				item.getItem().setDamage(item, 0);
			} else if (item.getItem().getDamage(item) == 0) {
				item.getItem().setDamage(item, 1);
			}
		}
		return item;
	}

	@Override
	public boolean hasEffect(ItemStack item, int pass) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		
		if (item.getItem().getDamage(item) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Immortality_Engine setCapacity(int capacity) {

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
		 return EnumRarity.epic;
	}

}
