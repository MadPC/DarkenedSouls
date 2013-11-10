package com.madpcgaming.ds.handlers;

import java.io.File;
import java.util.logging.Level;

import com.madpcgaming.ds.lib.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
	public static void init (File configFile)
	{
		configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			/* Block ID Configuration */
			
			/* Item ID Configuration */
			
			/* GUI ID Configuration */
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Whoops!"+ Reference.MOD_NAME + " goofed loading the configuraiton file");
		} finally {
			configuration.save();
		}
	}
	
	public static void set(String catergoryName, String propertyName, String newValue)
	{
		configuration.load();
		if (configuration.getCategoryNames().contains(catergoryName)){
			if (configuration.getCategory(propertyName).containsKey(propertyName)){
				configuration.getCategory(catergoryName).get(propertyName).set(newValue);
			}
		}
		configuration.save();
	}
}
