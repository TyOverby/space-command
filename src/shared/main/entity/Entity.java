package shared.main.entity;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import client.main.view.Camera;

import shared.main.entity.components.MessageHandlerComponent;
import shared.main.entity.components.RenderComponent;
import shared.math.Vector2f;
import shared.networking.Message;

/**
 * An Actor is something that moves around the screen and 
 * needs to have collision detected against.
 * @author Ty
 */
public class Entity implements Serializable {
	private static final long serialVersionUID = -7526252782026942901L;
	
	private int id;
	public final String name;
	
	float speed;
	Vector2f position;
	float scale;
	float rotation;
	
	ArrayList<RenderComponent> renderComponents = null;
	
	ArrayList<Component> components = null;
	
	/**
	 * Creates a new Entity with a name.  Positions it at 0,0 with a scale of 1.
	 * @param name The name of the entity
	 */
	public Entity(String name){
		this.name = name;
		
		renderComponents = new ArrayList<RenderComponent>();
		components = new ArrayList<Component>();
		position = new Vector2f(0,0);
		scale = 1;
		rotation = 0;
	}
	/**
	 * Creates a new Entity with a name at a position with a scale of 1.
	 * @param name
	 * @param position
	 */
	public Entity(String name,Vector2f position){
		this(name);
		this.position = position;
	}
	
	/**
	 * Adds a component to the entity.  Then it sets the component parent to this entity.
	 * @param component The component to add.
	 */
	public void addComponent(Component component){
		if(RenderComponent.class.isInstance(component)){
			renderComponents.add((RenderComponent)component);
		}
		
		component.setOwnerEntity(this);
		components.add(component);
	}
	
	/**
	 * Given an ID, returns the component
	 * @param id The id
	 * @return
	 */
	public Component getComponent(long id){
		for(Component comp:components){
			if(comp.getId()==id){
				return comp;
			}
		}
		return null;
	}
	
	/**
	 * Returns the first class that is an instance of the component.
	 * @param c The class to fetch component.
	 * @return
	 */
	public Component getComponent(Class<? extends Component> c){
		for(Component comp: components){
			if(c.isInstance(comp)){
				return comp;
			}
		}
		return null;
	}
	
	/**
	 * Checks to see if the entity contains a component of a certain class.
	 * @param c The class to check to see if it exists.
	 * @return
	 */
	public boolean containsComponent(Class<? extends Component> c){
		for(Component comp: components){
			if(c.isInstance(comp)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sends a message to the messageHandlerComponent which distributes to other components.
	 * @param message The message to send
	 */
	public void sendMessage(Message message){
		for(Component c:components){
			if(c instanceof MessageHandlerComponent){
				((MessageHandlerComponent) c).disperseMessage(message);
				return;
			}
		}
	}
	
	/**
	 * Updates all of the entities components.
	 * @param delta Amount
	 */
	public void update(long delta){
		for(Component component:components){
			component.update(delta);
		}
	}
	
	/**
	 * Renders the entity through the renderComponents.
	 * @param gr
	 * @param camera
	 * @param type
	 */
	public void render(Graphics gr, Camera camera,int type){
		if(renderComponents.size() != 0){
			for(RenderComponent rc:renderComponents){
				if(rc.isApplicable(type)){
					rc.render(gr, camera);
				}
			}
		}
	}
	
	public Vector2f getPosition(){
		return this.position;
	}
	public void setPosition(Vector2f position){
		this.position = position;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getScale(){
		return this.scale;
	}
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public float getRotation(){
		return this.rotation;
	}
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
}
