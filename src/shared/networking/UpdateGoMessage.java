package shared.networking;

import java.util.Map;

import shared.main.GameObject;

/**
 * This is just to send the updates of the other game objects.  
 * @author Ty
 *
 */
public class UpdateGoMessage extends Message{
	private static final long serialVersionUID = 8699605727198430813L;
	
	public final Map<Integer,GameObject> gameObjects;
	
	public UpdateGoMessage(Map<Integer,GameObject> gameObjects){
		this.gameObjects = gameObjects;
	}
}
