package shared.ships;

import org.newdawn.slick.geom.Vector2f;



public class JetPlane extends Ship {
	private static final long serialVersionUID = -149695681227433121L;
	
	static float scale = 1f;
	
	public JetPlane(Vector2f position,float rotation) {
		super(scale, position, rotation);
	}

	@Override
	public String getImagePath() {
		return "assets/plane.png";
	}

	@Override
	public int getRadius() {
		return 50;
	}


}
