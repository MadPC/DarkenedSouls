package com.madpcgaming.ds.data.handles;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.data.KarmaSaver;
import com.madpcgaming.ds.helpers.LoggingHelper;
import com.madpcgaming.ds.networking.DSKarmaPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;

import java.util.HashMap;
import java.util.Iterator;

public class KarmaHandler {

	public static final KarmaHandler instance = new KarmaHandler();

	private HashMap<String, Integer> karmalist = new HashMap<String, Integer>();
	private HashMap<Class<?>, Integer> karmaValue = new HashMap<Class<?>, Integer>();

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

		//Assume, if this fails Minecraft failed ...
		Iterator it = EntityList.IDtoClassMapping.entrySet().iterator();
		while (it.hasNext())
		{
			Object c = it.next();
			if (c instanceof IMob)
			{
				karmaValue.put(c.getClass(), 10);
			}
			else
			{
				karmaValue.put(c.getClass(), -10);
			}
		}
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

	public int update(String name, int karma, Side s)
	{
		int i = this.update(name, karma);
		if (s.isServer())
		{
			if (w == null)
				w = FMLServerHandler.instance().getServer().getEntityWorld();
			DarkenedSouls.handler.sendToClient(new DSKarmaPacket(karma, name), (EntityPlayerMP) w.getPlayerEntityByName(name));
		}
		return i;
	}

	/**
	 * This method should <b>ONLY</b> be called from the serverside.
	 * @param entity The entity that is used as a reference
	 * @param player The player being influenced
	 */
	public void handle(EntityLivingBase entity, EntityPlayerMP player)
	{
		Class<?> clazz = entity.getClass();
		if (karmaValue.containsKey(clazz))
		{
			int karmaChange = karmaValue.get(clazz).intValue();
			this.update(player.getDisplayName(), karmaChange, Side.SERVER);
		}
	}
}
