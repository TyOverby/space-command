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
		updateSpeed(delta);
		updateRotation(delta);
	}
	
	public float getManuver(){
		return engineeringComponent.getManuver().getPower();
	}
	public float getThrusters(){
		return engineeringComponent.getThrusters().getPower();
	}
	
	private float powerToSpeed(float power){
		float coefficient = 200;
		return coefficient*power;
	}
	
//	private float getMaxAcceleration(){
//		return 100;
//	}
//	
	public void updateSpeed(long delta){
		float fracOfSecond = (float)delta/1000;
		
		float curSpeed = parent.getSpeed();
		float destSpeed = powerToSpeed(engineeringComponent.getThrusters().getPower());
		
		float deltaSpeed = destSpeed - curSpeed;
		float accelSpeed = deltaSpeed*fracOfSecond;
		
		parent.setSpeed(accelSpeed+curSpeed);
	}
	
	/**
	 * Sees how long it takes to go from an angle to another
	 * from the right direction.
	 * @param dest The destination angle
	 * @param cur The current angle
	 * @return The distance from the right
	 */
	public float testRight(float dest, float cur){
		float test = dest - cur;
		
		if(test>0){
			return test;
		}
		else{
			return 360 + test;
		}
	}
	
	/**
	 * Sees how long it takes to go from an angle to another
	 * from the left direction.
	 * @param dest The destination angle
	 * @param cur The current angle
	 * @return The distance from the left
	 */
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
		final int coefficient = 50; 
		
		float fracOfSecond = (float)delta/1000;
		return coefficient*power*fracOfSecond;
	}
	
	private void updateRotation(long delta){
		

		float maxAngleDif = powerToAngle(engineeringComponent.getManuver().getPower(),delta);

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
