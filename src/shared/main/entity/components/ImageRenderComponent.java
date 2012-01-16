package shared.main.entity.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import client.main.drawing.Camera;
import client.resourcemanager.ImageManager;

import shared.main.entity.RenderComponent;
import shared.math.Vector2f;

public class ImageRenderComponent extends RenderComponent {
	private static final long serialVersionUID = 5876401439021437344L;
 
	private final String url;
	
	public ImageRenderComponent(String imgPath){
		super("ImageRenderComponent");
		url = imgPath;
	}

	@Override
	public void render(Graphics gr, Camera camera) {
		Vector2f drawPos = camera.globalToScreen(parent.getPosition());
		Image image = ImageManager.getOrPutImage(id, url);
		
		float scale = camera.getZoom()*this.parent.getScale();
		image.setCenterOfRotation((image.getWidth()/2)*scale, (image.getHeight()/2)*scale);
		image.setRotation(parent.getRotation());
		
		image.draw(drawPos.getX()*scale-(image.getWidth()/2)*scale, drawPos.getY()*scale-(image.getHeight()/2)*scale,scale);
		gr.drawRect(drawPos.getX()-1, drawPos.getY()-1, 3, 3);
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
	} 
}
