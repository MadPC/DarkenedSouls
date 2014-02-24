package com.madpcgaming.ds.networking;

import com.madpcgaming.ds.data.handles.KarmaHandler;
import com.madpcgaming.ds.helpers.NetworkingHelper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;

/**
 * Created by Rene on 20.02.14.
 */
public class DSKarmaPacket implements IDSPacket {

	private int karma;
	private char[] name;

	public DSKarmaPacket() {}

	public DSKarmaPacket(int karma, String name)
	{
		this.karma = karma;
		this.name = name.toCharArray();
	}

	@Override
	public void readBytes(ByteBuf buf) {
		this.karma = buf.readInt();
		this.name = NetworkingHelper.readCharArray(buf);
	}

	@Override
	public void writeBytes(ByteBuf buf) {
		buf.writeInt(this.karma);
		NetworkingHelper.writeCharArray(buf, this.name);
		buf.writeChar(0);
	}

	@Override
	public void handle(Side s) {
		if (s.isClient())
		{
			KarmaHandler.instance.update(String.valueOf(this.name), this.karma);
		}
		else
		{
			KarmaHandler.instance.update(String.valueOf(this.name), this.karma);
		}
	}
}
