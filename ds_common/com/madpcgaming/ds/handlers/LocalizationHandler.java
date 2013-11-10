package com.madpcgaming.ds.handlers;

import com.madpcgaming.ds.helpers.LocalizationHelper;
import com.madpcgaming.ds.lib.Localizations;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler
{
	public static void loadLanguages()
	{
		for (String localizationFile : Localizations.localeFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile,
					LocalizationHelper.getLocaleFromFileName(localizationFile),
					LocalizationHelper.isXMLLanguageFile(localizationFile));
		}
	}
}
