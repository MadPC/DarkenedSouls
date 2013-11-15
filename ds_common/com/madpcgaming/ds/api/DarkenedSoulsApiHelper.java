package com.madpcgaming.ds.api;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class DarkenedSoulsApiHelper
{
	static Method getObjectElements;
	static Method getObjectTags;
	
	public static ElementList cullTags(ElementList temp)
	{
		ElementList temp2 = new ElementList();
		for(Elements tag : temp.getElements())
		{
			if(tag != null)
				temp2.add(tag, temp.getAmount(tag));
		}
		while ((temp2 != null) && (temp2.size() > 10)){
			Elements lowest = null;
			int low = 2147483647;
			for(Elements tag: temp2.getElements()) {
				if((tag != null) && (temp2.getAmount(tag) < low))
				{
					low = temp2.getAmount(tag);
					lowest = tag;
				}
			}
			temp2.elements.remove(lowest);
		}
		return temp2;
	}
	
	public static ElementList getObjectElements(ItemStack is)
	{
		ElementList ot = null;
		try{
			if(getObjectTags == null){
				Class fake = Class.forName("ds_common.com.madpcgaming.ds.lib.DarkenedSoulsCraftingManager");
				getObjectTags = fake.getMethod("getObjectTags", new Class[] { ItemStack.class});
			}
			ot = (ElementList)getObjectTags.invoke(null, new Object[] {is});
		}catch (Exception ex){
			FMLLog.warning("[Darkened Souls API] Could not invoke ds_common.com.madpcgaming.ds.lib.DarkenedSoulsCraftingManager method getObjectTags", new Object[0]);
		}
		return ot;
	}
}
