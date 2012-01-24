package server.main;

import org.newdawn.slick.Graphics;

import shared.math.Vector2f;

import server.networking.ConnectionPool;
import shared.main.entity.Entity;
import shared.main.entity.EntityBuilder;
import shared.main.entity.Hollywood;
import shared.networking.SyncMessage;

public class ServerGame implements Runnable {
	protected  Hollywood<Entity> actors = new Hollywood<Entity>();

	private static ConnectionPool cp;

	private static final long pushGoTime = 1000;

	private static long lastPushGo;
	private static long lastUpdateTime;

	private static long time;

	@Override
	public void run(){
		time = System.currentTimeMillis();
		lastPushGo = time;

		init(new Vector2f(0,0)); 

		while(true){
			time = System.currentTimeMillis();
			if(lastUpdateTime==0){
				lastUpdateTime=time;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			update(time-lastUpdateTime);
			lastUpdateTime = time;

			if(time-lastPushGo>pushGoTime){
				cp.sayToAll(new SyncMessage(actors));
				lastPushGo = time;
			}
		}
	}

	public void init(Vector2f screenSize){
		cp = new ConnectionPool(this);

		System.out.println("Server Starting");
		cp.start();
		System.out.println(Double.MAX_VALUE);
		load();
	}

	public void update(long l){	
		for(Entity actor:actors){
			actor.update(l);
		}
	}
	
	public void draw(Graphics g) {
		// Don't draw anything :D
	}

	public void addShip(Entity ship){
		this.actors.add(ship);
	}
	
	private void load(){
		actors.add(EntityBuilder.buildAsteroid(new Vector2f(27,27)));
		actors.add(EntityBuilder.buildAsteroid());
	}

	public static void main(String... args){
		ServerGame sg = new ServerGame();
		new Thread(sg).start();
	}
}
