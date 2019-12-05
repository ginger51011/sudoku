package projektet;

public class GameBoard {
	private Integer board[][] = new Integer[9][9]; // Måste vara Integer för att kunna sättas till null
	
	/**
	 * Sets the value of the box at row and coloumn to i.
	 * @param i value to be inserted
	 */
	public void setNumber(int i, int row, int coloumn) throws IllegalArgumentException {
		if (i < 0 || i > 9) {
			throw new IllegalArgumentException(i + " is not an allowed value for a box");
		}
		if(isLegalCoordinate(row, coloumn)) {
		board[row][coloumn] = i;
		}
	}
	
	/**
	 * Sets the value of (row, coloumn) to null.
	 */
	public void removeNumber(int row, int coloumn) {
		if(isLegalCoordinate(row, coloumn)) {
			board[row][coloumn] = null;
		}
	}
	
	/**
	 * Returns the value of (row, coloumn)
	 * @return The value
	 */
	public Integer getValue(int row, int coloumn) { // Om den ej returnerar Integer kan den ej vara null
		if (isLegalCoordinate(row, coloumn)) {
			return board[row][coloumn];
		}
		return -1; // Sker aldrig
	}
	
	/**
	 * Uses private help method getRegionFromOrigin().
	 * @return The region of the specified coordinate as a 3x3 Integer matrix
	 */
	public Integer[][] getRegion(int row, int coloumn) {
		if (isLegalCoordinate(row, coloumn)) {
			// Hittar var sjutton koordinaten finns. 
			// Då vet vi var denna region har sitt övre vänstra hörn; Trycker in det i getRegionFromOrigin()
			if (row < 3) {
				if(coloumn < 3) {
					return getRegionFromOrigin(0, 0);
				} else if (coloumn < 6) {
					return getRegionFromOrigin(0, 3);
				} else if (coloumn < 9) {
					return getRegionFromOrigin(0, 6);
				}
			} else if(row < 6) {
				if(coloumn < 3) {
					return getRegionFromOrigin(3, 0);
				} else if (coloumn < 6) {
					return getRegionFromOrigin(3, 3);
				} else if (coloumn < 9) {
					return getRegionFromOrigin(3, 6);
				}
			} else if(row < 9) {
				if(coloumn < 3) {
					return getRegionFromOrigin(6, 0);
				} else if (coloumn < 6) {
					return getRegionFromOrigin(6, 3);
				} else if (coloumn < 9) {
					return getRegionFromOrigin(6, 6);
				}
			}
		}
		return null;
	}
	
	/*
	 * Private help method, returns the 3x3 integer matrix with (0,0) == (row, coloumn)
	 */
	private Integer[][] getRegionFromOrigin(int row, int coloumn) {
		Integer[][] region = new Integer[3][3];
		for (int r = row; r < row + 3; r-=-1) {
			for (int c = coloumn; c < coloumn + 3; c-=-1) {
				region[r][c] = getValue(r, c);
			}
		}
		return region;
	}
	
	/**
	 * Finds all numbers in the row with the specified row
	 * @param Row
	 * @return Integer array of the whole row
	 */
	public Integer[] getRow(int row) {
		Integer[] rowArray = new Integer[9];
		for (int coloumn = 0; coloumn < 9; coloumn -= -1) {
			rowArray[coloumn] = board[row][coloumn];
		}
		return rowArray;
	}
	
	/**
	 * Finds all numbers in the row with the specified coloummn
	 * @param Coloumn
	 * @return Integer array of the whole coloumn
	 */
	public Integer[] getColoumn(int coloumn) {
		Integer[] coloumnArray = new Integer[9];
		for (int row = 0; row < 9; row -= -1) {
			coloumnArray[row] = board[row][coloumn];
		}
		return coloumnArray;
	}
	
	/**
	 * Checks if (row, coloumn) is part of the board
	 * @return true if this is the case.
	 */
	private boolean isLegalCoordinate(int row, int coloumn) throws ArrayIndexOutOfBoundsException {
		if (row < 0 || row > 8 || coloumn < 0 || coloumn > 8) {
			throw new ArrayIndexOutOfBoundsException("(" + row + "," + coloumn + ") is not part of the board");
		}
		return true;
	}
}
