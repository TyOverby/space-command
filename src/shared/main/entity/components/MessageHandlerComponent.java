package shared.main.entity.components;

import shared.main.entity.Component;
import shared.networking.Message;
import shared.networking.UpdateMessage;

public class MessageHandlerComponent extends Component{
	private static final long serialVersionUID = 5630145720457307153L;

	RoleComponent[] roleComponents;

	public MessageHandlerComponent(RoleComponent... roleComponents) {
		super("MessageHandlerComponent");

		this.roleComponents = roleComponents;
	}

	public void disperseMessage(Message updateMessage){
		if(updateMessage instanceof UpdateMessage){
			for(RoleComponent rc:roleComponents){
				if(rc.shouldSend(((UpdateMessage)updateMessage).destination)){
					rc.handleMessage((UpdateMessage)updateMessage);
				}
			}
		}
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub

	}

}
