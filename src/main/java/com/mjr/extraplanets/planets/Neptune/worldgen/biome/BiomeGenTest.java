package com.mjr.extraplanets.planets.Neptune.worldgen.biome;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanetsBlocks;

public class BiomeGenTest extends BiomeGenBaseNeptune
{
	public BiomeGenTest()
	{
		super(225);
		this.enableRain = true;
		this.enableSnow = true;
		this.topBlock = ExtraPlanetsBlocks.neptuneBlocks;
		this.topMeta = 0;
		this.fillerBlock = ExtraPlanetsBlocks.neptuneBlocks;
		this.fillerMeta = 1;
		this.stoneBlock = ExtraPlanetsBlocks.neptuneBlocks;
		this.stoneMeta = 2;
	}
}