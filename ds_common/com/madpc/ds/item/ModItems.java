package com.madpc.ds.item;

import com.madpc.ds.item.armor.ItemNVGoggles;
import com.madpc.ds.lib.ItemIds;
import com.madpc.ds.lib.Strings;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;

public class ModItems {
    
    public static Item smokeScreen;
    public static Item nvGoggles;
    
    public static void init(){
        smokeScreen = new SmokeScreen(ItemIds.SMOKESCREEN).setUnlocalizedName(Strings.SMOKESCREEN_NAME);
        nvGoggles = new ItemNVGoggles(ItemIds.NVGOGGLES, EnumArmorMaterial.CLOTH, 0, 0).setUnlocalizedName(Strings.NVGOGGLES_NAME);
    }

}
