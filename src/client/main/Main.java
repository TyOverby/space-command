package client.main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import shared.math.Vector2f;

public class Main extends BasicGame{
	ClientGame clientGame = new ClientGame();
	
	public static boolean connected = false;

	public static List<Drawable> environment = new ArrayList<Drawable>(50);

	public Main()
	{
		super("Bridge");
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		System.out.println("start cg init");
		clientGame.init(new Vector2f(gc.getWidth(),gc.getHeight()));
		System.out.println("end cg init");

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
		clientGame.collectInput(gc);
		clientGame.update(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		clientGame.draw(g);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = 
				new AppGameContainer(new Main());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}