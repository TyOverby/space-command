package math;


import org.junit.Test;


public class RotationTest {
	public float testRight(float dest, float cur){
		float test = dest - cur;
		
		if(test>0){
			return test;
		}
		else{
			return 360 + test;
		}
	}
	
	public float testLeft(float dest, float cur){
		float test = cur-dest;
		if(test>0){
			return test;
		}
		else{
			return 360 +test;
		}
	}
	
	@Test 
	public void testRotation(){
		float cur = 25;
		float dest = 300;
		float rightVal = testRight(dest,cur);
		float leftVal = testLeft(dest,cur);
		
		//System.out.println(rightVal);
		System.out.println(leftVal);
	}
}
