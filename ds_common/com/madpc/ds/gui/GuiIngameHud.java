package com.madpc.ds.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.client.GuiIngameForge;

import com.madpc.ds.DarkenedSouls;
import com.madpc.ds.core.util.LogHelper;

public class GuiIngameHud extends GuiIngameForge {
    
    public final KeyBinding tools = new KeyBinding("ds_devtools", 46);
    
    public GuiIngameHud(Minecraft par1Minecraft) {
        super(par1Minecraft);
        DarkenedSouls.dsLog.info("Custom GUI added");
    }
    
    public void renderGameOverlay(float ticks, boolean hasScreenUp, int mouseX, int mouseY) {
        super.renderGameOverlay(ticks, hasScreenUp, mouseX, mouseY);
        
        if (DarkenedSouls.devMode && !this.mc.gameSettings.showDebugInfo) this.drawString(this.mc.fontRenderer, "Press C to open dev tools.", 0, 0, 0xFFFFFF);
    }
    
    public void updateTick() {
        super.updateTick();
        
        try {
            String username = this.mc.thePlayer.username;
            EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
            player.triggerAchievement(AchievementList.openInventory);
        } catch (Exception ex) {}
        
        if (DarkenedSouls.devMode && tools.isPressed()) this.mc.displayGuiScreen(new GuiDevTools());
    }
}
