package client.gui;

import org.newdawn.slick.Graphics;

import shared.math.Vector2f;

public abstract class ScreenClickCapture extends UIElement {

	public ScreenClickCapture(Vector2f size) {
		super(new Vector2f(0,0), size);
	}

	@Override
	public void draw(Graphics g) {
		// Don't draw anything.
	}

	@Override
	public abstract void handleClick(int button, float x, float y, int clickCount);

	@Override
	public void handleDrag(float oldx, float oldy, float newx, float newy) {
		// Don't do anything
	}
	@Override
	public void handleRelease(float x, float y){
		
	}

}
