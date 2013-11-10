package com.madpc.ds.configuration;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.madpc.ds.lib.BlockIds;
import com.madpc.ds.lib.ItemIds;
import com.madpc.ds.lib.Reference;
import com.madpc.ds.lib.Strings;

import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {
    
    public static Configuration configuration;
    
    public static void init(File configFile){
        configuration = new Configuration(configFile);
        
        try{
            configuration.load();
            ConfigurationSettings.DISPLAY_VERSION_RESULT = configuration.get(CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT).getBoolean(ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT);
            ConfigurationSettings.LAST_DISCOVERED_VERSION = configuration.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_DEFAULT).getString();
            ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE = configuration.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_DEFAULT).getString();
            
            /*Block Configs*/
            BlockIds.OBSIDIAN_ORE = configuration.getBlock(Strings.OBSIDIANORE_NAME, BlockIds.OBSIDIAN_ORE_DEFAULT).getInt(BlockIds.OBSIDIAN_ORE_DEFAULT);
            BlockIds.SHADE_STONE = configuration.getBlock(Strings.SHADESTONE_NAME, BlockIds.SHADE_STONE_DEFAULT).getInt(BlockIds.SHADE_STONE_DEFAULT);
            
            /*Item Configs*/
            ItemIds.SMOKESCREEN = configuration.getItem(Strings.SMOKESCREEN_NAME, ItemIds.SMOKESCREEN_DEFAULT).getInt(ItemIds.SMOKESCREEN_DEFAULT);
            ItemIds.NVGOGGLES = configuration.getItem(Strings.NVGOGGLES_NAME, ItemIds.NVGOGGLES_DEFAULT).getInt(ItemIds.NVGOGGLES_DEFAULT);
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
    public static void set(String categoryName, String propertyName, String newValue) {

        configuration.load();
        if (configuration.getCategoryNames().contains(categoryName)) {
            if (configuration.getCategory(categoryName).containsKey(propertyName)) {
                configuration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        configuration.save();
    }

}
