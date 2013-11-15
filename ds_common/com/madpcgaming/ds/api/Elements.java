package com.madpcgaming.ds.api;

import java.util.LinkedHashMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.text.WordUtils;

public class Elements
{
	String											tag;
	Elements[]										components;
	int												color;
	private String									chatcolor;
	ResourceLocation								image;
	int												blend;

	public static LinkedHashMap<String, Elements>	elements	= new LinkedHashMap();

	public static final Elements AIR = new Elements("Air",16777086, "e", 1);
	public static final Elements EARTH = new Elements("Earth",5685248, "2", 1);
	public static final Elements FIRE = new Elements("Fire",16734721, "c", 1);
	public static final Elements WATER = new Elements("Water",3986684, "3", 1);
	public static final Elements LIGHT = new Elements("Light", 14013676, "7", 1);
	public static final Elements SHADOW = new Elements("Shadow", 4210752, "8", 1);

	public Elements(String tag, int color, Elements[] components,
			ResourceLocation image, int blend)
	{
		if (elements.containsKey(tag))
			throw new IllegalArgumentException(tag + " already registred!");
		this.tag = tag;
		this.components = components;
		this.color = color;
		this.image = image;
		this.blend = blend;
		elements.put(tag, this);
	}

	public Elements(String tag, int color, Elements[] components)
	{
		this(tag, color, components, new ResourceLocation("DarkenedSouls",
				"textures/elements/" + tag.toLowerCase() + ".png"), 1);
	}

	public Elements(String tag, int color, Elements[] components, int blend)
	{
		this(tag, color, components, new ResourceLocation("DarkenedSouls",
				"textures/elements/" + tag.toLowerCase() + ".png"), blend);
	}

	public Elements(String tag, int color, String chatcolor, int blend)
	{
		this(tag, color, (Elements[]) null, blend);
		setChatColor(chatcolor);
	}

	public int getColor()
	{
		return this.color;
	}

	public String getName()
	{
		return WordUtils.capitalizeFully(this.tag);
	}

	public String getLocalizedDescription()
	{
		return StatCollector.translateToLocal("ds.elements" + this.tag);
	}
	
	public String getTag()
	{
		return this.tag;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public Elements[] getComponents()
	{
		return this.components;
	}
	
	public void setComponents(Elements[] components)
	{
		this.components = components;
	}
	
	public ResourceLocation getImage()
	{
		return this.image;
	}
	
	public static Elements getElement(String tag)
	{
		return(Elements)elements.get(tag);
	}
	
	public int getBlend()
	{
		return this.blend;
	}
	
	public void setBlend(int blend)
	{
		this.blend = blend;
	}
	
	public String getChatColor()
	{
		return this.chatcolor;
	}
	
	public void setChatColor(String chatcolor)
	{
		this.chatcolor = chatcolor;
	}
}
