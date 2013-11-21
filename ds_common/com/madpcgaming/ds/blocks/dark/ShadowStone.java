package com.madpcgaming.ds.blocks.dark;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.blocks.BlockDS;
import com.madpcgaming.ds.lib.Strings;

import net.minecraft.block.material.Material;

public class ShadowStone extends BlockDS
{

	public ShadowStone(int id)
	{
		super(id, Material.rock);
		this.setUnlocalizedName(Strings.SHADOW_STONE_NAME);
		this.setCreativeTab(DarkenedSouls.tabsDS);
	}

}
