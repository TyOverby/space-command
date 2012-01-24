package shared.main.entity;

import shared.main.entity.components.FreelyRotatingComponent;
import shared.main.entity.components.ImageRenderComponent;
import shared.main.entity.components.MessageHandlerComponent;
import shared.main.entity.components.MovingComponent;
import shared.main.entity.components.ship.EngineeringComponent;
import shared.main.entity.components.ship.HelmComponent;
import shared.main.entity.components.ship.RoleComponent;
import shared.math.Vector2f;

public class EntityBuilder {
	public static Entity buildAsteroid(){
		Entity asteroid = new Entity("Asteroid");
		asteroid.addComponent(new ImageRenderComponent("assets/asteroid.png"));
		asteroid.addComponent(new FreelyRotatingComponent(90));
		
		return asteroid;
	}
	public static Entity buildAsteroid(Vector2f position){
		Entity asteroid = new Entity("Asteroid",position);
		asteroid.addComponent(new ImageRenderComponent("assets/asteroid.png"));
		asteroid.addComponent(new FreelyRotatingComponent((int) (Math.random()*90)));
		
		return asteroid;
	}
	
	public static Ship buildShip(String shipName,Vector2f position){
		Ship ship = new Ship(shipName);
		ship.setPosition(position);
		
		ship.addComponent(new MovingComponent());
		ship.addComponent(new ImageRenderComponent("assets/plane.png"));
		RoleComponent helmComponent = new HelmComponent();
		ship.addComponent(helmComponent);
		ship.addComponent(new EngineeringComponent());
		ship.addComponent(new MessageHandlerComponent(helmComponent));
		
		return ship;
	}
}
