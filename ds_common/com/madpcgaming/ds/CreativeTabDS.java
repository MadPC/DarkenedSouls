package com.madpcgaming.ds;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabDS extends CreativeTabs
{

	public CreativeTabDS(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	
	public int getTabIconItemIndex()
	{
		return Item.enderPearl.itemID;
	}


}
