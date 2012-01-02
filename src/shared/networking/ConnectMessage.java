package shared.networking;

public class ConnectMessage extends Message{
	private static final long serialVersionUID = -754537038548887535L;
	
	public final String playerName;
	public final String password;
	public final String shipName;
	
	public ConnectMessage(String playerName, String password, String shipName){
		this.playerName = playerName;
		this.password = password;
		this.shipName = shipName;
	}
	
	public String toString(){
		String toReturn = "";
		
		toReturn += "CONNECT MESSAGE: ";
		toReturn += "[Name: "+playerName+"],";
		toReturn += "[Pass: "+password+"],";
		toReturn += "[ShipName: "+shipName+"],";
		
		return toReturn;
	}
}
