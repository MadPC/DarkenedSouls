package com.madpcgaming.ds.config;

import net.minecraft.nbt.NBTBase;

import com.madpcgaming.ds.api.DarkenedSoulsApi;
import com.madpcgaming.ds.api.ElementList;
import com.madpcgaming.ds.api.Elements;

public class ConfigElements
{
	public static void init()
	{
		registerEntityElements();
	}
	
	private static void registerEntityElements()
	{
		DarkenedSoulsApi.registerEntityTag("Zombie", new ElementList().add(Elements.SHADOW, 2).add(Elements.EARTH, 1), new NBTBase[0]);
	}
}
