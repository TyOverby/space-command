package shared.main.entity.components;

import shared.main.entity.Component;
import shared.math.Vector2f;

public class MovingComponent extends Component{
	private static final long serialVersionUID = -3671444778663024028L;

	private Vector2f velociy;
	
	public MovingComponent() {
		super("Moving");
	}
	
	@Override
	public void update(long delta) {
		float fracOfSecond = (float)delta/1000;
		parent.getPosition().plusEquals(velociy.times(fracOfSecond));
	}
}
