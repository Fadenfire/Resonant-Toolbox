package net.sparklepopprograms.resonanttoolbox.util;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.sparklepopprograms.core.util.LogHelper;
import net.sparklepopprograms.resonanttoolbox.ResonantToolbox;
import net.sparklepopprograms.resonanttoolbox.entity.InfinityBlast;

public class ResonantToolboxEventHooks {
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.isPotionActive(ResonantToolbox.immortality)) {
			EntityPlayer player = ((EntityPlayer)event.entityLiving);
			
			player.getFoodStats().addStats(10, 1.0F);
	      
		}
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		if (event.entityLiving.isPotionActive(ResonantToolbox.immortality)) {
			if (!(event.source == DamageSource.outOfWorld)) {
				if (!(event.source == InfinityDamage.causeInfinityDamage(event.source.getSourceOfDamage()))) {
					if (!(event.source == InfinityBlastDamage.causeInfinityDamage(new InfinityBlast(Minecraft.getMinecraft().theWorld), event.source.getSourceOfDamage()))) {
						event.setCanceled(true);
					}
				}
			}
		}
	}

}
