package shared.main.entity;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;

import shared.math.Vector2f;

/**
 * An Actor is something that moves around the screen and 
 * needs to have collision detected against.
 * @author Ty
 *
 */
public class Entity implements Serializable {
	private static final long serialVersionUID = -7526252782026942901L;
	private static long idTracker = 0;
	
	public static long getNextId(){
		return idTracker++;
	}
	
	public final long id;
	public final String name;
	
	Vector2f position;
	float scale;
	float rotation;
	
	ArrayList<RenderComponent> renderComponents = null;
	
	ArrayList<Component> components = null;
	
	public Entity(String name){
		this.id = Entity.getNextId();
		this.name = name;
		
		renderComponents = new ArrayList<RenderComponent>();
		components = new ArrayList<Component>();
		position = new Vector2f(0,0);
		scale = 1;
		rotation = 0;
	}
	public Entity(String name,Vector2f position){
		this(name);
		this.position = position;
	}
	
	public void addComponent(Component component){
		if(RenderComponent.class.isInstance(component)){
			renderComponents.add((RenderComponent)component);
		}
		
		component.setOwnerEntity(this);
		components.add(component);
	}
	
	public Component getComponent(long id){
		for(Component comp:components){
			if(comp.getId()==id){
				return comp;
			}
		}
		return null;
	}
	
	public void update(long delta){
		for(Component component:components){
			component.update(delta);
		}
	}
	
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
	
	public long getId(){
		return this.id;
	}
}
