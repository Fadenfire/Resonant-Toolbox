package net.sparklepopprograms.resonanttoolbox.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.sparklepopprograms.resonanttoolbox.ResonantToolboxBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class SapphireOreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
			
		addOreSpawn(ResonantToolboxBlocks.Sapphire_Ore, world, random, x*16, z*16, 16, 16, 5, 10, 1, 70);
		
	}

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
          int maxPossY = minY + (maxY - 1);
          assert maxY > minY;
          assert maxX > 0 && maxX <= 16;
          assert minY > 0;
          assert maxY < 256 && maxY > 0;
          assert maxZ > 0 && maxZ <= 16;
         
          int diffBtwnMinMaxY = maxY - minY;
          for(int x = 0; x < chancesToSpawn; x++) {
                 int posX = blockXPos + random.nextInt(maxX);
                 int posY = minY + random.nextInt(diffBtwnMinMaxY);
                 int posZ = blockZPos + random.nextInt(maxZ);
                 (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
          }
    }
	
}