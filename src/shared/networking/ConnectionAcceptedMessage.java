package shared.networking;

public class ConnectionAcceptedMessage extends Message{
	private static final long serialVersionUID = 550661670588691666L;
	
	public final int shipId;
	
	public ConnectionAcceptedMessage(int shipId){
		this.shipId = shipId;
	}
}
