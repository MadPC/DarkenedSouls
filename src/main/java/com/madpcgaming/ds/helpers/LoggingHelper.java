package com.madpcgaming.ds.helpers;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.FMLRelaunchLog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;


/**
 * Created by Rene on 24.02.14.
 */
public class LoggingHelper {

	public static final Logger logger = FMLLog.getLogger();

	public static void log(Level l, String msg)
	{
		logger.log(l, msg);
	}

	public static void log(Level l, String msg, Object ... args)
	{
		LoggingHelper.log(l, String.format(msg, args));
	}

	public static void info(String msg)
	{
		logger.info(msg);
	}

	public static void info(String msg, Object ... args)
	{
		logger.info(String.format(msg, args));
	}

	public static void warn(String msg, Object ... args)
	{
		logger.warn(String.format(msg, args));
	}

	public static void fatal(String msg, Object ... args) {
		logger.fatal(String.format(msg, args));
	}

	//Auto-Delegates start here
	public static void debug(String msg) {
		logger.debug(msg);
	}

	public static void fatal(String msg) {
		logger.fatal(msg);
	}

	public static void warn(String msg) {
		logger.warn(msg);
	}
}
