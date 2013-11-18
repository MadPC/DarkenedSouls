package com.madpcgaming.ds.api;

import java.util.ArrayList;

import net.minecraft.nbt.NBTBase;

public class DarkenedSoulsApi
{
	public static ArrayList<EntityTags> scanEntities = new ArrayList<EntityTags>();
	public static void registerEntityTag(String entityName, ElementList elements, NBTBase[] nbt)
	{
		scanEntities.add(new EntityTags(entityName, nbt, elements));
	}
	
	public static class EntityTags
	{
		public String entityName;
		public NBTBase[] nbts;
		public ElementList elements;
		public EntityTags(String entityName, NBTBase[] nbts, ElementList elements)
		{
			this.entityName = entityName;
			this.nbts = nbts;
			this.elements = elements;
		}
	}
	
	
}
