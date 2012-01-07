package shared.math;

import java.io.Serializable;

public class Vector2f implements Serializable, Cloneable{
	private static final long serialVersionUID = -7784815562039648172L;
	
	private float x;
	private float y;
	
	/****************
	 * CONSTRUCTORS *
	 ****************/
	
	public Vector2f(float x,float y){
		this.x = x;
		this.y = y;
	}
	public Vector2f(float theta){
		this.x = 0;
		this.y = 1;
		
		System.out.println(getMagnitude());
		
		setTheta(theta);
	}
	
	/***********
	 * SETTERS *
	 ***********/
	
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	
	public void setMagnitude(float mag){
		normalize();
		timesEquals(mag);
	}
	public void setTheta(float theta){
		float mag = getMagnitude();
		
		this.x = (float) (mag * Math.cos(Math.toRadians(theta)));
		this.y = (float) (mag * Math.sin(Math.toRadians(theta)));
	}
	
	/*************
	 * MODIFIERS *
	 *************/
	
	public void normalize(){
		float mag = (float) getMagnitude();
		
		setX((getX()/mag));
		setY((getY()/mag));
	}
	
	public Vector2f times(float scalar){
		return new Vector2f(getX()*scalar,getY()*scalar);
	}
	public void timesEquals(float scalar){
		setX(getX()*scalar);
		setY(getY()*scalar);
	}
	public Vector2f times(Vector2f other){
		return new Vector2f(getX()*other.getX(),getY()*other.getY());
	}
	public void timesEquals(Vector2f other){
		setX(getX()*other.getX());
		setY(getY()*other.getY());
	}
	
	public Vector2f divide(float scalar){
		return new Vector2f(getX()/scalar,getY()/scalar);
	}
	public void divideEquals(float scalar){
		setX(getX()/scalar);
		setY(getY()/scalar);
	}
	public Vector2f divide(Vector2f other){
		return new Vector2f(getX()/other.getX(),getY()/other.getY());
	}
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
	public float getY(){
		return y;
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
