package com.madpcgaming.ds.helpers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper
{
	public static boolean isXMLLanguageFile(String fileName)
	{
		return fileName.endsWith(".xml");
	}
	
	public static String getLocaleFromFileName(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
		
	}
	
	public static String getLocalizedString(String key)
	{
		return LanguageRegistry.instance().getStringLocalization(key);
	}

}
