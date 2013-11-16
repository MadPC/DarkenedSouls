package com.madpcgaming.ds.entity.elemental;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class Vann extends EntityLiving implements IMob, IBossDisplayData
{

	public Vann(World world)
	{
		super(world);
	}

}
