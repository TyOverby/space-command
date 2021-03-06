package shared.networking;

import shared.main.entity.Entity;
import shared.main.entity.Hollywood;

/**
 * This is just to send the updates of the other game objects.  
 * @author Ty
 *
 */
public class SyncMessage extends Message{
	private static final long serialVersionUID = 8699605727198430813L;
	
	public final Hollywood<Entity> actors;
	
	public SyncMessage(Hollywood<Entity> actors){
		this.actors = actors;
	}
}
