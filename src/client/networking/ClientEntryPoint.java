package client.networking;

import java.io.IOException;
import java.net.UnknownHostException;

import shared.networking.ConnectMessage;

public class ClientEntryPoint {
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientPort clientPort = new ClientPort();

		System.out.println("CLIENT");
		
		ConnectMessage requestShip = new ConnectMessage("shipname","shippass","seht1010");
		clientPort.say(requestShip);
		clientPort.start();
	}
}
