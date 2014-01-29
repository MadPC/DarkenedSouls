package com.madpcgaming.ds.blocks.light;

import net.minecraft.block.material.Material;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.blocks.BlockDS;
import com.madpcgaming.ds.lib.Strings;

public class BrightStone extends BlockDS
{

	public BrightStone(int id)
	{
		super(id, Material.field_151576_e);
		this.func_149647_a(DarkenedSouls.tabsDS);
		this.func_149663_c(Strings.BRIGHT_STONE_NAME);
	}

}
