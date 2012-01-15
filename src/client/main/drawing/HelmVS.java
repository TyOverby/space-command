package client.main.drawing;

import shared.main.entity.Entity;
import shared.math.Vector2f;

public class HelmVS extends ViewScreen{

	public HelmVS(Vector2f dimensions) {
		super(dimensions);
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPre() {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawEnv(Entity env) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawActor(Entity actor) {
		actor.render(graphics, camera, 1);		
	}

	@Override
	public void drawFinal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGui() {
		// TODO Auto-generated method stub
		
	}
	
}
