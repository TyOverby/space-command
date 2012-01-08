package shared.math;

import shared.main.DontDoThisException;

public class Point extends Vector2f{
	private static final long serialVersionUID = 4631200176667298146L;
	
	public Point(float x, float y) {
		super(x, y);
	}
	public Point(float theta) throws DontDoThisException{
		super(theta);
		throw new DontDoThisException("You shouldn't be initializing it this way");
	}
}
