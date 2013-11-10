package com.madpc.ds.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.madpc.ds.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
    
    public static Logger dsLogger = Logger.getLogger(Reference.MOD_ID);
    
    public static void init() {
        dsLogger.setParent(FMLLog.getLogger());
        
    }
    
    public static void log(Level logLevel, String message) {
        dsLogger.log(logLevel, message);
    }
    
    public static void info(String message) {
        dsLogger.log(Level.INFO, message);
    }
    
}
