package shared.main.entity.components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import client.main.view.Camera;

public class CircleRenderComponent extends RenderComponent{
	private static final long serialVersionUID = -6744744114710658660L;

	public CircleRenderComponent() {
		super("CircleRenderComponent");
	}

	@Override
	public void render(Graphics gr, Camera camera) {
		float radius = ((PhysicsComponent)parent.getComponent(PhysicsComponent.class)).getRadius();
		
		gr.setColor(Color.white);
		gr.fillOval(parent.getPosition().getX()-radius, parent.getPosition().getY()-radius, radius*2, radius*2);
		gr.setColor(Color.red);
		gr.fillRect(parent.getPosition().getX(), parent.getPosition().getY(), 1, 2);
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
	}
}
