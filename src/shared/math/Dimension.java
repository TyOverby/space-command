package shared.math;

import shared.main.DontDoThisException;

public class Dimension extends Vector2f{
	private static final long serialVersionUID = -4847462107071798595L;
	
	public Dimension(float x, float y) {
		super(x, y);
	}
	public Dimension(float theta) throws DontDoThisException{
		super(theta);
		throw new DontDoThisException("You shouldn't be initializing it this way");
	}
	
	public float getWidth(){
		return getX();
	}
	public float getHeight(){
		return getY();
	}
}
