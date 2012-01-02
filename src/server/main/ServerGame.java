package server.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.geom.Vector2f;

import server.networking.ConnectionPool;
import server.networking.Player;
import shared.main.GameObject;
import shared.main.actors.Asteroid;
import shared.ships.JetPlane;
import shared.ships.PlayerShip;

public class ServerGame extends Thread {
	
	private static ConnectionPool cp;
	
	private static final Map<Integer,GameObject> gameObjects = new HashMap<Integer,GameObject>();
	private static final Map<Integer,PlayerShip> playerShips = new HashMap<Integer,PlayerShip>();

	@Override
	public void run(){
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true){
			update();
			try {Thread.sleep(5);
			} catch (InterruptedException e) {}
			
			for(Object o:gameObjects.values()){
				System.out.println(o.getClass().getSimpleName());
			}
			for(Object o:playerShips.values()){
				System.out.println(o.getClass().getSimpleName());
			}
		}
	}
	
	private static void init() throws IOException{
		cp = new ConnectionPool();
		
		System.out.println("Server Starting");
		cp.start();
		
		gameObjects.put(gameObjects.size(), new Asteroid());
	}
	
	private static void update(){
		for(GameObject go:gameObjects.values()){
			go.update();
		}
	}
	
	
	public static Map<Integer,PlayerShip> getPlayerShips(){
		return playerShips;
	}
	public static void addPlayerShip(String shipName, String password,Player player){
		
		PlayerShip newShip = new PlayerShip(shipName,password,new JetPlane(new Vector2f(0,0),0));
		newShip.addPlayer(player.playerID);
		
		playerShips.put(playerShips.size(), newShip);
	}
	
	public static void main(String... args){
		ServerGame sg = new ServerGame();
		sg.start();
	}
}
