package shared.main;

import java.io.Serializable;

import shared.math.Vector2f;

/**
 * An Actor is something that moves around the screen and 
 * needs to have collision detected against.
 * @author Ty
 *
 */
public abstract class Actor extends GameObject implements Serializable {
	private static final long serialVersionUID = -7526252782026942901L;

	// Position stuff
	private Vector2f velocity;
	
	// Collision stuff
	private float radius;
	
	// The many constructors!
	public Actor(Vector2f position,Vector2f velocity,float radius, float rotation){
		setPosition(position);
		setRadius(radius);
		setRotation(rotation);
		
		setVelocity(velocity);
	}
	public Actor(Vector2f position,Vector2f velocity,float radius,float rotation,float scale){
		setPosition(position);
		setRadius(radius);
		setRotation(rotation);
		
		setVelocity(velocity);
		setScale(scale);
	}
	
	// Updating
	public void update(long delta){
		this.getPosition().plusEquals(velocity);
	}
	
	// Getters and setters
	public Vector2f getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
}
