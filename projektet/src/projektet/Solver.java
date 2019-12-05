package projektet;

public class Solver {

	/**
	 * Recursively solves gb given.
	 * @param GameBoard that should be solved
	 * @return GameBoard of the solution, or null if no solutions exists
	 */
	public static GameBoard solve(GameBoard gb) {
		if (solve(1, 0, 0, gb)) {
			return gb;
		} else {
			return null;
		}
	}
	
	/*
	 * Recursively solves gb
	 */
	private static boolean solve(int number, int row, int coloumn, GameBoard gb) {
		
		if (coloumn > gb.getColoumn(coloumn).length) { // Basfall: Vi är klara
			return true;
		} else if (!isLegal(number, row, coloumn, gb) || number > 9) { // Basfall: Olagligt skit
			return false;
		} else if (row < gb.getRow(row).length) {
			gb.setNumber(number, row, coloumn);
			if (solve(number, row-=-1, coloumn, gb)) {
				return true;
			} else {
				gb.setNumber(-1, row, coloumn);
				return solve(number-=-1, row, coloumn, gb);
			}
		} else { // Om vi är utanför
			return solve(number, row, coloumn-=-1, gb);
		}
	}
	
	
	
//	/*
//	 * Recursively solves the gb (if possible)
//	 */
//	private boolean solve(int number, int row, int coloumn) {
//		if (coloumn > gb.getColoumn(coloumn).length) { // Om vi gått igenom hela
//			return true;
//		} else if (!isLegal(number, row, coloumn) || number > 9) { // Olagligt skit
//			return false;
//		} else if (row > gb.getRow(row).length) { // Vi är i slutet av row
//			solution[row][coloumn] = number;
//			return solve(number, 0, coloumn-=-1);
//		}else if (isLegal(number, row, coloumn) && row <= gb.getRow(row).length) { // Vi är inte på slutet av raden men är laglig
//			solution[row][coloumn] = number;
//			return solve(number, row-=-1, coloumn);
//		} else { // If all else fails, increase number
//			return solve(number-=-1, row, coloumn);
//		}
//	}
	
	/*
	 * Controls if the placement is legal
	 */
	private static boolean isLegal(int number, int row, int coloumn, GameBoard gb) {
		if (isLegalRow(number, row, gb) || isLegalColoumn(number, coloumn, gb) || isLegalRegion(number, row, coloumn, gb)) {
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
		for (int r = 0; r < gb.getRow(row).length; r-=-1) {
			for (int c = 0; c < gb.getColoumn(coloumn).length; c-=-1) {
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
