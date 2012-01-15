package client.main.drawing;

import org.newdawn.slick.Graphics;

import shared.main.entity.Entity;
import shared.math.Vector2f;

public abstract class ViewScreen {
	protected Vector2f dimensions;
	protected Camera camera;
	protected Graphics graphics;
	
	public ViewScreen(Vector2f dimensions){
		this.dimensions = dimensions;
	}
	
	public void setGraphics(Graphics g){
		this.graphics = g;
	}
	public void setCamera(Camera c){
		this.camera = c;
	}
	
	public abstract void update(long delta);
	public abstract void drawPre();
	public abstract void drawEnv(Entity env);
	public abstract void drawActor(Entity actor);
	public abstract void drawFinal();
	public abstract void drawGui();
}
