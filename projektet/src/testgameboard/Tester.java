package testgameboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import projektet.GameBoard;

class Tester {
	GameBoard gb;
	
	@Before
	public void setUp() {
		gb = new GameBoard();
	}
	
	@After
	public void tearDown() {
		gb = null;
	}
	
	/*
	 * Tests setNumber()
	 */
	@Test
	public final GameBoard testSetNumber() {
		gb.setNumber(5, 1, 1);
		gb.setNumber(3, 6, 5);
		try {
			gb.setNumber(999, 2, 2);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Lyckat test
		}
		try {
			gb.setNumber(5, 999, 999);
			fail("Should raise ArrayIndexOutOfBoundsException");
		} catch (ArrayIndexOutOfBoundsException e) {
			// Lyckat test
		}
		return gb;
	}
	
	/*
	 * Prints the GameBoard
	 */
	@Test
	public void testPrintBoard() {
		gb = testSetNumber();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (gb.getValue(r, c) == -1) {
					System.out.print(" ");
				}
				System.out.print(" " + gb.getValue(r, c));
				if (c == 7) {
					System.out.println();
				}
			}
		}
	}

}
