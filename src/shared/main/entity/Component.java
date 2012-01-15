package shared.main.entity;

import java.io.Serializable;

public abstract class Component implements Serializable{
	private static final long serialVersionUID = 368834466616242524L;
	
	protected long id;
	protected String type;
	
	protected Entity parent;
	
	public Component(String type){
		this.id = Entity.getNextId();
		this.type = type;
	}
	
	public long getId(){
		return id;
	}
	public void setOwnerEntity(Entity parent){
		this.parent = parent;
	}
	
	public abstract void update(long delta);
}
