package shared.main.entity.components;

import shared.networking.UpdateMessage;
import shared.networking.UpdateMessage.Destination;

public class HelmComponent extends RoleComponent{
	private static final long serialVersionUID = 4149466898209160333L;
	
	private float destinationAngle = 0;
	
	public HelmComponent(float rotation) {
		super("HelmComponent ");
		destinationAngle = rotation;
	}


	@Override
	public boolean shouldSend(Destination destinationType) {
		if(destinationType==UpdateMessage.Destination.HELM){
			return true;
		}
		return false;
	}
	@Override
	public void handleMessage(UpdateMessage updateMessage) {
		if(updateMessage.destination==UpdateMessage.Destination.HELM){
			switch(updateMessage.type){
				case ANGLE:
					destinationAngle = (float) updateMessage.payload;
					break;
				case FORWARD:
					//go forward
					break;
				case BACKWARD:
					//go backward
					break;
			}
		}
		else{
			System.err.println("Somehow this message got to helm :( \n"+updateMessage.toString());
		}
	}

	@Override
	public void update(long delta) {
		updateRotation(delta);
	}
	
	private void updateRotation(long delta){
		float fracOfSecond = (float)delta/1000;
		
		float angleDiff = parent.getRotation()-destinationAngle;
		float deltaAngle = (angleDiff/10)*fracOfSecond;
		System.out.println("CurAngle:\t\t"+parent.getRotation());
		System.out.println("DestAngle:\t\t"+destinationAngle);
		
		parent.setRotation(parent.getRotation()-deltaAngle);
	}
}
