package shared.main.entity;

import shared.main.entity.components.ship.EngineeringComponent;
import shared.main.entity.components.ship.RoleComponent;
import shared.math.Vector2f;

public class Ship extends Entity{
	private static final long serialVersionUID = -5603939106663796485L;

	public Ship(String name){
		super(name);
	}
	public Ship(String name, Vector2f position) {
		super(name, position);
	}
	public void addComponent(Component component){
		super.addComponent(component);
		
		if(component instanceof EngineeringComponent){
			for(Component comp:components){
				if(comp instanceof RoleComponent){
					((RoleComponent) comp).setEngId(component.getId());
				}
			}
		}
	}
}
