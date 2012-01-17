package shared.main.entity;

import shared.main.entity.components.FreelyRotatingComponent;
import shared.main.entity.components.ImageRenderComponent;
import shared.main.entity.components.MovingComponent;
import shared.math.Vector2f;

public class EntityBuilder {
	public static Entity buildAsteroid(){
		Entity asteroid = new Entity("Asteroid");
		asteroid.addComponent(new MovingComponent()); 
		asteroid.addComponent(new ImageRenderComponent("assets/asteroid.png"));
		asteroid.addComponent(new FreelyRotatingComponent(90));
		
		return asteroid;
	}
	public static Entity buildAsteroid(Vector2f velocity){
		Entity asteroid = new Entity("Asteroid");
		
		asteroid.addComponent(new MovingComponent(velocity)); 
		asteroid.addComponent(new ImageRenderComponent("assets/asteroid.png"));
		asteroid.addComponent(new FreelyRotatingComponent((int) (Math.random()*90)));
		
		return asteroid;
	}
	
	public static Entity buildShip(Vector2f position){
		Entity ship = new Entity("Ship");
		ship.setPosition(position);
		
		ship.addComponent(new MovingComponent());
		ship.addComponent(new ImageRenderComponent("assets/plane.png"));
		
		return ship;
	}
}
