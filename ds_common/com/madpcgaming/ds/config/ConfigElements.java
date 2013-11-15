package com.madpcgaming.ds.config;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;

import com.madpcgaming.ds.DarkenedSouls;
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
		/**
		 * Registers Entities Elements by:
		 * 1. Naming the entity
		 * 2. Adding the element(s)
		 * 3. Using NBT to register
		 * You can have multiple Elements for each entity.
		 * When adding the Elements by using ElementList().add(Elements.ELEMENTHERE)
		 * Also you can send the amount of that Element here.
		 * @author Daniel
		 */
		//Evil mobs
		DarkenedSoulsApi.registerEntityTag("Zombie", new ElementList().add(Elements.DEATH, 2).add(Elements.EARTH, 1).add(Elements.EVIL, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Skeleton", new ElementList().add(Elements.DEATH, 1).add(Elements.EARTH, 1).add(Elements.EVIL, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Skeleton", new ElementList().add(Elements.DEATH, 1).add(Elements.FIRE, 2).add(Elements.EVIL, 2), new NBTBase[] { new NBTTagByte("SkeletonType") });
		DarkenedSoulsApi.registerEntityTag("Blaze", new ElementList().add(Elements.FIRE, 2).add(Elements.EVIL, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Creeper", new ElementList().add(Elements.EVIL, 3).add(Elements.FIRE, 1), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Endermam", new ElementList().add(Elements.AIR, 1).add(Elements.DARK, 2).add(Elements.EVIL, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Spider", new ElementList().add(Elements.AIR, 1).add(Elements.DARK, 2).add(Elements.EVIL, 2), new NBTBase[0]);
		//Good mobs
		DarkenedSoulsApi.registerEntityTag("Cow", new ElementList().add(Elements.GOOD, 1).add(Elements.LIFE, 1).add(Elements.LIGHT, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Pig", new ElementList().add(Elements.GOOD, 1).add(Elements.LIFE, 1).add(Elements.LIGHT, 2), new NBTBase[0]);
		DarkenedSoulsApi.registerEntityTag("Chicken", new ElementList().add(Elements.GOOD, 1).add(Elements.LIFE, 1).add(Elements.AIR, 2).add(Elements.LIGHT, 2), new NBTBase[0]);
	}
}
