package shared.networking;

public class UpdateMessage extends Message{
	private static final long serialVersionUID = 8699605727198430813L;
	
	public final Action action;
	public final Object data;
	
	public UpdateMessage(Action action, Object data){
		this.action = action;
		this.data = data;
	}
}
