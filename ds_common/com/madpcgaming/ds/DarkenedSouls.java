	package com.madpcgaming.ds;

import net.minecraft.creativetab.CreativeTabs;

import com.madpcgaming.ds.blocks.ModBlocks;
import com.madpcgaming.ds.core.proxy.CommonProxy;
import com.madpcgaming.ds.handlers.ConfigurationHandler;
import com.madpcgaming.ds.handlers.LocalizationHandler;
import com.madpcgaming.ds.items.ModItems;
import com.madpcgaming.ds.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class DarkenedSouls
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy	proxy;
	@Instance("DS")
	public static DarkenedSouls	instance;
	public static CreativeTabs	tabsMT	= new CreativeTabDS(
												CreativeTabs.getNextID(),
												Reference.MOD_ID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		LocalizationHandler.loadLanguages();
		NetworkRegistry.instance().registerGuiHandler(this, DarkenedSouls.proxy);
		instance = this;
		ModBlocks.init();
		ModItems.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{

	}
	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
	}
}
