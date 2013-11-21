package com.madpcgaming.ds.world.light;

import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerLight extends WorldChunkManager
{
	private BiomeGenLight biomeToUse;
	private float lightTemperature;
	private float rainfall;
	
	public WorldChunkManagerLight(BiomeGenLight par1, float par2, float par3)
	{
		this.biomeToUse = par1;
		this.lightTemperature = par2;
		this.rainfall = par3;
	}

}
