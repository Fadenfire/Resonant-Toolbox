package net.sparklepopprograms.resonanttoolbox.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class InfinityBlastDamage extends EntityDamageSourceIndirect {

	public InfinityBlastDamage(Entity transmitter, Entity indirectSource) {
		super("infinityBlast", transmitter, indirectSource);
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
	}
	
	public static DamageSource causeInfinityDamage(Entity transmitter, Entity indirectSource) {
		return new InfinityBlastDamage(transmitter, indirectSource);
	}

}
