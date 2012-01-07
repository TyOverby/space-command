package shared.main.actors;

import shared.math.Vector2f;

import shared.main.Actor;

public class Asteroid extends Actor{
	private static final long serialVersionUID = -6198898167039412937L;

	public Asteroid(Vector2f position, Vector2f velocity, float radius, float rotation) {
		super(position, velocity, radius, rotation);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		this.setRotation(this.getRotation()+1);
	}

	@Override
	public String getTexturePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSimplifiedImagePath() {
		// TODO Auto-generated method stub
		return null;
	}
}
