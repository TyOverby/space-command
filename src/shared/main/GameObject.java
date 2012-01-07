package shared.main;

import java.io.Serializable;

import shared.math.Vector2f;

/**
 * For anything in the game.
 * @author Ty
 *
 */
public abstract class GameObject implements Serializable{
	private static final long serialVersionUID = 5175693088113933729L;
	
	private float scale = 1;
	private float rotation = 0;
	private Vector2f position;
	
	// Texture detail
	public abstract String getTexturePath();
	public abstract String getSimplifiedImagePath();
	
	// Getters and Setters
	public float getScale(){
		return scale;
	}
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
