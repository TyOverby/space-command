package client.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import client.main.drawing.HelmVS;
import client.main.drawing.ViewScreen;
import client.networking.ClientPort;

import shared.main.Game;
import shared.main.entity.Entity;
import shared.main.entity.Hollywood;
import shared.math.Vector2f;
import shared.networking.ConnectMessage;
import shared.networking.UpdateMessage;

public class ClientGame extends Game{
	private static Hollywood<Entity> tmpAC = new Hollywood<Entity>();
	
	private ViewScreen viewScreen;
	
	ClientPort clientPort = null;
	
	/**
	 * 
	 */
	public void init(Vector2f screenDims){
		
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
	
	@Override
	public void collectInput(GameContainer gc) {
		Input currentInput = gc.getInput();
		if(currentInput.isKeyDown(Input.KEY_SPACE)){
			clientPort.say(new UpdateMessage(UpdateMessage.Destination.HELM,UpdateMessage.Type.ANGLE,180));
		}
	}

	public void update(long delta){
		viewScreen.update(delta);
		
		// Update the old items
		if(tmpAC!=null){
			actors=new Hollywood<Entity>(tmpAC);
			tmpAC = null;
		}
		
		for(Entity ac:actors){
			//System.out.println(ac.name);
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
