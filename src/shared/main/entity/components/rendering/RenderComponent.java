package shared.main.entity.components.rendering;

import org.newdawn.slick.Graphics;

import shared.main.entity.Component;

import client.main.view.Camera;

public abstract class RenderComponent extends Component{
	private static final long serialVersionUID = 3786843882640823531L;

	public RenderComponent(String type){
		super(type);
	}
	
	public abstract void render(Graphics gr, Camera camera);
	
	public abstract boolean isApplicable(int type);
}
