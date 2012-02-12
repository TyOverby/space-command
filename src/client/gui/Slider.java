package client.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import shared.math.Vector2f;

public class Slider extends UIElement {

	private final int padding;
	private final Color bgColor;
	private final Color fgColor;
	
	private final float minPos = 0;
	private final float maxPos;
	private float curPos;
	
	public Slider(Vector2f position, Vector2f size, float percent, int padding, Color bgColor, Color fgColor){
		super(position,size);
		
		this.padding = padding;
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		
		this.maxPos = size.getY()-padding*2;
		this.curPos = percent*maxPos;
	}
	
	public float getPercent(){
		return curPos/maxPos;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(bgColor);
		g.fillRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		
		g.setColor(fgColor);
		g.fillRect(position.getX()+padding, position.getY()+padding+(maxPos-curPos), size.getWidth()-padding*2, size.getHeight()-padding*2-(maxPos-curPos));
	}

	private void setPos(float y){
		this.curPos = Math.max(minPos, Math.min(maxPos-y+padding, maxPos));
	}
	
	@Override
	public void handleClick(int button, float x, float y, int clickcount) {
		setPos(y);
	}
	
	@Override
	public void handleDrag(float oldx, float oldy, float newx, float newy) {
		setPos(newy);		
	}

	@Override
	public void handleRelease(float x, float y) {
		setPos(y);
	}
}
