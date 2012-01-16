package shared.main.entity;

import shared.main.entity.components.FreelyRotatingComponent;
import shared.main.entity.components.ImageRenderComponent;
import shared.main.entity.components.MovingComponent;

public class EntityBuilder {
	public static Entity buildAsteroid(){
		Entity asteroid = new Entity("Asteroid");
		asteroid.addComponent(new MovingComponent()); 
		asteroid.addComponent(new ImageRenderComponent("assets/asteroid.png"));
		asteroid.addComponent(new FreelyRotatingComponent(90));
		
		return asteroid;
	}
}
