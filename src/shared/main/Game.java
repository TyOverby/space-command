package shared.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import client.main.drawing.Camera;

import shared.ships.PlayerShip;

public abstract class Game {
	protected  Map<Integer,Actor> actors = new HashMap<Integer,Actor>();
	protected Map<Integer,PlayerShip> playerShips = new HashMap<Integer,PlayerShip>();
	
	public abstract void init();
	
	public abstract void update(long delta);
	
	public abstract void draw(GameContainer gc, Graphics g, Camera camera);
}
