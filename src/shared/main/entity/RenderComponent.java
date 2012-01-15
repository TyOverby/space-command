package shared.main.entity;

import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;

public abstract class RenderComponent extends Component{
	private static final long serialVersionUID = 3786843882640823531L;

	public RenderComponent(String type){
		super(type);
	}
	
	public abstract void render(Graphics gr, Camera camera);
	
	public abstract boolean isApplicable(int type);
}
