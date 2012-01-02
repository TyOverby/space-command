package client.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.networking.AbstractConnectionThread;
import shared.networking.HelloMessage;
import shared.networking.Message;


public class ClientPort extends AbstractConnectionThread{

	protected ClientPort() throws UnknownHostException, IOException {
		super(new Socket("localhost",AbstractConnectionThread.PORT));
	}

	@Override
	protected void handleMessage(Message msg) throws IOException,InterruptedException {
		if(msg instanceof HelloMessage){
			System.out.println("Accepted");
		}
		System.out.println("MESSAGE RECIEVES");
	}
}
