package client.networking;

import java.nio.ByteBuffer;

import client.main.EntryPoint;

import shared.networking.Message;

public class ClientMessageHandler
{
	public static void handleMessage(byte[] message)
	{
		ByteBuffer bb = ByteBuffer.wrap(message);
		
		int type = bb.getInt();
		
		if(type == Message.Change.Navigation.HEADING)
		{
			EntryPoint.playerShip.setRotation(bb.getFloat());
		}
	}
}
