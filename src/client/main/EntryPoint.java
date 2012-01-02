package client.main;
 
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import client.main.drawing.Camera;

import shared.ships.Ship;
 
public class EntryPoint extends BasicGame{
	public boolean connected = false;
	
	public static List<Drawable> environment = new ArrayList<Drawable>(50);
	public static List<Ship> ships = new ArrayList<Ship>(50);
	
	public static Ship playerShip;
	public static Camera playerCamera;
	
    public EntryPoint()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	
    	// If we haven't connected yet, just chill.
    	while (!connected){
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException 
    {
 
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException 
    {
    	
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new EntryPoint());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}