package com.madpcgaming.ds.blocks.light;

import net.minecraft.block.material.Material;

import com.madpcgaming.ds.DarkenedSouls;
import com.madpcgaming.ds.blocks.BlockDS;
import com.madpcgaming.ds.lib.Strings;

public class BrightPortal extends BlockDS
{

	public BrightPortal(int id)
	{
		super(id, Material.portal);
		this.setCreativeTab(DarkenedSouls.tabsDS);
		this.setBlockName(Strings.BRIGHT_PORTAL_NAME);
	}

}
