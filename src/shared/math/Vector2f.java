package shared.math;

import java.io.Serializable;

/**
 * A two-dimensional vector comprised of float values.
 * @author Ty
 *
 */
public class Vector2f implements Serializable, Cloneable{
	private static final long serialVersionUID = -7784815562039648172L;
	
	private float x;
	private float y;
	
	/****************
	 * CONSTRUCTORS *
	 ****************/
	
	/**
	 * Constructs a vector with the components already set.
	 * @param x The X component
	 * @param y The Y component
	 */
	public Vector2f(float x,float y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructs a vector with an angle.
	 * @param theta The angle in *degrees*
	 */
	public Vector2f(float theta){
		this.x = 0;
		this.y = 1;
		
		System.out.println(getMagnitude());
		
		setTheta(theta);
	}
	
	/***********
	 * SETTERS *
	 ***********/
	
	/**
	 * Sets the X component
	 * @param x
	 */
	public void setX(float x){
		this.x = x;
	}
	/**
	 * Sets the Y component
	 * @param y
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * Sets the magnitude of the vector only preserving the angle
	 * @param mag The magnitude
	 */
	public void setMagnitude(float mag){
		normalize();
		timesEquals(mag);
	}
	/**
	 * Sets the angle of the vector preserving the magnitude
	 * @param theta
	 */
	public void setTheta(float theta){
		float mag = getMagnitude();
		
		this.x = (float) (mag * Math.cos(Math.toRadians(theta)));
		this.y = (float) (mag * Math.sin(Math.toRadians(theta)));
	}
	
	/*************
	 * MODIFIERS *
	 *************/
	
	/**
	 * Makes the magnitude of the vector 1
	 */
	public void normalize(){
		float mag = (float) getMagnitude();
		if(mag == 0){
			mag = 1;
		}
		
		setX((getX()/mag));
		setY((getY()/mag));
	}
	
	/**
	 * Returns a new vector that contains the components (x,y) all multiplied
	 * by a scalar value.
	 * @param scalar The value to multiply by.
	 * @return A new vector.
	 */
	public Vector2f times(float scalar){
		return new Vector2f(getX()*scalar,getY()*scalar);
	}
	/**
	 * Modifies this vector to be multiplied by a vector.
	 * For details see the other implementation.
	 * @param scalar The value to multiply by.
	 */
	public void timesEquals(float scalar){
		setX(getX()*scalar);
		setY(getY()*scalar);
	}
	/**
	 * Returns a new vector that contains the components multiplied
	 * by the respective values in another vector.
	 * The x component in this vector is multiplied by the x component in the other.
	 * The y component in this vector is multiplied by the y component in the other.
	 * @param other The other vector to be multiplied by.
	 * @return A new vector.
	 */
	public Vector2f times(Vector2f other){
		return new Vector2f(getX()*other.getX(),getY()*other.getY());
	}
	/**
	 * Modifies this vector to be multiplied by another one.
	 * See the other implementation for details.
	 * @param other The other vector
	 */
	public void timesEquals(Vector2f other){
		setX(getX()*other.getX());
		setY(getY()*other.getY());
	}
	
	/**
	 * Returns a new Vector where each (x,y) component is divided by 
	 * the scalar value.
	 * @param scalar The scalar value to be multiplied by.
	 * @return The new vector
	 */
	public Vector2f divide(float scalar){
		return new Vector2f(getX()/scalar,getY()/scalar);
	}
	/**
	 * Modifies this vector by dividing all of the components (x,y) 
	 * by the scalar value
	 * @param scalar The scalar value
	 */
	public void divideEquals(float scalar){
		setX(getX()/scalar);
		setY(getY()/scalar);
	}
	/**
	 * Returns a new vector that has all of this vectors components (x,y)
	 * divided by the respective components in the other vector.
	 * @param other The other Vector to divide by
	 * @return The new vector
	 */
	public Vector2f divide(Vector2f other){
		return new Vector2f(getX()/other.getX(),getY()/other.getY());
	}
	/**
	 * Divides this vector's components (x,y) by the components 
	 * of another.
	 * @param other 
	 */
	public void divideEquals(Vector2f other){
		setX(getX()/other.getX());
		setY(getY()/other.getX());
	}
	
	public Vector2f plus(Vector2f other){
		return new Vector2f(getX()+other.getX(),getX()+other.getX());
	}
	public void plusEquals(Vector2f other){
		setX(getX()+other.getX());
		setY(getY()+other.getY());
	}
	
	public Vector2f minus(Vector2f other){
		return new Vector2f(getX()-other.getX(),getY()-other.getY());
	}
	public void minusEquals(Vector2f other){
		setX(getX()-other.getX());
		setY(getY()-other.getY());
	}
	
	/***********
	 * GETTERS *
	 ***********/
	
	public float getMagnitude(){		
		return (float) Math.sqrt(this.x*this.x+this.y*this.y);
	}
	public float getTheta(){
		float toReturn = (float) StrictMath.toDegrees(StrictMath.atan2(y, x));
		
		if(toReturn<0){
			toReturn = 360 + toReturn;
		}
		
		return toReturn;
	}
	
	public float getX(){
		return x;
	}
	public float getWidth(){
		return getX();
	}
	public float getY(){
		return y;
	}
	public float getHeight(){
		return getY();
	}
	
	/*********
	 * OTHER *
	 *********/
	
	public Vector2f clone(){
		return new Vector2f(this.getX(),this.getY());
	}
	
	public boolean equals(Vector2f other){
		if(this.getX()!=other.getX()){
			return false;
		}
		if(this.getY()!=other.getY()){
			return false;
		}
		
		return true;
	}
	
	public String toString(){
		return "[Vector2f x: "+this.getX()+", y: "+this.getY()+", theta: "+this.getTheta()+", mag: "+this.getMagnitude()+"]";
	}
}
