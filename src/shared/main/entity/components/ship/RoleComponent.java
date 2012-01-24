package shared.main.entity.components.ship;

import shared.main.entity.Component;
import shared.networking.UpdateMessage;

public abstract class RoleComponent extends Component {
	private static final long serialVersionUID = -7571461060714873569L;
	
	long engId = -1;
	EngineeringComponent engineeringComponent;
	
	public RoleComponent(String type) {
		super(type);
	}

	public abstract void handleMessage(UpdateMessage updateMessage);
	
	public abstract boolean shouldSend(UpdateMessage.Destination destinationType);
	
	public void setEngId(long id){
		this.engId = id;
		engineeringComponent = (EngineeringComponent) parent.getComponent(id);
	}
}
