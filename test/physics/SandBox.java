package physics;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import shared.main.entity.Entity;
import shared.main.entity.EntityBuilder;
import shared.main.entity.components.PhysicsComponent;
import shared.math.Vector2f;

public class SandBox extends BasicGame{
	private List<Entity> entityList = new ArrayList<Entity>();

	public SandBox()
	{
		super("Bridge");
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		Vector2f p1 = new Vector2f(200,100);
		Vector2f v1 = new Vector2f(75,75);
		float    m1 = 50;
		float    r1 = 50f;

		Vector2f p2 = new Vector2f(200,500);
		Vector2f v2 = new Vector2f(75,-75);
		float    m2 = 50f;
		float    r2 = 50f;

		entityList.add(EntityBuilder.buildPhysicsBall(p1, v1, m1, r1));
		entityList.add(EntityBuilder.buildPhysicsBall(p2, v2, m2, r2));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		for(Entity entity:entityList){
			entity.update(delta);

			if(entity.containsComponent(PhysicsComponent.class)){
				PhysicsComponent entPhys = (PhysicsComponent)entity.getComponent(PhysicsComponent.class);
				for(Entity col:entityList){
					if(!col.equals(entity) && col.containsComponent(PhysicsComponent.class) ){
						PhysicsComponent colPhys = (PhysicsComponent)col.getComponent(PhysicsComponent.class);
						if((entPhys).isColliding(colPhys) && entPhys.isCollidable() && colPhys.isCollidable()){
							entPhys.collide(colPhys);
						}
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		for(Entity entity:entityList){
			entity.render(g, null, 0);
		}
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = 
				new AppGameContainer(new SandBox());

		app.setDisplayMode(1000, 800, false);
		app.start();
	}
}