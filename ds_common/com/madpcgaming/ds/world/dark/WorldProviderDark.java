package com.madpcgaming.ds.world.dark;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.lib.Strings;

public class WorldProviderDark extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerDark(BiomeGenDark.dark, 0.8F, 0.1F);
		this.dimensionId = DarkenedSouls.darkDimensionId;
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderDark(worldObj, worldObj.getSeed(), false);
	}

	public String getDimensionName()
	{
		return Strings.DARK_DIMENSION_NAME;
	}

}
