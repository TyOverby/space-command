package shared.main.entity.components.rendering;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import client.main.view.Camera;
import client.resourcemanager.ImageManager;

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
		image.setRotation(parent.getRotation()+90);
		
		image.draw(drawPos.getX()-(image.getWidth()/2)*scale, drawPos.getY()-(image.getWidth()/2*scale),scale);
		gr.drawRect(drawPos.getX()-1, drawPos.getY()-1, 3, 3);
		
		// Don't bother with any of this
		Vector2f inFront = new Vector2f(parent.getRotation());
		inFront.timesEquals(50);
		inFront.plusEquals(parent.getPosition());
		inFront = camera.globalToScreen(inFront);
		gr.drawRect(inFront.getX()-1, inFront.getY()-1, 3, 3);
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
	} 
}
