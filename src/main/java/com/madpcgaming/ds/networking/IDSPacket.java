package com.madpcgaming.ds.networking;

import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * Created by Rene on 20.02.14.
 */
public interface IDSPacket {

	public void readBytes(ByteBuf buf);
	public void writeBytes(ByteBuf buf);

	public void handle(Side s);
}
