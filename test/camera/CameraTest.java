package camera;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import shared.math.Point;

import client.main.drawing.Camera;

public class CameraTest {
	
	@Test
	public void cameraConversionTest() throws SlickException{
		GameContainer gc = new AppGameContainer(null, 500, 500, false);
		Camera camera = new Camera(gc);
		
		System.out.println(camera.getCenter());
		
		Point point = new Point(50,50);
	}
}
