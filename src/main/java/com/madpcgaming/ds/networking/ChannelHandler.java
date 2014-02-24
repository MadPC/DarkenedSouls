package com.madpcgaming.ds.networking;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFMLSidedHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.EnumMap;

/**
 * Created by Rene on 20.02.14.
 */
public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IDSPacket> {

	public static EnumMap<Side, FMLEmbeddedChannel> channels;

	public ChannelHandler()
	{
		addDiscriminator(0, DSKarmaPacket.class);
		channels = NetworkRegistry.INSTANCE.newChannel("DS-KARMA", this);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, IDSPacket msg, ByteBuf target) throws Exception {
		msg.writeBytes(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IDSPacket msg) {
		msg.readBytes(source);

		msg.handle(FMLCommonHandler.instance().getEffectiveSide());
	}

	public void sendToClients(IDSPacket pack)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(pack);
	}

	public void sendToClient(IDSPacket pack, EntityPlayerMP player)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channels.get(Side.SERVER).writeOutbound(pack);
	}

	public void sendToServer(IDSPacket pack)
	{
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(pack);
	}
}
