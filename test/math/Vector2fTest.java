package math;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.math.Vector2f;

public class Vector2fTest {

	@Test 
	public void testPolarConstructor(){
		Vector2f polar;
		Vector2f cartesian;
		
		polar = new Vector2f(0);
		cartesian = new Vector2f(1,0);
			testEquals(cartesian,polar);
		
		polar = new Vector2f(180);
		cartesian = new Vector2f(-1,0);
			testEquals(cartesian,polar);
		
		polar = new Vector2f(90);
		cartesian = new Vector2f(0,1);
			testEquals(cartesian,polar);	
		
		polar = new Vector2f(270);
		cartesian = new Vector2f(0,-1);
			testEquals(cartesian,polar);
		
		polar = new Vector2f(45);
		System.out.println(polar);
		polar.setMagnitude((float) Math.sqrt(2));
		System.out.println(polar);
		cartesian = new Vector2f(1,1);
			testEquals(cartesian,polar);
	}
	
	@Test
	public void testGetAngle(){
		Vector2f cartesian;
		
		cartesian = new Vector2f(0f,0f);
			assertEquals(0, cartesian.getTheta(),0.01);
		
		cartesian = new Vector2f(0f,5f);
			assertEquals(90,cartesian.getTheta(),0.01);
		
		cartesian = new Vector2f(-5f,0f);
			assertEquals(180,cartesian.getTheta(),0.01);
		
		cartesian = new Vector2f(0f,-5f);
			assertEquals(270,cartesian.getTheta(),0.01);
	}
	
	@Test
	public void testNormalize(){
		Vector2f cartesianOne;
		Vector2f cartesianTwo;
		
		cartesianOne = new Vector2f(5f,30f);
		cartesianTwo = new Vector2f(5f,30f);
		cartesianOne.normalize();
			assertEquals(1,cartesianOne.getMagnitude(),0.01);
			assertEquals(cartesianOne.getTheta(),cartesianTwo.getTheta(),0.01);
	}
	
	@Test
	public void testTimes(){
		Vector2f one = new Vector2f(5f,5f);
		Vector2f two = new Vector2f(2f,2f);
		Vector2f test= new Vector2f(10f,10f);
			testEquals(test,one.times(two));
		
		one = new Vector2f(10f,10f);
		float scalar = 4f;
		test = new Vector2f(40f,40f);
			testEquals(test,one.times(scalar));
	}
	
	@Test 
	public void testDivide(){
		
	}
	
	public void testEquals(Vector2f one, Vector2f two){
		assertEquals(one.getX(), two.getX(),0.01f);
		assertEquals(one.getY(), two.getY(),0.01f);
	}
}
