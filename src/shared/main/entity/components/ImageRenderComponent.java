package shared.main.entity.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import client.main.drawing.Camera;

import shared.main.entity.RenderComponent;
import shared.math.Vector2f;

public class ImageRenderComponent extends RenderComponent {
	private static final long serialVersionUID = 5876401439021437344L;
	
	private Image image;
 
	public ImageRenderComponent(String imgPath){
		super("ImgeRenderComponent");
		try {
			this.image = new Image(imgPath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics gr, Camera camera) {
		Vector2f drawPos = camera.globalToScreen(parent.getPosition());
		
		image.draw(drawPos.getX(), drawPos.getY(), parent.getScale());
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
	}
 
}
