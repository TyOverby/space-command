package camera;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.math.Vector2f;

import client.main.view.Camera;

public class CameraTest {
	
	@Test 
	public void centerTest(){
		Vector2f cameraSize = new Vector2f(100,100);
		Camera camera = new Camera(cameraSize);
		
		Vector2f expected = new Vector2f(50,50);
		testEquals(expected,camera.getCenter());
	}
	
	@Test
	public void backAndForthTest(){
		Vector2f cameraSize = new Vector2f(50,50);
		Camera camera = new Camera(cameraSize);
		
		Vector2f globalPoint = new Vector2f(5,30);
		testEquals(globalPoint,camera.ScreenToGlobal(camera.globalToScreen(globalPoint)));
	}
	
	public void testEquals(Vector2f one, Vector2f two){
		assertEquals(one.getX(), two.getX(),0.01f);
		assertEquals(one.getY(), two.getY(),0.01f);
	}
}
