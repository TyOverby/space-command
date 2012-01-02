package shared.main;

/**
 * For anything in the game.
 * @author Ty
 *
 */
public interface GameObject {
	public void update();
	
	public String getImagePath();
	public float getScale();
	public float getRotation();
}
