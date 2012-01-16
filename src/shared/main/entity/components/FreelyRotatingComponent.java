package shared.main.entity.components;

import shared.main.entity.Component;

public class FreelyRotatingComponent extends Component {
	private static final long serialVersionUID = 4170929592569803734L;

	private int degrees;
	public FreelyRotatingComponent(int degreesPerSec) {
		super("FreelyRotatingComponent");
		degrees = degreesPerSec;
	}

	@Override
	public void update(long delta) {
		float fracOfSecond = (float)delta/1000;
		parent.setRotation(parent.getRotation()+(degrees*fracOfSecond));
	}
}
