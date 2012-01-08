package client.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;
import client.networking.ClientPort;

import shared.main.Actor;
import shared.main.Game;
import shared.math.Dimension;
import shared.math.Vector2f;
import shared.networking.ConnectMessage;
import shared.ships.PlayerShip;

public class ClientGame extends Game{
	private static Map<Integer,Actor> tmpAC = new HashMap<Integer,Actor>();
	private static Map<Integer,PlayerShip> tmpPS = new HashMap<Integer,PlayerShip>();
	

	public static Camera playerCamera;
	
	public void init(Dimension screenDims){
		playerCamera = new Camera(gc, new Vector2f(0,0));
		
		ClientPort clientPort = null;
		try{
		clientPort = new ClientPort();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("CLIENT");
		
		ConnectMessage requestShip = new ConnectMessage("shipname","shippass","seht1010");
		clientPort.start();
		clientPort.say(requestShip);
	}
	
	public void update(long delta){
		System.out.println(delta);
		
		// Update the old items
		if(tmpAC!=null){
			actors=new HashMap<Integer, Actor>(tmpAC);
			tmpAC = null;
		}
		if(tmpPS!=null){
			playerShips=new HashMap<Integer, PlayerShip>(tmpPS);
			tmpPS = null;
		}
		
		for(Actor ac:actors.values()){
			ac.update(delta);
		}
		for(PlayerShip ps:playerShips.values()){
			ps.update(delta);
		}
	}
	
	public void draw(GameContainer gc, Graphics g, Camera c){
		for(Actor ac:actors.values()){
			Vector2f drawPos = c.globalToScreen(ac.getPosition());
			
			g.setColor(Color.cyan);
			g.drawRect(drawPos.getX(), drawPos.getY(),(float) (50/c.getZoom()),(float) (50/c.getZoom()));
		}
		for(PlayerShip ps:playerShips.values()){
			Vector2f drawPos = c.globalToScreen(ps.getPosition());
			
			g.setColor(Color.red);
			g.drawRect(drawPos.getX(), drawPos.getY(),(float) (50/c.getZoom()),(float) (50/c.getZoom()));
		}
	}
	
	public static void updateGameObjects(Map<Integer,Actor> go){
		tmpAC = go;
	}
	public static void updatePlayerShips(Map<Integer,PlayerShip> ps){
		tmpPS = ps;
	}
}
