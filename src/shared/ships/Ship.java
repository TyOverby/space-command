package shared.ships;

import shared.math.Vector2f;

import shared.main.Actor;

public abstract class Ship extends Actor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6591529703014415036L;
	
	public Ship(Vector2f position,Vector2f velocity,float radius,float rotation){
		super(position, velocity, radius, rotation);
	}
	
	public void update(long delta){
		
	}
}
