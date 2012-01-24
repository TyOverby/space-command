package shared.networking;


public class UpdateMessage extends Message{
	private static final long serialVersionUID = 3608091701857581922L;

	public enum Destination{
		HELM, ENGINEERING,
	}
	public enum Type{
		// HELM
		FORWARD,
		BACKWARD,
		ANGLE,
		
		// ENGINEERING
		
		// OTHER DESTINATIONS
	}
	
	public final Destination destination;
	public final Type type;
	public final double payload;
	
	public UpdateMessage(Destination destination,Type type,double payload){
		this.destination = destination;
		this.type = type;
		this.payload = payload;
	}
	
	public String toString(){
		return "[UpdateMessage destination: "+destination+", type: "+type+", payload: "+payload;
	}
}
