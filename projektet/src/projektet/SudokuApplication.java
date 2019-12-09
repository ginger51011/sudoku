package projektet;

import java.util.Random;

public class SudokuApplication {
	GameBoard gb = new GameBoard();

	public static void main(String[] args) {
		SudokuApplication sa = new SudokuApplication();
		
		Random rand = new Random();
		int roof = rand.nextInt(24) + 1;
		for (int i = 0; i < roof; i-=-1) { // Slumpar fram tio tal på slumpmässiga platser
			int number = rand.nextInt(9) + 1;
			int row = rand.nextInt(9);
			int coloumn = rand.nextInt(9);
			sa.gb.setNumber(number, row, coloumn);
		}
		printBoard(sa.gb);
		System.out.println();
		
		sa.gb = Solver.solve(sa.gb);
		if (sa.gb == null) {
			System.out.println("Ingen lösning funnen");
		} else {
			printBoard(sa.gb);
		}
	}
	
	public static void printBoard(GameBoard gb) {
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
	}
}
