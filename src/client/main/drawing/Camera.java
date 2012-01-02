package client.main.drawing;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	private final Vector2f position;
	private double zoom = 1;
	
	public Camera(Vector2f position){
		this.position = position;
	}
	
	public double getZoom(){
		return zoom;
	}
	public Vector2f getPosition(){
		return position;
	}
}
