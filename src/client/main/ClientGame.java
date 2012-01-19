package client.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import client.main.view.ViewScreen;
import client.main.view.helm.HelmVS;
import client.networking.ClientPort;

import shared.main.entity.Entity;
import shared.main.entity.Hollywood;
import shared.math.Vector2f;
import shared.networking.ConnectMessage;
import shared.networking.UpdateMessage;

public class ClientGame{
	private static Hollywood<Entity> tmpAC = new Hollywood<Entity>();
	protected  Hollywood<Entity> actors = new Hollywood<Entity>();

	private ViewScreen viewScreen;
	private ClientPort clientPort = null;
	private int shipId = Integer.MAX_VALUE;


	/**
	 * 
	 */
	public void init(Vector2f screenDims,GameContainer gc){

		try{
			clientPort = new ClientPort(this);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("CLIENT");

		viewScreen = new HelmVS(screenDims,gc,this);
		viewScreen.setupInput();

		ConnectMessage requestShip = new ConnectMessage("shipname","shippass","seht1010");
		clientPort.start();
		clientPort.say(requestShip);
	}

	public void forWardInput(UpdateMessage um) {
		clientPort.say(um);
		getPlayerShip().sendMessage(um);
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
		viewScreen.drawPre(g);

		// TODO: viewScreen.drawEnv(); 

		for(Entity ac:actors){
			viewScreen.drawActor(ac,g);
		}

		viewScreen.drawFinal(g);
		viewScreen.drawGui();
	}

	public void setPlayerShipId(int id){
		this.shipId = id;
	}

	public Entity getPlayerShip(){
		if(actors.size()>=shipId){
			return actors.get(shipId);
		}
		else{
			return null;
		}
	}

	public static void updateGameObjects(Hollywood<Entity> go){
		tmpAC = go;
	}
}
