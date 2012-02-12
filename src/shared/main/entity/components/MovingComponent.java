package shared.main.entity.components;

import shared.main.entity.Component;
import shared.math.Vector2f;

public class MovingComponent extends Component{
	private static final long serialVersionUID = -3671444778663024028L;

	private Vector2f velocity;
	
	private boolean firstRun = true;
	private float firstSpeed;
	
	public MovingComponent(){
		this(0f);
	}
	public MovingComponent(float speed){
		super("MovingComponent");
		firstSpeed = speed;
	}
	
	@Override
	public void update(long delta) {
		if(firstRun){
			parent.setSpeed(firstSpeed);
			firstRun = false;
		}
		
		float fracOfSecond = (float)delta/1000;
		float speed = parent.getSpeed();
		velocity = new Vector2f(parent.getRotation()).times(speed);
		parent.getPosition().plusEquals(velocity.times(fracOfSecond));
	}
}
