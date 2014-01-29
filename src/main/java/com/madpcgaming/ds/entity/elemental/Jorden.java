package com.madpcgaming.ds.entity.elemental;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class Jorden extends EntityLiving implements IMob, IBossDisplayData
{

	public Jorden(World world)
	{
		super(world);
	}

}
