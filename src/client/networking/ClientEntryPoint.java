package client.networking;

import java.io.IOException;
import java.net.UnknownHostException;

import shared.networking.ConnectMessage;

public class ClientEntryPoint {
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientPort clientPort = new ClientPort(null);

		System.out.println("CLIENT");
		
		ConnectMessage requestShip = new ConnectMessage("seht1010","shippass","shipName");
		clientPort.say(requestShip);
		clientPort.start();
	}
}
