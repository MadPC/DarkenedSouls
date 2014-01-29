package com.madpcgaming.ds.entity.dark;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class GrimReaper extends EntityLiving implements IMob, IBossDisplayData
{

	public GrimReaper(World world)
	{
		super(world);
	}

}
