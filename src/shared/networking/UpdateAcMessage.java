package shared.networking;

import java.util.Map;

import shared.main.Actor;

/**
 * This is just to send the updates of the other game objects.  
 * @author Ty
 *
 */
public class UpdateAcMessage extends Message{
	private static final long serialVersionUID = 8699605727198430813L;
	
	public final Map<Integer,Actor> actors;
	
	public UpdateAcMessage(Map<Integer,Actor> actors){
		this.actors = actors;
	}
}
