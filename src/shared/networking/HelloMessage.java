package shared.networking;

public class HelloMessage extends Message{
	private static final long serialVersionUID = 550661670588691666L;
	
	public final int playerId;
	public final int shipActorId;
	
	public HelloMessage(int playerId,int shipActorId){
		this.playerId = playerId;
		this.shipActorId = shipActorId;
	}
}
