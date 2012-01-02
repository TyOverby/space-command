package server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import server.main.ServerGame;
import shared.ships.PlayerShip;
import shared.networking.AbstractConnectionThread;
import shared.networking.HelloMessage;


public class ConnectionPool extends Thread{
	
	private boolean isRunning = false;
	
	private List<Player> players = new CopyOnWriteArrayList<Player>();
	private ServerSocket serverSocket;
	
		
	private int playerCount = 0;
	
	public ConnectionPool() throws IOException{
		this.isRunning = true;
		serverSocket = new ServerSocket(AbstractConnectionThread.PORT);
	}
	
	public void run(){
		while(this.isRunning){
			try {
				Player newPlayer = new Player(serverSocket.accept(), this);
				newPlayer.start();
				players.add(newPlayer);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill(){
		this.isRunning = false;
		if(serverSocket != null){
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(Player sch:players){
			sch.close();
		}
	}
	
	public int getPlayerLength(){
		return players.size();
	}
	
	public int newPlayer(){
		playerCount ++;
		return playerCount;
	}
	
	public Collection<PlayerShip> getPlayerShips(){
		return ServerGame.getPlayerShips().values();
	}

	public void requestShip(String shipName, String password,Player player) {
		for(PlayerShip ps:getPlayerShips()){
			if(ps.getName().equals(shipName)){
				if(ps.getPass().equals(password)){
					ps.addPlayer(player.playerID);
					player.say(new HelloMessage());
				}
				return;
			}
		}
		
		ServerGame.addPlayerShip(shipName,password,player);
	}
}
