package com.madpcgaming.ds.api;

import java.io.Serializable;
import java.util.LinkedHashMap;

import net.minecraft.item.ItemStack;

public class ElementList implements Serializable
{
	public LinkedHashMap<Elements, Integer> elements = new LinkedHashMap();
	
	public ElementList(int id, int meta)
	{
		try
		{
			ElementList temp = DarkenedSoulsApiHelper.getObjectElements(new ItemStack(id, 1, meta));
			if(temp != null)
				for(Elements tag: temp.getElements())
					add(tag, temp.getAmount(tag));
		} catch (Exception e) {
		}
	}
	
	public ElementList()
	{
		
	}
	
	public int size()
	{
		return this.elements.size();
	}
	
	public Elements[] getElements()
	{
		Elements[] q = new Elements[1];
		return (Elements[]) this.elements.keySet().toArray(q);
	}
	
	public int getAmount(Elements key)
	{
		return this.elements.get(key) == null ? 0 : ((Integer)this.elements.get(key)).intValue();
	}
	
	public ElementList remove(Elements key)
	{
		this.elements.remove(key);
		return this;
	}
	
	public ElementList remove(Elements key, int amount)
	{
		int am = getAmount(key) - amount;
		if (am <= 0) this.elements.remove(key); else
			this.elements.put(key, Integer.valueOf(am));
		return this;
	}
	
	public ElementList add(Elements elements, int amount)
	{
		if(this.elements.containsKey(elements)){
			int oldamount = ((Integer)this.elements.get(elements)).intValue();
			amount += oldamount;
		}
		this.elements.put(elements, Integer.valueOf(amount));
		return this;
	}
}
