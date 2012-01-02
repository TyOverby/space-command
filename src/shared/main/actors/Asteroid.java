package shared.main.actors;

import org.newdawn.slick.geom.Vector2f;

import shared.main.Actor;

public class Asteroid implements Actor{

	private static final long serialVersionUID = -6198898167039412937L;

	private float rotation = 0;
	
	@Override
	public float getScale() {
		return 2;
	}

	@Override
	public float getRotation() {
		return this.rotation;
	}

	@Override
	public float getVelocity() {
		return 0;
	}

	@Override
	public Vector2f getPosition() {
		return new Vector2f(50,50);
	}

	@Override
	public String getImagePath() {
		return "assets/plane.png";
	}

	@Override
	public int getRadius() {
		return 100;
	}
	
	@Override
	public void update(){
		this.rotation++;
	}
	
}
