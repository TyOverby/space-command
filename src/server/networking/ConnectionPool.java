package server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import server.main.ServerGame;
import shared.main.entity.Entity;
import shared.networking.AbstractConnectionThread;
import shared.networking.ConnectionAcceptedMessage;
import shared.networking.Message;


public class ConnectionPool extends Thread{

	private boolean isRunning = false;

	private List<PlayerShip> playerShips = new ArrayList<PlayerShip>(5);
	private List<Player> players = new CopyOnWriteArrayList<Player>();
	private ServerSocket serverSocket;

	private final ServerGame serverGame;

	private int playerCount = 0;

	public ConnectionPool(ServerGame serverGame){
		this.serverGame = serverGame;
		this.isRunning = true;
		try {
			serverSocket = new ServerSocket(AbstractConnectionThread.PORT);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
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
		playerCount++;
		return playerCount;
	}

	public void removePlayer(Player player){
		players.remove(player);
	}

	public void sayToAll(Message message){
		for(Player player:players){
			player.say(message);
		}
	}
	
	public void addPlayerToShip(String shipName, String password,Player player){
		PlayerShip newShip = new PlayerShip(shipName,password,new Entity(shipName));
		newShip.addPlayer(player.playerID);
		
		playerShips.add(newShip);
		serverGame.addShip(newShip.getShip());
	}

	public void requestShip(String shipName, String password,Player player) {
		for(PlayerShip ps:playerShips){
			if(ps.getName().equals(shipName)){
				if(ps.testPassword(password)){
					ps.addPlayer(player.playerID);
					player.say(new ConnectionAcceptedMessage());
					System.out.println("Player added to ship: "+shipName);
				}
				else{
					System.out.println("Wrong password for ship: "+shipName);
				}
				return;
			}
		}

		addPlayerToShip(shipName,password,player);
		player.say(new ConnectionAcceptedMessage());
		System.out.println("Player created ship: "+shipName);
	}
}
