package shared.main.entity;

import shared.main.entity.components.ImageRenderComponent;
import shared.main.entity.components.MovingComponent;

public class EntityBuilder {
	public static Entity buildAsteroid(){
		Entity asteroid = new Entity("asteroid 1");
		asteroid.addComponent(new MovingComponent()); 
		asteroid.addComponent(new ImageRenderComponent("./assets/asteroid.png"));
		
		return asteroid;
	}
}
