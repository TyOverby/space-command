package client.main.view.helm;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public abstract class HelmMouseClick implements MouseListener {

	private List<Integer> buttonsDown = new ArrayList<Integer>();
	
	@Override
	public void setInput(Input input) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAcceptingInput() {
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
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		buttonsDown.add(button);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		buttonsDown.remove(button);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		buttonDragged(oldx, oldy, newx, newy, buttonsDown.get(buttonsDown.size()-1));
	}
	
	public void buttonDragged(int oldx, int oldy, int newx, int newy, int button){
		
	}
}
