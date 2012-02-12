package client.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

import shared.math.Vector2f;

public class UIContainer implements InputListener{
	
	public List<UIElement> uiElements = new ArrayList<UIElement>();
	
	public UIContainer(GameContainer gc){
		gc.getInput().addListener(this);
	}

	public void draw(Graphics g){
		for(UIElement uie:uiElements){
			uie.draw(g);
		}
	}
	
	public void addElement(UIElement element){
		this.uiElements.add(element);
	}	
	
	public boolean hits(UIElement uie, Vector2f point){
		Vector2f elementPos = uie.getPosition();
		Vector2f elementSize = uie.getSize();
		
		if(point.getX()>elementPos.getX() && point.getY()>elementPos.getY()){
			if(point.getX()<elementPos.getX()+elementSize.getWidth() && point.getY()<elementPos.getY()+elementSize.getHeight()){
				return true;
			}
		}
		return false;
	}
	public boolean hits(UIElement uie, int x, int y){
		return hits(uie,new Vector2f(x,y));
	}	
	
	
	
	/*
	 * OH SHIT
	 */	
	
	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {		
		for(int i=uiElements.size()-1;i>=0;i--){
			UIElement uie = uiElements.get(i);
			
			float xPos = uie.getPosition().getX();
			float yPos = uie.getPosition().getY();
			
			if(hits(uie,x,y)){
				uie.handleClick(button, x-xPos, y-yPos, clickCount);
				return;
			}
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for(int i=uiElements.size()-1;i>=0;i--){
			UIElement uie = uiElements.get(i);
			
			float xPos = uie.getPosition().getX();
			float yPos = uie.getPosition().getY();
			
			if(hits(uie,x,y)){
				uie.handleRelease(x-xPos, y-yPos);
				return;
			}
		}	
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		for(int i=uiElements.size()-1;i>=0;i--){
			UIElement uie = uiElements.get(i);
			
			float xPos = uie.getPosition().getX();
			float yPos = uie.getPosition().getY();
			
			if(hits(uie,newx,newy)||hits(uie,oldx,oldy)){
				uie.handleDrag(oldx-xPos,oldy-yPos,newx-xPos,newy-yPos);
				return;
			}
		}
	}

	@Override
	public void setInput(Input input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub
		
	}
}
