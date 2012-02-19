package shared.main.entity.components.rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import shared.math.Vector2f;

import client.main.view.Camera;

public class PolygonRenderComponent extends RenderComponent{
	private static final long serialVersionUID = -6744744114710658660L;

	private final Polygon renderPolygon = new Polygon();
	
	public PolygonRenderComponent(org.newdawn.fizzy.Polygon inputPoly) {
		super("RectRenderComponent");
		
		for(int i=0;i<inputPoly.getPointCount();i++){
			renderPolygon.addPoint(inputPoly.getPointX(i), inputPoly.getPointY(i));
			System.out.println(""+inputPoly.getPointX(i)+", "+inputPoly.getPointY(i));
		}
	}

	@Override
	public void render(Graphics gr, Camera camera) {
		Vector2f pos = parent.getPosition();
		
		gr.setAntiAlias(true);
		gr.setColor(new Color(255,255,255,100));
		gr.rotate(pos.getX(), pos.getY(), parent.getRotation());
		gr.fill(renderPolygon);
		gr.rotate(pos.getX(), pos.getY(), -1*parent.getRotation());
		
		gr.setColor(Color.red);
		gr.fillRect(parent.getPosition().getX(), parent.getPosition().getY(), 1, 1);
	}

	@Override
	public boolean isApplicable(int type) {
		return true;
	}

	@Override
	public void update(long delta) {
//		renderPolygon.setCenterX(parent.getPosition().getX());
//		renderPolygon.setCenterY(parent.getPosition().getY());
		renderPolygon.setLocation(parent.getPosition().getX(), parent.getPosition().getY());
//		System.out.println(parent.getPosition().getY());
	}
}
