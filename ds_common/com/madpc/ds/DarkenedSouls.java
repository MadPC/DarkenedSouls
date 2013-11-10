package com.madpc.ds;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;

import com.madpc.ds.block.ModBlocks;
import com.madpc.ds.configuration.ConfigurationHandler;
import com.madpc.ds.core.handler.LocalizationHandler;
import com.madpc.ds.core.helper.DimensionHelper;
import com.madpc.ds.core.helper.EntityHelper;
import com.madpc.ds.core.util.LogHelper;
import com.madpc.ds.core.util.VersionHelper;
import com.madpc.ds.gui.CreativeTabDS;
import com.madpc.ds.gui.GuiIngameHud;
import com.madpc.ds.item.ModItems;
import com.madpc.ds.lib.Reference;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DarkenedSouls {
    
    @Instance("DS")
    public static DarkenedSouls instance;
    public static Logger dsLog = Logger.getLogger("DarkenedSoul");
    public static CreativeTabs tabsDS = new CreativeTabDS(CreativeTabs.getNextID(), Reference.MOD_ID);
    
    public static boolean devMode;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Dev Tools
        try {
            Class.forName("com.madpc.ds.dev.EnableDevTools", false, ClassLoader.getSystemClassLoader());
            DarkenedSouls.enableDevTools();
        }
        catch (ClassNotFoundException ex) {}
        
        LogHelper.init();
        
        LocalizationHandler.loadLanguages();
        
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));
        
        VersionHelper.execute();
        
        ModBlocks.init();
        
        ModItems.init();
        
        dsLog.setParent(FMLLog.getLogger());
        dsLog.info("Loading/Creating Config");
        // TODO: Load config file here
        if (VersionHelper.ENABLE_VERSION_CHECK) {
            VersionHelper.checkVersion();
        }
        
        DimensionHelper.init();
    }
    
    @Init
    public void init(FMLInitializationEvent event) {
        EntityHelper.init(this);
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        
    }
    
    @ServerStarted
    public void serverStarted(FMLServerStartedEvent event) {
        FMLClientHandler.instance().getClient().ingameGUI = new GuiIngameHud(FMLClientHandler.instance().getClient());
    }
    
    public static void enableDevTools() {
        DarkenedSouls.devMode = true;
        LogHelper.info("~~~~~~~~~~~~~~~~~");
        LogHelper.info("DEV TOOLS ENABLED");
        LogHelper.info("~~~~~~~~~~~~~~~~~");
    }
}
