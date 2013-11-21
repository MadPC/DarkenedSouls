package com.madpcgaming.ds.blocks.light;

import net.minecraft.block.material.Material;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.blocks.BlockDS;
import com.madpcgaming.ds.lib.Strings;

public class BrightStone extends BlockDS
{

	public BrightStone(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(DarkenedSouls.tabsDS);
		this.setUnlocalizedName(Strings.BRIGHT_STONE_NAME);
	}

}
