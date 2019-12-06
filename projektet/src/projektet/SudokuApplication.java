package projektet;

public class SudokuApplication {
	GameBoard gb = new GameBoard();

	public static void main(String[] args) {
		SudokuApplication sa = new SudokuApplication();
		sa.gb.setNumber(3, 1, 1);
		sa.gb.setNumber(9, 3, 7);
		sa.gb = Solver.solve(sa.gb);
		if (sa.gb == null) {
			System.out.println("Ingen lösning funnen");
		} else {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					if (sa.gb.getValue(r, c) == -1) {
						System.out.print(" ");
					}
					System.out.print(" " + sa.gb.getValue(r, c));
					if (c == 8) {
						System.out.println();
					}
				}
			}
		}
	}
}
