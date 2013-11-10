package com.madpc.ds.core.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.madpc.ds.configuration.ConfigurationHandler;
import com.madpc.ds.configuration.ConfigurationSettings;
import com.madpc.ds.lib.Colors;
import com.madpc.ds.lib.Reference;
import com.madpc.ds.lib.Strings;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class VersionHelper implements Runnable {

    public static boolean ENABLE_VERSION_CHECK = false;

    private static VersionHelper instance = new VersionHelper();

    // Version Check Site visible to public
    private static final String REMOTE_VERSION_XML_FILE = "https://github.com/MadPC/Darkened-Souls/blob/master/version.xml";

    public static Properties remoteVersionProperties = new Properties();

    // All returnable values
    public static final byte UNINITIALIZED = 0;
    public static final byte CURRENT = 1;
    public static final byte OUTDATED = 2;
    public static final byte ERROR = 3;
    public static final byte FINAL_ERROR = 4;
    public static final byte MC_VERSION_NOT_FOUND = 5;

    private static byte result = UNINITIALIZED;
    public static String remoteVersion = null;
    public static String remoteUpdateLocation = null;

    public static void checkVersion() {
        InputStream remoteVersionRepoStream = null;
        result = UNINITIALIZED;
        try {
            URL remoteVersionURL = new URL(REMOTE_VERSION_XML_FILE);
            remoteVersionRepoStream = remoteVersionURL.openStream();
            remoteVersionProperties.loadFromXML(remoteVersionRepoStream);

            String remoteVersionProperty = remoteVersionProperties
                    .getProperty(Loader.instance().getMCVersionString());

            if (remoteVersionProperty != null) {
                String[] remoteVersionTokens = remoteVersionProperty
                        .split("\\|");

                if (remoteVersionTokens.length >= 2) {
                    remoteVersion = remoteVersionTokens[0];
                    remoteUpdateLocation = remoteVersionTokens[1];
                } else {
                    result = ERROR;
                }

                if (remoteVersion != null) {
                    if (!ConfigurationSettings.LAST_DISCOVERED_VERSION
                            .equalsIgnoreCase(remoteVersion)) {
                        ConfigurationHandler
                                .set(Configuration.CATEGORY_GENERAL,
                                        ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME,
                                        remoteVersion);
                    }

                    if (remoteVersion.equalsIgnoreCase(getVersionForCheck())) {
                        result = CURRENT;
                    } else {
                        result = OUTDATED;
                    }
                }
            } else {
                result = MC_VERSION_NOT_FOUND;
            }
        } catch (Exception e) {

        } finally {
            if (result == UNINITIALIZED) {
                result = ERROR;
            }
            try {
                if (remoteVersionRepoStream != null) {
                    remoteVersionRepoStream.close();
                }
            } catch (Exception e) {

            }
        }
    }

    private static String getVersionForCheck() {
        String[] versionTokens = Reference.VERSION.split(" ");

        if (versionTokens.length >= 1) {
            return versionTokens[0];
        } else
            return Reference.VERSION;
    }

    public static void logResult() {
        if (result == CURRENT || result == OUTDATED) {
            LogHelper.log(Level.INFO, getResultMessage());
        }
        else {
            LogHelper.log(Level.WARNING, getResultMessage());
        }
    }

    public static String getResultMessage() {

        if (result == UNINITIALIZED)
            return LanguageRegistry.instance().getStringLocalization(Strings.UNINITIALIZED_MESSAGE);
        else if (result == CURRENT) {
            String returnString = LanguageRegistry.instance().getStringLocalization(Strings.CURRENT_MESSAGE);
            returnString = returnString.replace("@REMOTE_MOD_VERSION@", remoteVersion);
            returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
            return returnString;
        }
        else if (result == OUTDATED && remoteVersion != null && remoteUpdateLocation != null) {
            String returnString = LanguageRegistry.instance().getStringLocalization(Strings.OUTDATED_MESSAGE);
            returnString = returnString.replace("@MOD_NAME@", Reference.MOD_NAME);
            returnString = returnString.replace("@REMOTE_MOD_VERSION@", remoteVersion);
            returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
            returnString = returnString.replace("@MOD_UPDATE_LOCATION@", remoteUpdateLocation);
            return returnString;
        }
        else if (result == OUTDATED && remoteVersion != null && remoteUpdateLocation != null) {
            String returnString = LanguageRegistry.instance().getStringLocalization(Strings.OUTDATED_MESSAGE);
            returnString = returnString.replace("@MOD_NAME@", Reference.MOD_NAME);
            returnString = returnString.replace("@REMOTE_MOD_VERSION@", remoteVersion);
            returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
            returnString = returnString.replace("@MOD_UPDATE_LOCATION@", remoteUpdateLocation);
            return returnString;
        }
        else if (result == ERROR)
            return LanguageRegistry.instance().getStringLocalization(Strings.GENERAL_ERROR_MESSAGE);
        else if (result == FINAL_ERROR)
            return LanguageRegistry.instance().getStringLocalization(Strings.FINAL_ERROR_MESSAGE);
        else if (result == MC_VERSION_NOT_FOUND) {
            String returnString = LanguageRegistry.instance().getStringLocalization(Strings.MC_VERSION_NOT_FOUND);
            returnString = returnString.replace("@MOD_NAME@", Reference.MOD_NAME);
            returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
            return returnString;
        }
        else {
            result = ERROR;
            return LanguageRegistry.instance().getStringLocalization(Strings.GENERAL_ERROR_MESSAGE);
        }
    }

    public static String getResultMessageForClient() {

        String returnString = LanguageRegistry.instance().getStringLocalization(Strings.OUTDATED_MESSAGE);
        returnString = returnString.replace("@MOD_NAME@", Colors.YELLOW+ Reference.MOD_NAME + Colors.WHITE);
        returnString = returnString.replace("@REMOTE_MOD_VERSION@", Colors.YELLOW + VersionHelper.remoteVersion + Colors.WHITE);
        returnString = returnString.replace("@MINECRAFT_VERSION@", Colors.YELLOW + Loader.instance().getMCVersionString() + Colors.WHITE);
        returnString = returnString.replace("@MOD_UPDATE_LOCATION@", Colors.YELLOW + VersionHelper.remoteUpdateLocation + Colors.WHITE);
        return returnString;
    }

    public static byte getResult() {

        return result;
    }

    @Override
    public void run() {

        int count = 0;

        LogHelper.log(Level.INFO, LanguageRegistry.instance().getStringLocalization(Strings.VERSION_CHECK_INIT_LOG_MESSAGE) + " " + REMOTE_VERSION_XML_FILE);

        try {
            while (count < Reference.VERSION_CHECK_ATTEMPTS - 1 && (result == UNINITIALIZED || result == ERROR)) {

                checkVersion();
                count++;
                logResult();

                if (result == UNINITIALIZED || result == ERROR) {
                    Thread.sleep(10000);
                }
            }

            if (result == ERROR) {
                result = FINAL_ERROR;
                logResult();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void execute() {

        new Thread(instance).start();
    }

}
