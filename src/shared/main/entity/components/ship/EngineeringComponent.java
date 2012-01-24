package shared.main.entity.components.ship;

import java.util.ArrayList;
import java.util.List;

import shared.networking.UpdateMessage;
import shared.networking.UpdateMessage.Destination;

public class EngineeringComponent extends RoleComponent {
	private static final long serialVersionUID = -5787645113783176529L;
	
	private List<Piece> pieces = new ArrayList<Piece>();
	
	private float energy = 1000;
	
	Piece thrusters;
	Piece warp;
	Piece manuver;

	public EngineeringComponent() {
		super("EngineeringComponent");
		// HELM
		thrusters= new Piece("Thrusters");
		pieces.add(thrusters);
		
		warp = new Piece("Warp");
		pieces.add(warp);
		
		manuver = new Piece("Manuverability");
		pieces.add(manuver);
		
		
	}
	
	public float getEnergy(){
		return this.energy;
	}
	
	public Piece getThrusters(){
		return thrusters;
	}
	public Piece getWarp(){
		return warp;
	}
	public Piece getManuver(){
		return manuver;
	}

	@Override
	public void handleMessage(UpdateMessage updateMessage) {
		// TODO Actually handle the messages		
	}

	@Override
	public boolean shouldSend(Destination destinationType) {
		if(destinationType==Destination.ENGINEERING){
			return true;
		}
		return false;
	}

	@Override
	public void update(long delta) {
		for(Piece piece:pieces){
			piece.update(delta);
			energy -= piece.getAdjustedPowerConsumption(delta);
		}
	}
}
