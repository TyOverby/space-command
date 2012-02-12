package client.main.view.helm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import client.gui.ScreenClickCapture;
import client.gui.Slider;
import client.gui.UIContainer;
import client.main.ClientGame;
import client.main.view.Compas;
import client.main.view.ViewScreen;

import shared.main.entity.Entity;
import shared.main.entity.components.ship.HelmComponent;
import shared.math.Vector2f;
import shared.networking.UpdateMessage;
import static shared.networking.UpdateMessage.Type;
import static shared.networking.UpdateMessage.Destination;


public class HelmVS extends ViewScreen{
	private HelmComponent helm;
	private UIContainer uiContainer;

	private final Compas compas = new Compas(200,camera);

	public HelmVS(Vector2f dimensions,GameContainer gc, ClientGame clientGame) {
		super(dimensions,gc,clientGame);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		if(playerShip!=null){
			helm = (HelmComponent) playerShip.getComponent(HelmComponent.class);
		}

		if(helm != null){
			camera.setPosition(helm.getCurPos());
		}			
	}

	@Override
	public void setupInput(){
		uiContainer = new UIContainer(gc);
		
		ScreenClickCapture scc = new ScreenClickCapture(new Vector2f(gc.getWidth(),gc.getHeight())) {
			@Override
			public void handleClick(int button, float x, float y, int clickCount) {
				 System.out.println(button+" "+x+" "+y+" "+clickCount);

                 Vector2f mouseClick = new Vector2f(x,y);
                 Vector2f shipPos = camera.globalToScreen(helm.getCurPos());
                 Vector2f diff = mouseClick.minus(shipPos);

                 clientGame.forWardInput(new UpdateMessage(Destination.HELM, Type.ANGLE, diff.getTheta()));			
			}
		};
		
		Color outerColor = new Color(255,255,255,50);
		Color innerColor = new Color(255,255,255,50);
		
		Slider slider1 = new Slider(new Vector2f(50,50),new Vector2f(50,200),1f,5, outerColor, innerColor);
		
		uiContainer.addElement(scc);
		uiContainer.addElement(slider1);
	}

	@Override
	public Collection<UpdateMessage> handleInput(Input curInput) {
		List<UpdateMessage> toReturn = new ArrayList<UpdateMessage>(5);

		if(curInput.isKeyPressed(Input.KEY_SPACE)){
			toReturn.add(new UpdateMessage(Destination.HELM, Type.ANGLE, 180)); 
		}

		return toReturn;
	}

	@Override 
	public void teardownInput(){
		this.gc.getInput().removeAllListeners();
	}

	@Override
	public void drawPre(Graphics g) {
		// Draws the arc of movement
		if(playerShip!=null && helm!=null){
			Vector2f position = camera.globalToScreen(playerShip.getPosition());

			float curRot = helm.getCurRotation();
			float destRot = helm.getDestinationRotation();

			float right = helm.testRight(destRot, curRot);
			float left = helm.testLeft(destRot, curRot);

			if(right<left){
				compas.drawArc(curRot, destRot,right, position, g);
			}
			else{
				compas.drawArc(destRot, curRot,left, position, g);
			}
		}		
	}

	@Override
	public void drawEnv(Entity env,Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawActor(Entity actor,Graphics g) {
		actor.render(graphics, camera, 1);		
	}

	@Override
	public void drawFinal(Graphics g) {
		if(clientGame.getPlayerShip()!=null){
			Vector2f position = camera.globalToScreen(clientGame.getPlayerShip().getPosition());
			compas.draw(position,g);
		}
	}

	@Override
	public void drawGui(Graphics g) {
		uiContainer.draw(g);
	}
}
