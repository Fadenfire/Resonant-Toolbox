package net.sparklepopprograms.resonanttoolbox.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class InfinityDamage extends EntityDamageSource {

	public InfinityDamage(Entity transmitter) {
		super("infinity", transmitter);
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
	}
	
	public static DamageSource causeInfinityDamage(Entity transmitter) {
		return new InfinityDamage(transmitter);
	}

}
