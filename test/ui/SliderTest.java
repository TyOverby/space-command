package ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import shared.math.Vector2f;

import client.gui.Slider;
import client.gui.UIContainer;


public class SliderTest  extends BasicGame{

	private Slider slider1;
	private Slider slider2;
	private UIContainer uiCont;
	
	public SliderTest() {
		super("Slider Test");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		uiCont = new UIContainer(container);
		slider1 = new Slider(new Vector2f(50,50),new Vector2f(100,500),0.5f,5,Color.red,Color.blue);
		slider2 = new Slider(new Vector2f(60,50),new Vector2f(50,200),1f,1,Color.cyan,Color.pink);
		
		uiCont.addElement(slider1);
		uiCont.addElement(slider2);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		uiCont.draw(g);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = 
				new AppGameContainer(new SliderTest());

		app.setDisplayMode(1000, 800, false);
		app.start();
	}
}
