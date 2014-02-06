package com.madpcgaming.ds.blocks.dark;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.blocks.BlockDS;
import com.madpcgaming.ds.lib.Strings;

public class ShadowPortal extends BlockDS
{

	public ShadowPortal(int id)
	{
		super(id, Material.portal);
		this.setCreativeTab(DarkenedSouls.tabsDS);
		this.setBlockName(Strings.SHADOW_PORTAL_NAME);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if((entity.ridingEntity == null) && (entity.riddenByEntity == null)  && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			Minecraft.getMinecraft();
			MinecraftServer mServer = MinecraftServer.getServer();
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 10;
			}
			else if(player.dimension != DarkenedSouls.darkDimensionId)
			{
				player.timeUntilPortal = 10;
				//player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new DSDarkTeleporter(mServer.worldServerForDimension(1)));
			}
		}
	}
}
