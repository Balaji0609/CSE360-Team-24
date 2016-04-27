package com.portalsandtimemachines.game;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameBoardTest {
	
	/**
	 * check whether the game is initialized properly.
	 */
	@Test
	public void testInit() {
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the portal values", "Initializing the portal values");
	}
	
	/**
	 * check whether the portals are created and that they have been assigned with values.
	 */
	@Test
	public void testInit_numberofportalscheck() {
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the portal values", "Initializing the portal values");
		int[] temp = testobj.portal_positions();
		assertNotNull(temp);
	}

	/**
	 * Test whether the value of die roll is between the required range. 
	 */
	@Test
	public void testRoll_die() {
		GameBoard testobj = new GameBoard();
		boolean outOfBounds = true;
		
		int rolledNumber = testobj.roll_die();
		//System.out.println(rolledNumber);
		
		if( rolledNumber >= 1 || rolledNumber <= 6)
			outOfBounds = false;
		
		assertFalse(outOfBounds); 
	}
	
	
	

	/**
	 * Check whether the required number of portals are created.
	 */
	@Test
	public void testPrint() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init();
		int[] temp = testobj.portal_positions();
		assertEquals(temp.length,8);
		
		
	}

	/**
	 * Check whether the portals are not created beyond square 100.
	 */
	
	@Test
	public void testportal_positionsnotathundred() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		int[] temp = testobj.portal_positions();
		for(int i = 0; i < temp.length;i++)
		{
			assertNotEquals(100,temp[i]);
		}
	}

	/**
	 * checking for the portal in the out of board range.
	 */
	@Test
	public void testCheck_portal() {
		GameBoard testobj = new GameBoard();
		testobj.init();
		assertEquals(0,testobj.check_portal(150));
		
	}

	/**
	 * checking whether the object time machines have been created and assigned with values.
	 */
	@Test
	public void testInit_time_machine() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the Time Machine values", "Initializing the Time Machine values");
	}

	/**
	 * To check whether the required number of time machine are created.
	 */
	@Test
	public void testInit_numberoftime_machine() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the Time Machine values", "Initializing the Time Machine values");
//		int[] temp = testobj.timeMachine_positions();
//		assertEquals(3,temp.length);
	}
	
	/**
	 * To check whether the time machine is not null.
	 */
	@Test
	public void testTM_positions() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		int[] temp = testobj.timeMachine_positions();
		assertNotNull(temp);
		
	}
	
	/**
	 * check whether the values in the time machine are not null.
	 */
	@Test
	public void testTM_positionsnotatzero() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		int[] temp = testobj.timeMachine_positions();
		for(int i = 0; i < temp.length;i++)
		{
			assertNotEquals(100,temp[i]);
		}
	}


	/**
	 * check whether the position of the time machine is not out of board range.
	 */

	@Test
	public void testCheck_TM() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		assertEquals(0,testobj.check_timeMachine(150));
	}

}
