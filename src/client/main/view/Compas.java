package client.main.view;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import client.main.drawing.Camera;

import shared.math.Vector2f;

public class Compas{
	private static class Tick{
		private final int magicHeight = 16/2;
		private final int magicWidth = 28/2;

		private final Vector2f start;
		private final Vector2f end;
		private final Vector2f textPos;

		private final String text;

		public Tick(float angle,float baseRadius,float subtract, Camera c){
			start = new Vector2f(angle);
			start.timesEquals(baseRadius);

			end = new Vector2f(angle);
			end.timesEquals(baseRadius-subtract);

			textPos = new Vector2f(angle);
			textPos.timesEquals(baseRadius+20);

			String dummyText = (""+(int)c.globalToScreen(angle)+"");
			for(int i=dummyText.length();i<3;i++){
				dummyText = "0"+dummyText;
			}
			text = dummyText;
		}

		public void draw(Graphics g,Vector2f center){
			g.drawLine(start.getX()+center.getX(), start.getY()+center.getY(), end.getX()+center.getX(), end.getY()+center.getY());

			g.drawString(text, textPos.getX()+center.getX()-magicWidth, textPos.getY()+center.getY()-magicHeight);
		}

		public String toString(){
			return this.text;
		}
	}

	private final List<Tick> ticks = new ArrayList<Tick>(360);
	private final float radius;

	private Image localImg = null;

	public Compas(float radius,Camera c){
		// Prepare
		this.radius = radius;

		for(int i=0;i<360;i+=15){
			int mod = (i%45==0)?10:0;
			ticks.add(new Tick(i,radius,10+mod,c));
		}

		// Set up the image
		int padding = 100;
		int width = (int) (radius*2);
		int height= (int) (radius*2);

		Graphics localImgG = null;

		try {
			localImg = new Image(width+padding,height+padding);
			localImgG = localImg.getGraphics();
		} catch (SlickException e) {
			Log.error("creating local image or graphics context failed: " + e.getMessage());
		}

		localImgG.setAntiAlias(true);
		localImgG.setLineWidth(2);
		localImgG.clear();
		localImgG.setColor(Color.white);

		localImgG.drawOval(padding/2, padding/2, width,height);
		for(Tick tick:ticks){
			tick.draw(localImgG,new Vector2f(radius+padding/2,radius+padding/2));
		}

		localImgG.flush();
	}


	public void draw(Vector2f center,Graphics g) {
		g.drawImage(localImg, center.getX()-localImg.getWidth()/2,center.getY()-localImg.getHeight()/2);
	}
	public void drawArc(float angleStart, float angleEnd, Vector2f center, Graphics g){

		// Arc
		g.setColor(new Color(0,100,100,50));
		g.fillArc(center.getX()-radius, center.getY()-radius, radius*2, radius*2, angleStart, angleEnd);
		if(angleStart<10){
			g.fillArc(center.getX()-radius, center.getY()-radius, radius*2, radius*2, angleStart, angleStart+10);
		}
		// Lines
		// Start Line

		Vector2f startLine = new Vector2f(angleStart);
		startLine.timesEquals(radius);
		startLine.plusEquals(center);

		Vector2f endLine = new Vector2f(angleEnd);
		endLine.timesEquals(radius);
		endLine.plusEquals(center);

		g.setColor(new Color(0,100,100,200));
		g.setLineWidth(3);
		g.drawLine(center.getX(), center.getY(), startLine.getX(), startLine.getY());	
		g.drawLine(center.getX(), center.getY(), endLine.getX(), endLine.getY());

		g.setAntiAlias(false);
	}
}
