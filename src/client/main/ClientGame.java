package client.main;

import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;
import client.main.drawing.HelmVS;
import client.main.drawing.ViewScreen;
import client.networking.ClientPort;

import shared.main.Game;
import shared.main.entity.Entity;
import shared.main.entity.Hollywood;
import shared.math.Vector2f;
import shared.networking.ConnectMessage;

public class ClientGame extends Game{
	private static Hollywood<Entity> tmpAC = new Hollywood<Entity>();
	
	private ViewScreen viewScreen;

	public static Camera playerCamera;
	
	/**
	 * 
	 */
	public void init(Vector2f screenDims){
		playerCamera = new Camera(screenDims, new Vector2f(0,0));
		
		ClientPort clientPort = null;
		try{
			clientPort = new ClientPort();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("CLIENT");
		
		viewScreen = new HelmVS(screenDims);
		
		ConnectMessage requestShip = new ConnectMessage("shipname","shippass","seht1010");
		clientPort.start();
		clientPort.say(requestShip);
	}

	public void update(long delta){
		viewScreen.update(delta);
		
		// Update the old items
		if(tmpAC!=null){
			actors=new Hollywood<Entity>(tmpAC);
			tmpAC = null;
		}
		
		for(Entity ac:actors){
			ac.update(delta);
		}
	}
	
	public void draw(Graphics g){
		viewScreen.setGraphics(g);
		viewScreen.drawPre();
		
		// TODO: viewScreen.drawEnv(); 
		
		for(Entity ac:actors){
			viewScreen.drawActor(ac);
		}
		
		viewScreen.drawFinal();
		viewScreen.drawGui();
	}
	
	public static void updateGameObjects(Hollywood<Entity> go){
		tmpAC = go;
	}
}
