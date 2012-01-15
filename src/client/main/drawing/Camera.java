package client.main.drawing;

import shared.math.Vector2f;

/**
 * A camera keeps track of where the player is looking and provides
 * methods to help drawing functions with their calculations.  
 * Internally, the class stores it's information in Screen positions:
 * the pixel data of the screen.
 * @author Ty
 *
 */
public class Camera {
	private Vector2f screenDims;
	private Vector2f position;
	private float zoom = 1;
	
	/**
	 * Basic camera without a position
	 * @param screenDimension The size (width,height) of the current viewport.
	 */
	public Camera(Vector2f screenDimension){
		this.screenDims = screenDimension;
		this.position = new Vector2f(0,0);
	}
	/**
	 * Camera with a size and position input
	 * @param screenDimension The size (width,height) of the current viewport.
	 * @param centerPosition The starting position of the camera.  Note that this is the center of the screen.
	 */
	public Camera(Vector2f screenDimension,Vector2f centerPosition){
		this.screenDims = screenDimension;
		setCenter(centerPosition);
	}
	
	/**
	 * Sets the position of the camera directly.  The position is the top right-hand corner position.
	 * @param position The position to move the camera to.
	 */
	public void setPosition(Vector2f position){
		this.position = position;
	}
	/**
	 * Takes the screen size into account when positioning the camera.
	 * @param center The position to center the camera on.
	 */
	public void setCenter(Vector2f center){
		this.position = new Vector2f(center.getX()-screenDims.getX()/2,center.getY()-screenDims.getHeight()/2);
	}
	/**
	 * Sets the Zoom of the camera.  Numbers larger than 1 make items smaller, while 
	 * numbers smaller than one but larger than zero makes them bigger.
	 * @param zoom The zoom level.
	 */
	public void setZoom(float zoom){
		this.zoom = zoom;
	}
	
	/**
	 * @return The zoom level of the camera.  x>1 is zoomed out, 1<x>0 is zoomed in.
	 */
	public float getZoom(){
		return zoom;
	}
	/**
	 * Gets the position of the top left corner of the camera.
	 * @return
	 */
	public Vector2f getPosition(){
		return position;
	}
	/**
	 * Gets the point currently in the center of the camera
	 * @return
	 */
	public Vector2f getCenter(){
		return new Vector2f(position.getX()+screenDims.getWidth()/2,position.getY()+screenDims.getHeight()/2);
	}
	
	/**
	 * Given a global position, this function will calculate the position of that item on the screen
	 * @param globalPosition The position in the game.
	 * @return The position on the screen.
	 */
	public Vector2f globalToScreen(Vector2f globalPosition){
		Vector2f toReturn = globalPosition.clone();
		
		toReturn.setY(toReturn.getY()*-1);  // Invert the Y axis to actually make sense.
		toReturn.setMagnitude((float) (toReturn.getMagnitude()/getZoom()));
		
		toReturn.setX(toReturn.getX()+screenDims.getWidth()/2);
		toReturn.setY(toReturn.getY()+screenDims.getHeight()/2);
		
		return toReturn;
	}
	
	/**
	 * Given a point on the screen, calculate the nearest game-point.
	 * @param screenPosition The x,y point on the screen.
	 * @return The point in game.
	 */
	public Vector2f ScreenToGlobal(Vector2f screenPosition){
		Vector2f toReturn = screenPosition.clone();
		
		toReturn.setY(toReturn.getY()-screenDims.getHeight()/2);
		toReturn.setX(toReturn.getX()-screenDims.getWidth()/2);
		
		toReturn.setMagnitude((float)(toReturn.getMagnitude()*getZoom()));
		toReturn.setY(toReturn.getY()*-1);
		
		return toReturn;
	}
}
