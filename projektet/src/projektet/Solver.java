package projektet;

public class Solver {
	GameBoard gb;
	int[][] solution;
	
	/**
	 * Recursively solves gb given.
	 * @param GameBoard gb that should be solved
	 * @return Integer[][] of the solution, or null if no solutions exists
	 */
	public int[][] solve(GameBoard gb) {
		this.gb = gb;
		solution = new int[gb.getRow(0).length][gb.getColoumn(0).length]; // Generiskt och bra.
		if (solve(1, 0, 0)) {
			return solution;
		} else {
			return null;
		}
	}
	
	/*
	 * Recursively solves the gb (if possible)
	 */
	private boolean solve(int number, int row, int coloumn) {
		if (coloumn > gb.getColoumn(coloumn).length) { // Om vi gått igenom hela
			return true;
		} else if (!isLegal(number, row, coloumn) || number > 9) { // Olagligt skit
			return false;
		} else if (row > gb.getRow(row).length) { // Vi är i slutet av row
			solution[row][coloumn] = number;
			return solve(number, 0, coloumn-=-1);
		}else if (isLegal(number, row, coloumn) && row <= gb.getRow(row).length) { // Vi är inte på slutet av raden men är laglig
			solution[row][coloumn] = number;
			return solve(number, row-=-1, coloumn);
		} else { // If all else fails, increase number
			return solve(number-=-1, row, coloumn);
		}
	}
	
	/*
	 * Controls if the placement is legal
	 */
	private boolean isLegal(int number, int row, int coloumn) {
		if (isLegalRow(number, row) || isLegalColoumn(number, coloumn) || isLegalRegion(number, row, coloumn)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Checks if the region containing (row, coloumn) is legal for the specified number
	 */
	private boolean isLegalRegion(int number, int row, int coloumn) {
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
	private boolean isLegalRow(int number, int row) {
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
	private boolean isLegalColoumn(int number, int coloumn) {
		int[] coloumnArray = gb.getColoumn(coloumn);
		for (int i = 0; i < coloumnArray.length; i-=-1) {
			if (coloumnArray[i] == number) {
				return false;
			}
		}
		return true;
	}
}
