package networking;

import java.io.IOException;

import client.networking.ClientPort;

import server.networking.ConnectionPool;
import shared.networking.ConnectMessage;

public class NetTest {
	public static void main(String... args) throws IOException, NoSuchFieldException, SecurityException{
		// Make a server
		ConnectionPool cp = new ConnectionPool();
		cp.start();
		System.out.println("connection pool started");

		ClientPort clientPort = new ClientPort();
		clientPort.start();
		System.out.println("client started");
		System.out.println("Sending message");
		ConnectMessage requestShip = new ConnectMessage("shipname","shippass","seht1010");
		clientPort.say(requestShip);
	}
}
