package com.madpcgaming.ds.core;

import com.madpcgaming.ds.data.handles.KarmaHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHandler {

	@SubscribeEvent
	public void onEntityLivingDeath(LivingDeathEvent event)
	{
		if (!event.entity.worldObj.isRemote)
		{
			EntityLivingBase entity = event.entityLiving;
			Entity e = event.source.getEntity();
			if (e instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) e;
				KarmaHandler.instance.handle(entity, player);
			}
		}
	}
}
