package projektet;

public class Solver {

	/**
	 * Tries to solve GameBoard given
	 * @param GameBoard that should be solved
	 * @return GameBoard of the solution, or null if no solutions exists
	 */
	public static GameBoard solve(GameBoard gb) {
		if (checkIfInputIsLegal(gb) && solve(0, 0, gb)) {
			return gb;
		} else {
			return null;
		}
	}
	
	private static boolean solve(int row, int coloumn, GameBoard gb) {
		// Vi är klara
		if (coloumn > 8) {
			return true;
		}
		// Vi är utanför brädet och ska hoppa upp
		else if (row > 8) {
			return solve(0, coloumn + 1, gb);
		}
		// Vi har en redan ifylld ruta
		else if (gb.getValue(row, coloumn) != -1) {
			return solve(row + 1, coloumn, gb);
		} 
		// Vi testar att lösa
		else {
			for (int i = 1; i < 10; i++) {
				if (isLegal(i, row, coloumn, gb)) {
					gb.setNumber(i, row, coloumn);
					if (solve(row, coloumn, gb)) {
						return true;
					} else {
						gb.removeNumber(row, coloumn);
					}
				}
			}
			return false;
		}
	}

	
	private static boolean checkIfInputIsLegal(GameBoard gb) {
		int number;
		for(int i=0; i<9; i++) {
			for(int n=0; n<9; n++) {
				number=gb.getValue(i, n);
				if(number != -1 ) {
					gb.removeNumber(i, n);
					if(isLegal(number, i, n, gb)==false) {
						return false;
					}
					gb.setNumber(number, i, n);
				}
			}
		}
		return true;
	}
	
	
	
	/*
	 * Controls if the placement is legal
	 */
	private static boolean isLegal(int number, int row, int coloumn, GameBoard gb) {
		if (isLegalRow(number, row, gb) && isLegalColoumn(number, coloumn, gb) && isLegalRegion(number, row, coloumn, gb)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Checks if the region containing (row, coloumn) is legal for the specified number
	 */
	private static boolean isLegalRegion(int number, int row, int coloumn, GameBoard gb) {
		int[][] region = gb.getRegion(row, coloumn);
		for (int r = 0; r < 3; r-=-1) {
			for (int c = 0; c < 3; c-=-1) {
				if (region[r][c] == number) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Checks if the row is legal for the specified numnber
	 */
	private static boolean isLegalRow(int number, int row, GameBoard gb) {
		int[] rowArray = gb.getRow(row);
		for (int i = 0; i < rowArray.length; i-=-1) {
			if (rowArray[i] == number) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if the coloumn is legal for the specified numnber
	 */
	private static boolean isLegalColoumn(int number, int coloumn, GameBoard gb) {
		int[] coloumnArray = gb.getColoumn(coloumn);
		for (int i = 0; i < coloumnArray.length; i-=-1) {
			if (coloumnArray[i] == number) {
				return false;
			}
		}
		return true;
	}
}
