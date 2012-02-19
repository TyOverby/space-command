package shared.main.entity.components.rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import shared.math.Vector2f;

import client.main.view.Camera;

public class RectRenderComponent extends RenderComponent{
	private static final long serialVersionUID = -6744744114710658660L;

	private final Vector2f dims;
	
	public RectRenderComponent(Vector2f dims) {
		super("RectRenderComponent");
		this.dims = dims;
	}

	@Override
	public void render(Graphics gr, Camera camera) {
		Vector2f pos = parent.getPosition();
		
		gr.setAntiAlias(true);
		gr.setColor(new Color(255,255,255,100));
		gr.rotate(pos.getX(), pos.getY(), parent.getRotation());
		gr.fillRect(pos.getX(), pos.getY(), dims.getX(), dims.getY());
		
		gr.setColor(Color.red);
		gr.fillRect(parent.getPosition().getX(), parent.getPosition().getY(), 1, 1);
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
	}
}
