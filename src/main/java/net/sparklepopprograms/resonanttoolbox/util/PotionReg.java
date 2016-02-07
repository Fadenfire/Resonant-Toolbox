package net.sparklepopprograms.resonanttoolbox.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

public class PotionReg extends Potion {
	
	public PotionReg(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    @Override
    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }

}
