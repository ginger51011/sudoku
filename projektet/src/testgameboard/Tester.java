package testgameboard;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import projektet.GameBoard;
import projektet.Solver;

public class Tester {
	GameBoard gb;
	
	
	/*
	 * Tests setNumber()
	 */
	@Test
	public final void testSetNumber() {
		gb = new GameBoard();
		gb.setNumber(1, 1, 1);
		gb.setNumber(1, 0, 5);
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
		gb = null;
	}
	
	/*
	 * Tests solve() on empty GameBoard	
	 */
	@Test
	public void testNonSolveable() {
		gb = new GameBoard();
		gb.setNumber(1, 0, 0);
		gb.setNumber(1, 1, 1);
		
		try {
			assertNull(Solver.solve(gb));
		} catch (Exception e) {
			fail("Raised exception: " + e);
		}
		gb = null;
	}
	
	/*
	 * Tests solve()
	 */
	@Test
	public void testSolve() {
		gb = new GameBoard();
		gb.setNumber(5, 1, 1);
		gb.setNumber(3, 6, 5);
		gb.setNumber(8, 1, 2);
		
		try {
			gb = Solver.solve(gb);
			if (gb == null) {
				fail("Could not solve solvable GameBoard");
			}
		} catch (Exception e) {
			fail("Raised exception: " + e);
		}
		gb = null;
	}
	
	/*
	 * Prints the GameBoard
	 */
	@Test
	public void testPrintBoard() {
		gb = new GameBoard();
		gb.setNumber(5, 1, 1);
		gb.setNumber(3, 6, 5);
		gb.setNumber(8, 1, 2);
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (gb.getValue(r, c) == -1) {
					System.out.print(" " + gb.getValue(r, c));
				} else {
				System.out.print("  " + gb.getValue(r, c)); // Space to account for -1
				}
				if (c == 8) {
					System.out.println();
				}
			}
		}
		gb = null;
	}

}
