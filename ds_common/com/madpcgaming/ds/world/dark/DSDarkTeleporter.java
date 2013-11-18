package com.madpcgaming.ds.world.dark;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.madpcgaming.ds.blocks.ModBlocks;
import com.madpcgaming.ds.world.DSWorld;

import cpw.mods.fml.common.FMLLog;

public class DSDarkTeleporter extends Teleporter
{
	protected WorldServer myWorld;
	protected Random rand;

	public DSDarkTeleporter(WorldServer worldServer)
	{
		super(worldServer);
		this.myWorld = worldServer;
		if(this.rand == null)
		{
			this.rand = new Random();
		}
	}
	
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, double par7, float par9)
	{
		int c = 200;
		double d = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = MathHelper.floor_double(entity.posX);
		int i1 = MathHelper.floor_double(entity.posZ);
		for(int j1 = l - c; j1 <= l + c; j1++)
		{
			double d1 = j1 + 0.5D - entity.posX;
			for(int j2 = i1 - c; j2 < i1 + c; j2++)
			{
				double d3 = j2 + 0.5D - entity.posZ;
				for(int k2 = DSWorld.WORLDHEIGHT - 1; k2 >= 0; k2--)
				{
					if(isBlockPortal(this.myWorld, j1, k2, j2))
					{
						while(isBlockPortal(this.myWorld, j1, k2 - 1, j2))k2--;
						double d5 = k2 + 0.5D - entity.posY;
						double d7 = d1 * d1 + d5 * d5 + d3*d3;
						if((d < 0.0D) || (d7 < d))
						{
							d = d7;
							i = j1;
							j = k2;
							k = j2;
						}
					}
				}
			}
		}
		
		if (d >= 0.0D)
		{
			int k1 = i;
			int l1 = j;
			int i2 = k;
			double portalX = k1 + 0.5D;
			double portalY = l1 + 0.5D;
			double portalZ = i2 + 0.5D;
			if(isBlockPortal(this.myWorld, k1 - 1, l1, i2))
			{
				portalX -= 0.5D;
			}
			if(isBlockPortal(this.myWorld, k1 + 1, l1, i2))
			{
				portalX += 0.5D;
			}
			if(isBlockPortal(this.myWorld, k1, l1, i2 -1))
			{
				portalZ -= 0.5D;
			}
			if(isBlockPortal(this.myWorld, k1, l1, i2 + 1))
			{
				portalZ += 0.5D;
			}
			int xOffset = 0;
			int zOffset = 0;
			while ((xOffset == zOffset) && (xOffset == 0))
			{
				xOffset = this.rand.nextInt(3) - this.rand.nextInt(3);
				zOffset = this.rand.nextInt(3) - this.rand.nextInt(3);
			}
			entity.setLocationAndAngles(portalX + xOffset, portalY + 1.0D, portalZ + zOffset, entity.rotationYaw, 0.0F);
			entity.motionX = (entity.motionY = entity.motionZ = 0.0D);
			return true;
		}
		return false;
	}
	
	public boolean isBlockPortal(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z) == ModBlocks.ShadowPortal.blockID;
	}
	
	public boolean makePortal(Entity entity)
	{
		ChunkCoordinates spot = findPortalCoords(entity, true);
		if(spot != null)
		{
			FMLLog.info("[DarkenedSouls] Found the perfect portal spot", new Object[0]);
			makePortalAt(this.myWorld, spot.posX, spot.posY, spot.posZ);
			return true;
		}
		
		FMLLog.info("[DarkenedSouls] Couldn't find the perfect spot, trying for an ideal one", new Object[0]);
		spot = findPortalCoords(entity,false);
		if( spot != null)
		{
			FMLLog.info("[DarkenedSouls] found an ideal spot", new Object[0]);
			makePortalAt(this.myWorld, spot.posX, spot.posY, spot.posZ);
			return true;
		}
		
		FMLLog.info("[DarkenedSouls] could not find an ideal, closing eyes and hoping for the best", new Object[0]);
		
		double yFactor = this.myWorld.provider.dimensionId == 0 ? 2.0D : 0.5D;
		
		int entityX = MathHelper.floor_double(entity.posX);
		int entityY = MathHelper.floor_double(entity.posY * yFactor);
		int entityZ = MathHelper.floor_double(entity.posZ);
		
		makePortalAt(this.myWorld, entityX, entityY, entityZ);
		
		return false;
	}
	
	public ChunkCoordinates findPortalCoords(Entity entity, boolean ideal)
	{
		double yFactor = this.myWorld.provider.dimensionId == 0 ? 2.0D : 0.5D;
		
		int entityX = MathHelper.floor_double(entity.posX);
		int entityY = MathHelper.floor_double(entity.posY * yFactor);
		int entityZ = MathHelper.floor_double(entity.posZ);
		
		double spotWeight = -1.0D;
		
		ChunkCoordinates spot = null;
		byte range = 16;
		for(int rx = entityX - range; rx <= entityX + range; rx++)
		{
			double xWeight = rx + 0.5D - entity.posX;
			for(int rz = entityZ - range; rz <= entityZ + range; rz++)
			{
				double zWeight = rz + 0.5D - entity.posZ;
				for (int ry = 127; ry >= 0; ry--)
				{
					if(this.myWorld.isAirBlock(rx, ry, rz))
					{
						while ((ry > 0) && (this.myWorld.isAirBlock(rx, ry, rz))) ry--;
						if(ideal ? isIdealPortal(rx,rz,ry) : isOkayPortal(rx,rz,ry))
						{
							double yWeight = ry + 0.5D - entity.posY * yFactor;
							double rPosWeight = xWeight * xWeight + yWeight * yWeight + zWeight * zWeight;
							
							if((spotWeight < 0.0D) || (rPosWeight < spotWeight))
							{
								spotWeight = rPosWeight;
								spot = new ChunkCoordinates(rx,ry,rz);
							}
						}
					}
				}
			}
		}
		return spot;
	}
	
	public boolean isIdealPortal(int rx, int ry, int rz)
	{
		for(int potentialZ = 0; potentialZ < 4; potentialZ++)
		{
			for(int potentialX = 0; potentialX < 4; potentialX++)
			{
				for(int potentialY = 0; potentialY < 3; potentialY++)
				{
					int tx = rx + (potentialX -1);
					int ty = ry + potentialY;
					int tz = rz + (potentialZ - 1);
					if(((potentialY == -1) && (this.myWorld.getBlockMaterial(tx, ty, tz) != Material.grass)) || ((potentialY >= 0) && (!this.myWorld.getBlockMaterial(tx, ty, tz).isReplaceable())))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isOkayPortal(int rx, int ry, int rz)
	{
		for(int potentialZ = 0; potentialZ < 4; potentialZ++)
		{
			for(int potentialX = 0; potentialX < 4; potentialX++)
			{
				for(int potentialY = 0; potentialY < 3; potentialY++)
				{
					int tx = rx + (potentialX -1);
					int ty = ry + potentialY;
					int tz = rz + (potentialZ - 1);
					if(((potentialY == -1) && (!this.myWorld.getBlockMaterial(tx, ty, tz).isSolid())) || ((potentialY >= 0) && (!this.myWorld.getBlockMaterial(tx, ty, tz).isReplaceable())))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void makePortalAt(World world, int px, int py, int pz)
	{
		if(py <30)
		{
			py = 30;
		}
		world.getClass();
		if (py > 118)
		{
			world.getClass();
			py = 118;
		}
		py--;
		
		world.setBlock(px - 1, py + 0, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 0, py + 0, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 1, py + 0, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		
		world.setBlock(px - 1, py + 1, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 1, py + 1, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		
		world.setBlock(px - 1, py + 2, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 1, py + 2, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		
		world.setBlock(px - 1, py + 3, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 0, py + 3, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		world.setBlock(px + 1, py + 3, pz - 1, ModBlocks.ShadowStone.blockID, 0, 2);
		
		world.setBlock(px + 0, py + 1, pz - 1, ModBlocks.ShadowPortal.blockID, 0, 2);
		
		world.setBlock(px + 0, py + 3, pz - 1, ModBlocks.ShadowPortal.blockID, 0, 2);
		
		for(int dx = -1; dx <= 2; dx++)
		{
			for(int dz = -1; dz <= 2; dz++)
			{
				for(int dy = 1; dy <= 5; dy++)
				{
					world.setBlock(px + dx, py + dy, pz + dz, 0, 0, 2);
				}
			}
		}
		
	}

}
