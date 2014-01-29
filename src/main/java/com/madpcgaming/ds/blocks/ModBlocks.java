package com.madpcgaming.ds.blocks;

import net.minecraft.block.Block;

import com.madpcgaming.ds.blocks.dark.ShadowPortal;
import com.madpcgaming.ds.blocks.dark.ShadowStone;
import com.madpcgaming.ds.blocks.light.BrightPortal;
import com.madpcgaming.ds.blocks.light.BrightStone;
import com.madpcgaming.ds.lib.BlockIds;
import com.madpcgaming.ds.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;


public class ModBlocks
{
	public static Block	ShadowPortal;
	public static Block ShadowStone;
	public static Block BrightPortal;
	public static Block BrightStone;

	public static void init()
	{
		/* Load Blocks Here */
		ShadowPortal = new ShadowPortal(BlockIds.SHADOW_PORTAL);
		ShadowStone = new ShadowStone(BlockIds.SHADOW_STONE);
		BrightPortal = new BrightPortal(BlockIds.BRIGHT_PORTAL);
		BrightStone = new BrightStone(BlockIds.BRIGHT_STONE);
		
		/* GameRegistry Here*/
		GameRegistry.registerBlock(ShadowPortal, Strings.SHADOW_PORTAL_NAME);
		GameRegistry.registerBlock(ShadowStone, Strings.SHADOW_STONE_NAME);
		GameRegistry.registerBlock(BrightPortal, Strings.BRIGHT_PORTAL_NAME);
		GameRegistry.registerBlock(BrightStone, Strings.BRIGHT_STONE_NAME);
		
		/* Tile Entities Here */
		
		/* Extra Code here (i.e. OreDict)*/
	}
}
