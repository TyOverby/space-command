package physics;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.fizzy.World;
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
	World world = new World(0);

	public SandBox()
	{
		super("Bridge");
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		
//		{
//			Vector2f pos = new Vector2f(200,100);
//			Vector2f vel = new Vector2f(0,1);
//			float    mass = 50;
//			float    radius = 100f;
//
//			entityList.add(EntityBuilder.buildPhysicsBall(pos, vel, radius, mass,world));
//		}

		{
			Vector2f pos = new Vector2f(200,100);
			Vector2f vel = new Vector2f(0,0);
			
			float    mass = 5;
			float    rot  = 1;

			List<Vector2f> pointList = new ArrayList<Vector2f>();
			pointList.add(new Vector2f(   0  , 40  ));
			pointList.add(new Vector2f( -20  , 20  ));
			pointList.add(new Vector2f( -20 , -20   ));
			pointList.add(new Vector2f(  20  , -20 ));
			pointList.add(new Vector2f(  20  , 20 ));

			entityList.add(EntityBuilder.buildPhysicsPoly(pos,pointList, vel, rot, mass, world));
		}

		{
			Vector2f pos = new Vector2f(0,500);
			Vector2f vel = new Vector2f(0,0);
			Vector2f dims = new Vector2f(500,10);

			float mass = 100;
			float rot = 0f;

			entityList.add(EntityBuilder.buildPhysicsRect(pos, dims, vel,rot, mass, world));
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		world.update(1f/60f);

		for(Entity entity:entityList){
			entity.update(delta);
		}
		
		if(gc.getInput().isMouseButtonDown(0)){
			for(Entity entity:entityList){
				Vector2f forceVector = new Vector2f(entity.getRotation()+90);
				Vector2f fromVector = entity.getPosition().plus(forceVector.times(-1f).times(30f));
				forceVector.timesEquals(50);
				//forceVector.plusEquals(entity.getPosition());
				
				((PhysicsComponent)entity.getComponent(PhysicsComponent.class)).getBody().applyForce(forceVector.getX(),forceVector.getY(),fromVector.getX(),fromVector.getY(),false);
				
				
			}
		}
		if(gc.getInput().isMouseButtonDown(1)){
			for(Entity entity:entityList){
				((PhysicsComponent)entity.getComponent(PhysicsComponent.class)).getBody().applyTorque(-50);
				
				//System.out.println("applying force");
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		for(Entity entity:entityList){
			entity.render(g, null, 0);
			
			Vector2f forceVector = new Vector2f(entity.getRotation()+90);
			Vector2f fromVector = entity.getPosition().plus(forceVector.times(-1f).times(30f));
			forceVector.timesEquals(50);
			forceVector.plusEquals(entity.getPosition());
			
			g.setColor(Color.red);
			g.drawRect(forceVector.getX()-2, forceVector.getY()-2, 4, 4);
			
			g.setColor(Color.blue);
			g.drawRect(fromVector.getX()-2, fromVector.getY()-2, 4, 4);
			
		}
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = 
				new AppGameContainer(new SandBox());

		app.setDisplayMode(1000, 800, false);
		app.start();
	}
}