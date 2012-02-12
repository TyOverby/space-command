package server.main;

import org.newdawn.slick.SlickException;

public class EntryPoint {

	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		ServerGame.main(args);
		client.main.Main.main(args);
	}
}
