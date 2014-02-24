package com.madpcgaming.ds.helpers;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.ArrayUtils;

import javax.lang.model.type.PrimitiveType;
import java.util.ArrayList;

public class NetworkingHelper {

	/**
	 * This implementation is NOT optimised because the size is unknown. <br />
	 * <u><b>!!WARNING!!!</b></u> the string MUST be null terminated <br />
	 * @see com.madpcgaming.ds.helpers.NetworkingHelper#readCharArray(ByteBuf, int) readCharArray(ByteBuf,int) for better performance
	 * @param buf the buffer to be read from
	 * @return a char[] containing the characters from the current position up to but not including the null terminator
	 */
	public static char[] readCharArray(ByteBuf buf)
	{
		ArrayList<Character> ret = new ArrayList<Character>();
		char b;
		while ((b = buf.readChar()) != 0)
		{
			ret.add(b);
		}
		return ArrayUtils.toPrimitive(ret.toArray(new Character[1]));
	}

	/**
	 * This implementation will read a certain number of characters from the given buffer and return them.
	 * @param buf the buffer to be read from
	 * @param length the length of the string to read
	 * @return
	 */
	public static char[] readCharArray(ByteBuf buf, int length)
	{
		try {
			char[] ret = new char[length];
			for (int x = 0; x < length; x++)
			{
				ret[x] = buf.readChar();
			}
			return ret;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("DSNetworkingHelper couldn't read a String of length " + length + " from buffer " + buf.toString());
			e.printStackTrace();
			return new char[] {'N', 'u', 'l', 'l'};
		}
	}

	/**
	 * Writes a char[] into the specified buffer <br />
	 * <u><b>!!WARNING!!</b></u> the message will NOT be null terminated
	 * @param buf the buffer to write to
	 * @param msg the message
	 */
	public static void writeCharArray(ByteBuf buf, char[] msg)
	{
		for (char c : msg)
		{
			buf.writeChar(c);
		}
	}

	/**
	 * @see com.madpcgaming.ds.helpers.NetworkingHelper#writeCharArray(io.netty.buffer.ByteBuf, char[])
	 * @param buf the buffer to write to
	 * @param s the message
	 */
	public static void writeString(ByteBuf buf, String s)
	{
		writeCharArray(buf, s.toCharArray());
	}
}
