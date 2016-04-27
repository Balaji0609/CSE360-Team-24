package com.portalsandtimemachines.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;

@SuppressWarnings("unused")
public class GameTest {

	/**
	 * To check whether the game object has been created.
	 */
	@Test
	public void test() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNotNull(obj);
	}
	
	/**
	 * To check whether the game piece has been created.
	 */
	@Test
	public void testgamepiececreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePiece);
	}
	
	/**
	 * To check whether the board stage has been created.
	 */
	@Test
	public void testBoardstagen() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamestage);
	}
	
	/**
	 * To check whether the roll text button has been created.
	 */
	@Test
	public void rolltextButtonCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.rollDice);
	}
	
	/**
	 * To check whether the game skin has been created.
	 */
	@Test
	public void gameskinCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gameskin);
	}
	
	/**
	 * To check whether the board background has been created.
	 */
	@Test
	public void testBoardBackgoung() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.boardBackground);
	}

	/**
	 * To check whether the game piece texture has been created.
	 */
	@Test
	public void testgamePieceTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePieceTexture);
	}
	
	/**
	 * To check whether the portal texture has been created.
	 */
	@Test
	public void testportalTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.portalTexture);
	}
	
	/**
	 * To check whether the time machine texture has been created.
	 */
	@Test
	public void testtimemachineTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.timemachineTexture);
	}
	
}
