package com.madpcgaming.ds.world.dark;

import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerDark extends WorldChunkManager
{
	private BiomeGenDark biomeToUse;
	private float darkTemperature;
	private float rainfall;
	
	public WorldChunkManagerDark(BiomeGenDark par1, float par2, float par3)
	{
		this.biomeToUse = par1;
		this.darkTemperature = par2;
		this.rainfall = par3;
	}

}
