package shared.networking;

import java.util.Map;

import shared.ships.PlayerShip;

public class UpdatePsMessage extends Message{
	private static final long serialVersionUID = 2968769876092925903L;

	public final Map<Integer,PlayerShip> playerShips;
	
	public UpdatePsMessage(Map<Integer,PlayerShip> ps){
		this.playerShips = ps;
	}
}
