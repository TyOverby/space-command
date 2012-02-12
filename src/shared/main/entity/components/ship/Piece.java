package shared.main.entity.components.ship;

import java.io.Serializable;

public class Piece implements Serializable{
	private static final long serialVersionUID = 2837382842638794589L;
	
	public final String name;

	final float BASE_POWER = 0.5f;
	final float MAX_POWER = 1f;
	final float MAX_OVERHEAT = 1f;
	final float MAX_DAMAGE = 1f;

	float damage = 0;

	float power = BASE_POWER;
	float coolant = 0;

	float overheat = 0;

	public Piece(String name){
		this.name = name;
	}

	public void update(long delta){
		float fracOfSecond = (float)delta/1000; 

		// If we have more power than we should for the amount of coolant,
		if(power-BASE_POWER>coolant){
			overheat += fracOfSecond*(power-BASE_POWER-coolant);
			// reset overheat if it gets too hot.
			if(overheat>MAX_OVERHEAT){
				overheat = MAX_OVERHEAT;
			}
			if(overheat<0){
				overheat = 0;
			}
		}

		// If it is overheating
		if(overheat >=MAX_OVERHEAT){
			// give them 2 secs to drop it off before it totally breaks.
			damage += 5*fracOfSecond;
			if(damage>MAX_DAMAGE){
				damage = MAX_DAMAGE;
			}
		}
	}
	
	public float getAdjustedPowerConsumption(long delta){
		float fracOfSecond = (float)delta/1000; 
		final float energyPerSec = fracOfSecond * (1/100);
		return power*energyPerSec;
	}
	
	public float getOverheat(){
		return this.overheat/this.MAX_OVERHEAT;
	}

	public float getPower(){
		return Math.min((power-damage)/MAX_POWER,1f);
	}
	public void setPower(float power){
		this.power = Math.min(power,MAX_POWER);
	}

	public float getCoolant(){
		return this.coolant;
	}
	public void setCoolant(float coolant){
		this.coolant = coolant;
	}
}