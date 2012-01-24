package shared.main.entity.components.ship;

import shared.math.Vector2f;
import shared.networking.UpdateMessage;
import shared.networking.UpdateMessage.Destination;

public class HelmComponent extends RoleComponent{
	private static final long serialVersionUID = 4149466898209160333L;

	private float destinationAngle = 0;

	public HelmComponent(){
		super("HelmComponent");
	}
	
	public HelmComponent(float rotation) {
		super("HelmComponent");
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
	
	public float testRight(float dest, float cur){
		float test = dest - cur;
		
		if(test>0){
			return test;
		}
		else{
			return 360 + test;
		}
	}
	
	public float testLeft(float dest, float cur){
		float test = cur-dest;
		if(test>0){
			return test;
		}
		else{
			return 360 +test;
		}
	}
	
	private float powerToAngle(float power,float delta){
		float fracOfSecond = (float)delta/1000;
		return 50*power*fracOfSecond;
	}
	
	private void updateRotation(long delta){
		

		float maxAngleDif = powerToAngle(engineeringComponent.getManuver().getAdjustedPower(),delta);

		float curRot = getCurRotation();
		float destRot = getDestinationRotation();
		
		if(testRight(destRot,curRot)<testLeft(destRot,curRot)){
			float moveVal = Math.min(maxAngleDif, testRight(destRot,curRot));
			
			parent.setRotation((curRot+moveVal)%360);
		}
		else{
			float moveVal = Math.min(maxAngleDif, testRight(destRot,curRot));
			
			parent.setRotation(((curRot-moveVal)+360)%360);
		}
	}

	public float getCurRotation(){
		return parent.getRotation();
	}
	public float getDestinationRotation(){
		return this.destinationAngle;
	}
	public Vector2f getCurPos(){
		return this.parent.getPosition();
	}
}
