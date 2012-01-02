package shared.ships;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;



public class JetPlane extends Ship{
	static Image image;
	static float scale = 1f;
	
	public JetPlane(Vector2f position,float rotation) throws SlickException {
		super(scale, position, rotation);
		setImage(new Image("assets/plane.png"));
	}
}
