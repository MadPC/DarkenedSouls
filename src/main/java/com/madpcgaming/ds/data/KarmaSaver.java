package com.madpcgaming.ds.data;

import com.madpcgaming.ds.helpers.LoggingHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;

/**
 * Created by Rene on 24.02.14.
 */
public class KarmaSaver extends WorldSavedData {

	private NBTTagList list;
	private ArrayList<String> names;

	public KarmaSaver(String par1Str) {
		super(par1Str);
		list = new NBTTagList();
		names = new ArrayList<String>();
	}

	public KarmaSaver()
	{
		this("Karma-Levels");
	}

	public void fill(HashMap<String, Integer> map)
	{
		for (int x = 0; x < list.tagCount(); x++)
		{
			NBTTagCompound tag = list.getCompoundTagAt(x);
			map.put(tag.getString("name"), tag.getInteger("level"));
		}
	}

	public void update(String name, int karma)
	{
		int in = names.indexOf(name);
		if (in == -1)
		{
			names.add(name);
			in = names.indexOf(name);
		}
		NBTTagCompound tag = list.getCompoundTagAt(in);
		tag.setInteger("level", karma);
		list.appendTag(tag);
	}

	public void updateAll(HashMap<String, Integer> map)
	{
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<String, Integer> cur = it.next();
			update(cur.getKey(), cur.getValue());
		}
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		if (!tag.hasKey("Karma-Levels"))
		{
			tag.setTag("Karma-Levels", list);
		}
		list = (NBTTagList) tag.getTag("Karma-Levels");
		for (int x = 0; x < list.tagCount(); x++)
		{
			NBTTagCompound cur = list.getCompoundTagAt(x);
			names.add(x, cur.getString("name"));
			LoggingHelper.warn("Loaded %s karma for player %s", cur.getInteger("level"), cur.getString("name"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		if (list != null)
		{
			tag.setTag("Karma-Levels", list);
			LoggingHelper.info("Saving karma-values");
		}
		else
		{
			LoggingHelper.fatal("Tried to save nonexistent karma list. (This is most likely a bug.)");
		}
		this.markDirty();
	}
}
