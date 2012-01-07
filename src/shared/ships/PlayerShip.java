package shared.ships;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.math.Vector2f;

public class PlayerShip implements Serializable{
	private static final long serialVersionUID = -5079049404420648508L;
	
	private final String name;
	private final String password;
	
	private final Ship ship;
	
	private final List<Integer> playerIdList = new ArrayList<Integer>(10);
	
	public PlayerShip(String name, String password, Ship ship){
		this.name = name;
		this.password = password;
		this.ship = ship;
	}
	
	public void update(long l){
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean testPassword(String pass){
		return this.password.equals(pass);
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public List<Integer> getPlayerList(){
		return playerIdList;
	}
	
	public void addPlayer(int playerId){
		this.playerIdList.add(playerId);
	}

	public Vector2f getPosition() {
		return ship.getPosition();
	}
}
