package projektet;

public class SudokuApplication {
	GameBoard gb = new GameBoard();
	
	public static void main(String[] args) {
		SudokuApplication sa = new SudokuApplication();
		sa.gb.setNumber(1, 5, 6);
		sa.gb.setNumber(3, 2, 2);
		sa.gb.setNumber(2, 1, 1);
	}
}
