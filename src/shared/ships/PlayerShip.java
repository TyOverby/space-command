package shared.ships;

import java.util.ArrayList;
import java.util.List;

public class PlayerShip{
	private final String name;
	private final String password;
	
	private final Ship ship;
	
	private final List<Integer> playerIdList = new ArrayList<Integer>(10);
	
	public PlayerShip(String name, String password, Ship ship){
		this.name = name;
		this.password = password;
		this.ship = ship;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPass(){
		return this.password;
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
}
