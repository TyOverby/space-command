package client.main.drawing;

import org.newdawn.slick.GameContainer;

import shared.math.Dimension;
import shared.math.Vector2f;

public class Camera {
	private Dimension screenDims;
	private Vector2f position;
	private float zoom = 5;
	
	public Camera(Dimension screenDimension){
		this.screenDims = screenDimension;
	}
	public Camera(Dimension screenDimension,Vector2f position){
		this.position = position;
		this.screenDims = screenDimension;
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
		toReturn.setMagnitude((float) (toReturn.getMagnitude()/getZoom()));
		
		toReturn.setX(toReturn.getX()+gc.getWidth()/2);
		toReturn.setY(toReturn.getY()+gc.getHeight()/2);
		
		return toReturn;
	}
	
	public Vector2f ScreenToGlobal(Vector2f screenPosition){
		Vector2f toReturn = screenPosition.clone();
		
		toReturn.setY(toReturn.getY()-gc.getHeight()/2);
		toReturn.setX(toReturn.getX()-gc.getWidth()/2);
		
		toReturn.setMagnitude((float)(toReturn.getMagnitude()*getZoom()));
		toReturn.setY(toReturn.getY()*-1);
		
		return toReturn;
	}
}
