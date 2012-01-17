package shared.main.entity.components;

import shared.main.entity.Component;
import shared.networking.UpdateMessage;

public abstract class RoleComponent extends Component {
	public RoleComponent(String type) {
		super(type);
	}

	private static final long serialVersionUID = -7571461060714873569L;

	public abstract void handleMessage(UpdateMessage updateMessage);
	
	public abstract boolean shouldSend(UpdateMessage.Destination destinationType);
}
