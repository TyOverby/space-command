package shared.ships;

import org.newdawn.slick.geom.Vector2f;

import shared.main.Actor;

public abstract class Ship implements Actor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6591529703014415036L;
	private Vector2f position;
	private float velocity;
	private float rotation;
	
	private float scale;
	
	public Ship(float scale,Vector2f position,float rotation){
		this.scale = scale;
		
		this.position = new Vector2f(100, 100);
		this.rotation = 10f;
	}
	
	public void update(){
		
	}
	
	public void setRotation(float rot){
		this.rotation = rot;
	}
	public float getRotation(){
		return this.rotation;
	}
	
	
	@Override
	public float getVelocity() {
		return this.velocity;
	}

	@Override
	public Vector2f getPosition() {
		return this.position;
	}
	
	@Override
	public float getScale(){
		return scale;
	}
}
