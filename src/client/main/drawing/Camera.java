package client.main.drawing;

import org.newdawn.slick.GameContainer;
import shared.math.Vector2f;

public class Camera {
	private GameContainer gc;
	private Vector2f position;
	private float zoom = 1;
	
	public Camera(GameContainer gc){
		this.gc = gc;
	}
	public Camera(GameContainer gc,Vector2f position){
		this.position = position;
		this.gc = gc;
	}
	
	public void setPosition(Vector2f position){
		this.position = position;
	}
	public void setCenter(Vector2f center){
		this.position = new Vector2f(center.getX()-gc.getWidth()/2,center.getY()-gc.getHeight()/2);
	}
	public void setZoom(float zoom){
		this.zoom = zoom;
	}
	
	public double getZoom(){
		return zoom;
	}
	public Vector2f getPosition(){
		return position;
	}
	public Vector2f getCenter(){
		return new Vector2f(position.getX()+gc.getWidth()/2,position.getY()+gc.getHeight()/2);
	}
	
	public Vector2f globalToScreen(Vector2f globalPosition){
		Vector2f toReturn = globalPosition.clone();
		
		toReturn.setY(toReturn.getY()*-1);  // Invert the Y axis to actually make sense.
		toReturn.setX(toReturn.getX()); //TODO: make this work
		toReturn.setMagnitude((float) (toReturn.getMagnitude()/getZoom()));
		
		return toReturn;
	}
}
