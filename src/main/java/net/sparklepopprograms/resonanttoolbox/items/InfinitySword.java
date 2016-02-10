package net.sparklepopprograms.resonanttoolbox.items;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.sparklepopprograms.core.util.FormatHelper;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.entity.InfinityBlast;
import net.sparklepopprograms.resonanttoolbox.util.ConfigHandler;
import net.sparklepopprograms.resonanttoolbox.util.InfinityDamage;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxTab;

public class InfinitySword extends Item implements IEnergyContainerItem {
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public InfinitySword() {
		super();
		this.setUnlocalizedName("InfinitySword");
		this.setTextureName(ResonantToolbox.modid + ":InfinitySword");
		this.setMaxStackSize(1);
		this.setCreativeTab(ResonantToolboxTab.tab);
		this.setMaxTransfer(10000000);
		this.setCapacity(60000000);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		List<String> text = new ArrayList<String>();
		
		text.add("Charge: " + FormatHelper.shortenNumber(item.stackTagCompound.getInteger("Energy")) + " / " + FormatHelper.shortenNumber(this.capacity) + " RF");
		text.add(EnumChatFormatting.GREEN + "Slices things up.");
		text.add(EnumChatFormatting.GREEN + "Uses " + FormatHelper.shortenNumber(ConfigHandler.InfinitySwordEnergyUsage * 1000) + " RF per thing you slice up.");
		
		FormatHelper.addShiftTooltip(list, text);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack item, EntityPlayer player, Entity entity) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		if (item.stackTagCompound.getInteger("Energy") >= ConfigHandler.InfinitySwordEnergyUsage * 1000) {
			item.stackTagCompound.setInteger("Energy", item.stackTagCompound.getInteger("Energy") - ConfigHandler.InfinitySwordEnergyUsage * 1000);
			entity.attackEntityFrom(InfinityDamage.causeInfinityDamage(player), 25000000);
		} else {
			if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Not enough power!"));
			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
		}
		if (item.stackTagCompound.getInteger("Energy") >= ConfigHandler.InfinitySwordEnergyUsage * 1000) {
			item.stackTagCompound.setInteger("Energy", item.stackTagCompound.getInteger("Energy") - ConfigHandler.InfinitySwordEnergyUsage * 1000);
			if (!world.isRemote) {
				world.spawnEntityInWorld(new InfinityBlast(world, player));
			}
		} else {
			if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Not enough power!"));
			}
		}
		return item;
		
	}
	
	public InfinitySword setCapacity(int capacity) {

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
