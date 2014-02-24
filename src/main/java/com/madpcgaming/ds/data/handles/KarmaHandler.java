package com.madpcgaming.ds.data.handles;

import com.madpcgaming.ds.data.KarmaSaver;
import com.madpcgaming.ds.helpers.LoggingHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;

import java.util.HashMap;

public class KarmaHandler {

	public static final KarmaHandler instance = new KarmaHandler();

	private HashMap<String, Integer> karmalist = new HashMap<String, Integer>();
	private static KarmaSaver save;

	//sneakily keep a copy of the world ...
	private World w;

	public void loadKarma(World world)
	{
		w = world;
		save = (KarmaSaver) world.perWorldStorage.loadData(KarmaSaver.class, "Karma");
		if (save == null)
		{
			LoggingHelper.info("Couldn't get a WorldSavedData handle. (IFF this happens more than once per world it is most likely a bug.)");
			save = new KarmaSaver();
		}
		save.fill(karmalist);
	}

	public void saveKarma(World world)
	{
		if (world == null)
			world = w;
		MapStorage store = world.perWorldStorage;

		save.updateAll(karmalist);

		store.setData("Karma", save);
		store.saveAllData();
	}

	public int update(String name, int karma)
	{
		return karmalist.put(name, karma);
	}
}
