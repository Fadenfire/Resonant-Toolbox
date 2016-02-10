package net.sparklepopprograms.resonanttoolbox.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.sparklepopprograms.resonanttoolbox.util.InfinityBlastDamage;

public class InfinityBlast extends EntityThrowable {

	public InfinityBlast(World world) {
		super(world);
	}

	public InfinityBlast(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public InfinityBlast(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null) {
			mop.entityHit.attackEntityFrom(InfinityBlastDamage.causeInfinityDamage(this, this.getThrower()), 250000);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

}
