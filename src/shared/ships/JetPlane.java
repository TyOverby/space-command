package shared.ships;

import shared.math.Vector2f;



public class JetPlane extends Ship {
	private static final long serialVersionUID = -149695681227433121L;
	
	public JetPlane(Vector2f position,Vector2f velocity,float radius,float rotation){
		super(position, velocity, radius, rotation);
	}

	@Override
	public String getTexturePath() {
		return "assets/plane.png";
	}

	@Override
	public String getSimplifiedImagePath() {
		return "assets/plane.png";
	}
}
