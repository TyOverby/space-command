package client.main.view;

import java.util.Collection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import client.main.ClientGame;

import shared.main.entity.Entity;
import shared.main.entity.Ship;
import shared.math.Vector2f;
import shared.networking.UpdateMessage;

public abstract class ViewScreen {
	protected Vector2f dimensions;
	protected Camera camera;
	protected Graphics graphics;

	protected Ship playerShip;
	protected ClientGame clientGame;
	protected GameContainer gc;

	public ViewScreen(Vector2f panelDim,GameContainer gc,ClientGame clientGame){
		this.dimensions = panelDim;
		this.gc = gc;
		this.clientGame = clientGame;
		setCamera(new Camera(dimensions));
	}

	public void setGraphics(Graphics g){
		this.graphics = g;
	}
	public void setCamera(Camera c){
		this.camera = c;
	}

	public void update(long delta){
		this.playerShip = (Ship) clientGame.getPlayerShip();
	}

	public abstract void setupInput();	
	public abstract Collection<UpdateMessage> handleInput(Input curInput);
	public abstract void teardownInput();

	public abstract void drawPre(Graphics g);
	public abstract void drawEnv(Entity env, Graphics g);
	public abstract void drawActor(Entity actor, Graphics g);
	public abstract void drawFinal(Graphics g);
	public abstract void drawGui(Graphics g);
}
