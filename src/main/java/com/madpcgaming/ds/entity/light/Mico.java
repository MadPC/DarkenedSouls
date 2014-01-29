package com.madpcgaming.ds.entity.light;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class Mico extends EntityLiving implements IMob, IBossDisplayData
{

	public Mico(World world)
	{
		super(world);
	}

}
