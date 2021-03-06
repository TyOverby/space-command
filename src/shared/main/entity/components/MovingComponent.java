package shared.main.entity.components;

import shared.main.entity.Component;
import shared.math.Vector2f;

public class MovingComponent extends Component{
	private static final long serialVersionUID = -3671444778663024028L;

	private Vector2f velocity;
	
	public MovingComponent(){
		this(new Vector2f(0,0));
	}
	public MovingComponent(Vector2f velocity){
		super("MovingComponent");
		this.velocity = velocity;
	}
	
	@Override
	public void update(long delta) {
		float fracOfSecond = (float)delta/1000;
		
		parent.getPosition().plusEquals(velocity.times(fracOfSecond));
	}
	
	public void setVelocity(Vector2f newVelocity){
		this.velocity = newVelocity;
	}
	
	public Vector2f getVelocity(){
		return velocity;
	}
}
