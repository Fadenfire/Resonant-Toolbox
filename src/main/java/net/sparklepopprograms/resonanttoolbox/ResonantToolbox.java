package net.sparklepopprograms.resonanttoolbox;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import scala.Int;
import scala.tools.nsc.settings.Final;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.sparklepopprograms.core.util.LogHelper;
import net.sparklepopprograms.resonanttoolbox.entity.InfinityBlast;
import net.sparklepopprograms.resonanttoolbox.render.RenderInfinityBlast;
import net.sparklepopprograms.resonanttoolbox.util.ConfigHandler;
import net.sparklepopprograms.resonanttoolbox.util.PotionReg;
import net.sparklepopprograms.resonanttoolbox.util.ResonantToolboxEventHooks;
import net.sparklepopprograms.resonanttoolbox.util.SapphireOreGenerator;
import cpw.mods.fml.common.Mod.EventHandler;


@Mod(modid = ResonantToolbox.modid, name = "Resonant Toolbox", version = ResonantToolbox.version , dependencies = ResonantToolbox.dependencies)

public class ResonantToolbox {
	
	public static final String modid = "ResonantToolbox";
	public static final String version = "1.0.4";
	public static final String dependencies = "required-after:DimensionalCore@[1.0.2,);required-after:ThermalExpansion@[1.7.10R4.1.1B237,)";

	public static Potion immortality;
	
	@EventHandler
	public void load(FMLPreInitializationEvent event) {
		
		ConfigHandler.initProps();
		
		if (ConfigHandler.enableSapphireOreGen) {
			GameRegistry.registerWorldGenerator(new SapphireOreGenerator(), 0);
			LogHelper.info("Registered Sapphire World Generator", modid);
		}
		
		MinecraftForge.EVENT_BUS.register(new ResonantToolboxEventHooks());
		
		Potion[] potionTypes;

        for (Field f : Potion.class.getDeclaredFields())
        {
            f.setAccessible(true);

            try
            {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
                {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e)
            {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }
		
		LogHelper.info("Finished Pre Initialization", modid);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		ResonantToolboxItems.RegisterItems();
		ResonantToolboxBlocks.RegisterBlocks();
		ResonantToolboxRecipes.RegisterRecipes();
		
		immortality = (new PotionReg(ConfigHandler.ImmortalityPotionID, false, 0)).setPotionName("potion.immortality");
		LogHelper.info("Potion Effect Registered", modid);
		
		EntityRegistry.registerModEntity(InfinityBlast.class, "InfinityBlast", 90, this, 120, 3, true);
		RenderingRegistry.registerEntityRenderingHandler(InfinityBlast.class, new RenderInfinityBlast());
		
		LogHelper.info("Finished Initialization", modid);
	}
	
	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		
		
		LogHelper.info("Finished Post Initialization", modid);
	}
}