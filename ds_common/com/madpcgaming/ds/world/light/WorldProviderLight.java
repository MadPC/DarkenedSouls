package com.madpcgaming.ds.world.light;

import net.minecraft.world.WorldProvider;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.lib.Strings;

public class WorldProviderLight extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerLight(null, 0.8F, 0.1F);
		this.dimensionId = DarkenedSouls.lightDimensionId;
	}
	public String getDimensionName()
	{
		return Strings.LIGHT_DIMENSION_NAME;
	}

}
