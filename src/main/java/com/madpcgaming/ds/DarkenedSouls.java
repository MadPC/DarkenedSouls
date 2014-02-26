package com.madpcgaming.ds;

import com.madpcgaming.ds.data.handles.KarmaHandler;
import com.madpcgaming.ds.networking.ChannelHandler;
import cpw.mods.fml.common.event.*;
import net.minecraft.creativetab.CreativeTabs;

import com.madpcgaming.ds.blocks.ModBlocks;
import com.madpcgaming.ds.core.proxy.CommonProxy;
import com.madpcgaming.ds.items.ModItems;
import com.madpcgaming.ds.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class DarkenedSouls
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy	proxy;
	@Instance("DS")
	public static final int darkDimensionId = 8;
	public static final int lightDimensionId = 9;
	public static DarkenedSouls	instance;
	public static CreativeTabs	tabsDS	= new CreativeTabDS(
												CreativeTabs.getNextID(),
												Reference.MOD_ID);

	public static ChannelHandler handler = new ChannelHandler();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		NetworkRegistry.INSTANCE.registerGuiHandler(this, DarkenedSouls.proxy);

		MinecraftForge.EVENT_BUS.register(new com.madpcgaming.ds.core.EventHandler());

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

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		KarmaHandler.instance.loadKarma(event.getServer().worldServerForDimension(0));
	}

	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{

	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		KarmaHandler.instance.saveKarma(null);
	}
}
