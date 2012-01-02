package server.networking;

import java.io.IOException;
import java.net.Socket;

import shared.networking.AbstractConnectionThread;
import shared.networking.ConnectMessage;
import shared.networking.Message;

public class Player extends AbstractConnectionThread{
	private final ConnectionPool connectionPool;
	
	public final int playerID;
	private String playerName;
	
	protected Player(Socket socket, ConnectionPool connectionPool) {
		super(socket);
		this.connectionPool = connectionPool;
		this.playerID = connectionPool.newPlayer();
	}

	@Override
	protected void handleMessage(Message msg) throws IOException,  InterruptedException {
		if(msg instanceof ConnectMessage){
			ConnectMessage message = (ConnectMessage) msg;
			
			System.out.println("Player: "+message.playerName+" requesting ship "+message.shipName+" with password "+message.password);
			
			connectionPool.requestShip(message.shipName,message.password,this);
		}
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
}
