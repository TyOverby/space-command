package game;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.main.entity.components.ship.Piece;

public class PieceTest {

	@Test 
	public void testCreation(){
		Piece newPiece = new Piece("newPiece");
		
		assertEquals(0.5, newPiece.getPower(),0.01);
		assertEquals(0, newPiece.getCoolant(),0.01);
		assertEquals(0,newPiece.getAdjustedPowerConsumption(10),0.01);
	}
	
	@Test
	public void testOverload(){
		Piece newPiece = new Piece("newPiece");
		newPiece.setPower(75);
		newPiece.update(1000);
		
		System.out.println(newPiece.getOverheat());
	}
}
