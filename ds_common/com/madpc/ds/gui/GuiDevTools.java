package com.madpc.ds.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumGameType;

import com.madpc.ds.DarkenedSouls;
import com.madpc.ds.core.helper.DimensionHelper;
import com.madpc.ds.entity.EntityShadowDragon;
import com.madpc.ds.entity.shadow.EntityShadow;
import com.madpc.ds.lib.Colors;
import com.madpc.ds.lib.Point3D;

public class GuiDevTools extends GuiScreen {
    
    public GuiButton gmButton;
    public GuiButton adventureButton;
    public GuiButton fortressButton;
    public static Point3D fortressLoc;
    
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width - 20, 0, 20, 20, Colors.RED + "X"));
        this.buttonList.add(new GuiButton(2, 0, 20, 50, 20, "Heal"));
        this.buttonList.add(new GuiButton(3, 0, 40, 98, 20, "Remove Effects"));
        this.buttonList.add(new GuiButton(4, 0, 60, 98, 20, "Invincibility"));
        this.buttonList.add(new GuiButton(5, 0, 80, 98, 20, "One-Hit"));
        this.buttonList.add(gmButton = new GuiButton(6, 0, 0, 20, 20, "?"));
        this.buttonList.add(new GuiButton(7, 20, 0, 20, 20, "A"));
        this.buttonList.add(fortressButton = new GuiButton(12, 108, 40, 98, 20, "Go to Fortress"));
        this.buttonList.add(new GuiButton(10, 108, 60, 98, 20, "Shadow Dimension"));
        this.buttonList.add(new GuiButton(11, 108, 80, 98, 20, "Overworld"));
        
        // Spawning
        this.buttonList.add(new GuiButton(8, 0, 120, 98, 20, "Shadow"));
        this.buttonList.add(new GuiButton(9, 108, 120, 98, 20, "Shadow Dragon"));
        
        String username = this.mc.thePlayer.username;
        EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
        if (player.capabilities.isCreativeMode) {
            gmButton.displayString = "S";
        } else {
            gmButton.displayString = "C";
        }
        
        fortressButton.enabled = player.dimension == DimensionHelper.dimensionShadowID; 
    }
    
    protected void actionPerformed(GuiButton button) {
        String username = this.mc.thePlayer.username;
        EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
        
        if (player == null) {
            DarkenedSouls.dsLog.info("Player username does not match entity username!");
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
            this.mc.sndManager.resumeAllSounds();
            return;
        }
        
        switch (button.id) {
            case 1: // Close
                this.mc.displayGuiScreen((GuiScreen) null);
                this.mc.setIngameFocus();
                this.mc.sndManager.resumeAllSounds();
                break;
            
            // -==[ PLAYER ]==- \\
            case 2:
                player.heal(player.getMaxHealth());
                break;
            case 3:
                player.curePotionEffects(new ItemStack(Item.bucketMilk));
                break;
            case 4:
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 100000, 5));
                break;
            case 5:
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 100000, 10));
                break;
            case 6:
                if (player.capabilities.isCreativeMode) {
                    player.setGameType(EnumGameType.SURVIVAL);
                    gmButton.displayString = "C";
                } else {
                    player.setGameType(EnumGameType.CREATIVE);
                    gmButton.displayString = "S";
                }
                break;
            case 7:
                player.setGameType(EnumGameType.ADVENTURE);
                break;
            case 10:
                player.setGameType(EnumGameType.SURVIVAL);
                player.travelToDimension(DimensionHelper.dimensionShadowID);
                /*
                 * MinecraftServer server = MinecraftServer.getServer();
                 * ChunkCoordinates chunkcoordinates =
                 * server.worldServerForDimension
                 * (DarkenedSouls.shadowDimensionID
                 * ).getEntrancePortalLocation(); if (chunkcoordinates != null)
                 * { player.playerNetServerHandler.setPlayerLocation((double)
                 * chunkcoordinates.posX, (double)chunkcoordinates.posY,
                 * (double)chunkcoordinates.posZ, 0.0F, 0.0F); }
                 */
                // server.getConfigurationManager().transferPlayerToDimension(player,
                // DarkenedSouls.shadowDimensionID);
                break;
            case 11:
                player.setGameType(EnumGameType.SURVIVAL);
                player.travelToDimension(0);
                break;
            case 12:
                if (GuiDevTools.fortressLoc != null) player.playerNetServerHandler.setPlayerLocation(fortressLoc.x, fortressLoc.y, fortressLoc.z, player.rotationYaw, player.rotationPitch);
                else player.addChatMessage("No new fortresses found");
                break;
            
            // -==[ SPAWNS ]==- \\
            case 8:
                EntityShadow shadow = new EntityShadow(player.worldObj);
                shadow.setPosition(player.posX, player.posY, player.posZ);
                player.worldObj.spawnEntityInWorld(shadow);
                break;
            case 9:
                EntityShadowDragon dragon = new EntityShadowDragon(player.worldObj);
                dragon.setPosition(player.posX, player.posY, player.posZ);
                player.worldObj.spawnEntityInWorld(dragon);
                break;
            default:
                break;
        }
    }
    
    public void drawScreen(int par1, int par2, float par3) {
        // this.drawDefaultBackground();
        GuiDevTools.drawRect(0, 0, this.width, this.height, 0x88000000);
        this.drawCenteredString(this.fontRenderer, "Dev Tools", this.width / 2, 5, 16777215);
        this.drawString(this.mc.fontRenderer, "Spawning:", 5, 105, 0xFFFFFF);
        super.drawScreen(par1, par2, par3);
    }
}
