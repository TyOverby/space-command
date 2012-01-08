package server.main;

import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;

import shared.math.Vector2f;

import server.networking.ConnectionPool;
import server.networking.Player;
import shared.main.Actor;
import shared.main.Game;
import shared.main.actors.Asteroid;
import shared.networking.UpdateAcMessage;
import shared.networking.UpdatePsMessage;
import shared.ships.JetPlane;
import shared.ships.PlayerShip;

public class ServerGame extends Game implements Runnable {

	private static ConnectionPool cp;

	private static final long pushGoTime = 1000;
	private static final long pushPsTime = 150;

	private static long lastPushGo;
	private static long lastPushPs;
	private static long lastUpdateTime;

	private static long time;

	@Override
	public void run(){
		time = System.currentTimeMillis();
		lastPushGo = time;
		lastPushPs = time;

		init();

		while(true){
			time = System.currentTimeMillis();
			if(lastUpdateTime==0){
				lastUpdateTime=time;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			update(time-lastUpdateTime);
			lastUpdateTime = time;

			if(time-lastPushGo>pushGoTime){
				cp.sayToAll(new UpdateAcMessage(actors));
				lastPushGo = time;
			}
			if(time-lastPushPs>pushPsTime){
				cp.sayToAll(new UpdatePsMessage(playerShips));
				lastPushPs = time;
			}
		}
	}

	public void init(){
		cp = new ConnectionPool(this);

		System.out.println("Server Starting");
		cp.start();
		System.out.println(Double.MAX_VALUE);
		load();
	}

	public void update(long l){				
		for(Actor actor:actors.values()){
			System.out.println(actor.getPosition());
			actor.update(l);
		}
		for(PlayerShip ps:playerShips.values()){
			ps.update(l);
		}
	}
	
	@Override
	public void draw(GameContainer gc, Graphics g, Camera c) {
		// Don't draw anything :D
	}

	private void load(){
		Asteroid asteroid = new Asteroid(new Vector2f(0,0),new Vector2f(10,10),50,50);
		actors.put(actors.size(),asteroid);
	}


	public Map<Integer,PlayerShip> getPlayerShips(){
		return playerShips;
	}
	public void addPlayerShip(String shipName, String password,Player player){

		PlayerShip newShip = new PlayerShip(shipName,password,new JetPlane(new Vector2f(50,50), new Vector2f(0,0),50,50));
		newShip.addPlayer(player.playerID);

		playerShips.put(playerShips.size(), newShip);
	}

	public static void main(String... args){
		ServerGame sg = new ServerGame();
		new Thread(sg).start();
	}


}
