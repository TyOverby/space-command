package shared.main;

import org.newdawn.slick.Graphics;

import shared.main.entity.Entity;
import shared.main.entity.Hollywood;
import shared.math.Vector2f;

public abstract class Game {
	protected  Hollywood<Entity> actors = new Hollywood<Entity>();
	
	public abstract void init(Vector2f screenDims);
	
	public abstract void update(long delta);
	
	public abstract void draw(Graphics g);
}
