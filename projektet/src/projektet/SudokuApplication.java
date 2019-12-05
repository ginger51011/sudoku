package projektet;

public class SudokuApplication {
	GameBoard gb = new GameBoard();
	
	public static void main(String[] args) {
		SudokuApplication sa = new SudokuApplication();
		sa.gb.setNumber(1, 5, 6);
		sa.gb.setNumber(3, 2, 2);
		sa.gb.setNumber(2, 1, 1);
		sa.gb.setNumber(3, 6, 1);
		sa.gb = Solver.solve(sa.gb);
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (sa.gb.getValue(r, c) == -1) {
					System.out.print(" ");
				}
				System.out.print(" " + sa.gb.getValue(r, c));
				if (c == 7) {
					System.out.println();
				}
			}
		}
	}
}
