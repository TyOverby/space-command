package shared.main;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

/**
 * An Actor is something that moves around the screen and 
 * needs to have collision detected against.
 * @author Ty
 *
 */
public interface Actor extends Serializable, GameObject{
	
	public abstract float getVelocity();
	public abstract Vector2f getPosition();
	public abstract String getImagePath();
	
	public abstract int getRadius();
}
