package client.gui;

import org.newdawn.slick.Graphics;

import shared.math.Vector2f;

public abstract class UIElement {
	final Vector2f position;
	final Vector2f size;
	
	public UIElement(Vector2f position, Vector2f size){
		this.position = position;
		this.size = size;
	}
	
	public abstract void draw(Graphics g);

	public abstract void handleClick(int button, float x, float y,int clickCount);
	public abstract void handleDrag(float oldx, float oldy, float newx, float newy);
	public abstract void handleRelease(float x, float y);
	
	public Vector2f getPosition(){
		return this.position;
	}
	public Vector2f getSize(){
		return this.size;
	}
}