package client.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.main.ClientGame;
import client.main.Main;

import shared.networking.AbstractConnectionThread;
import shared.networking.ConnectionAcceptedMessage;
import shared.networking.ConnectionDeniedMessage;
import shared.networking.Message;
import shared.networking.SyncMessage;


public class ClientPort extends AbstractConnectionThread{

	private final ClientGame clientGame;
	
	public ClientPort(ClientGame cg) throws UnknownHostException, IOException {
		super(new Socket("localhost",AbstractConnectionThread.PORT));
		this.clientGame = cg;
	}

	@Override
	protected void handleMessage(Message msg) throws IOException,InterruptedException {
		
		if(msg instanceof SyncMessage){
			Main.connected = true;
			ClientGame.updateGameObjects(((SyncMessage) msg).actors);
			//System.out.println("updating game objects");
		}else if(msg instanceof ConnectionAcceptedMessage){
			clientGame.setPlayerShipId(((ConnectionAcceptedMessage)msg).shipId);
			System.err.println("Accepted");
			Main.connected = true;
		}else if(msg instanceof ConnectionDeniedMessage){
			System.out.println("Connection denied");
			Main.connected = false;  // This shouldn't be necessary, but just to make sure :)
		}
		else{
			System.out.println("unknown message of type: "+msg.getClass().getSimpleName());
		}
	}
	
	public void say(Message message){
		try{
			super.say(message);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
