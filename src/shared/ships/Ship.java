package shared.ships;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import client.main.Drawable;
import client.main.drawing.Camera;

public abstract class Ship implements Drawable{
	private Vector2f position;
	private float rotation;
	
	private Image image;
	private float scale;
	
	public Ship(float scale,Vector2f position,float rotation) throws SlickException{
		this.scale = scale;
		
		this.position = new Vector2f(100, 100);
		this.rotation = 10f;
	}
	
	protected void setImage(Image image){
		this.image = image;
	}
	
	public void update(){
		
	}
	
	public void draw(Camera camera){
		image.rotate(rotation);
		image.draw(position.x, position.y, scale);
	}	
	
	
	
	public void setRotation(float rot){
		this.rotation = rot;
	}
	public float getRotation(){
		return this.rotation;
	}
}
