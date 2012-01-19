package shared.main.entity;

import shared.main.entity.components.HelmComponent;
import shared.math.Vector2f;

public class Ship extends Entity{
	private static final long serialVersionUID = -5603939106663796485L;

	public Ship(String name){
		super(name);
	}
	public Ship(String name, Vector2f position) {
		super(name, position);
	}
	
	private HelmComponent helmComponent;

	public void addComponent(Component component){
		super.addComponent(component);
		
		if(component instanceof HelmComponent){
			this.helmComponent = (HelmComponent) component;
		}
	}
	
	public HelmComponent getHelmComponent(){
		return this.helmComponent;
	}
}
