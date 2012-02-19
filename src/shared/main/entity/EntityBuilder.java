package shared.main.entity;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.newdawn.fizzy.Circle;
import org.newdawn.fizzy.Polygon;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.Shape;
import org.newdawn.fizzy.World;

import shared.main.entity.components.FreelyRotatingComponent;
import shared.main.entity.components.MessageHandlerComponent;
import shared.main.entity.components.MovingComponent;
import shared.main.entity.components.PhysicsComponent;
import shared.main.entity.components.rendering.CircleRenderComponent;
import shared.main.entity.components.rendering.ImageRenderComponent;
import shared.main.entity.components.rendering.PolygonRenderComponent;
import shared.main.entity.components.rendering.RectRenderComponent;
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
	
	public static Entity buildPhysicsBall(Vector2f position, Vector2f velocity, float mass, float radius, World world){
		Entity toReturn = new Entity("physball",position);
		Shape shape = new Circle(radius);
		toReturn.addComponent(new PhysicsComponent(shape, position, velocity,0.0f, mass, 0.5f,0.5f,world,true));
		toReturn.addComponent(new CircleRenderComponent(radius));
		
		return toReturn;
	}
	
	public static Entity buildPhysicsRect(Vector2f position, Vector2f dims, Vector2f velocity, float rot, float mass, World world){
		Entity toReturn = new Entity("physball",position);
		Shape shape = new Rectangle(dims.getX(), dims.getY());
		toReturn.addComponent(new PhysicsComponent(shape, position, velocity,rot, mass, 0.5f,0.5f,world,false));
		toReturn.addComponent(new RectRenderComponent(dims));
		
		return toReturn;
	}
	
	public static Entity buildPhysicsPoly(Vector2f position,List<Vector2f> points, Vector2f velocity, float rot, float mass, World world){
		Entity toReturn = new Entity("physball",position);
		
		Polygon poly = new Polygon();
		
		Vec2[] polyPoints = new Vec2[points.size()]; int i = 0;
		for(Vector2f point:points){
			polyPoints[i]=new Vec2(point.getX(),point.getY());
			i++;
		}
		poly.setPoints(polyPoints);
		
		toReturn.addComponent(new PhysicsComponent(poly, position, velocity,rot, mass, 1f,0f,world,true));
		toReturn.addComponent(new PolygonRenderComponent(poly));
		
		return toReturn;
	}
	
}
