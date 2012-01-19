package shared.main.entity;

import java.io.Serializable;

public abstract class Component implements Serializable{
	private static final long serialVersionUID = 368834466616242524L;
	
	private static int idCount=0;
	private static int getNewId(){
		return idCount++;
	}
	
	
	protected int id;
	protected String type;
	
	protected Entity parent;
	
	public Component(String type){
		this.id = getNewId();
		this.type = type;
	}
	
	public int getId(){
		return id;
	}
	public void setOwnerEntity(Entity parent){
		this.parent = parent;
	}
	
	public abstract void update(long delta);
}
