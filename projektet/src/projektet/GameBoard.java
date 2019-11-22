package projektet;

public class GameBoard {
	private Integer board[][] = new Integer[9][9]; // Måste vara Integer för att kunna sättas till null
	
	/*
	 * Sets the value of the box at row and coloumn to i.
	 */
	public void setNumber(int i, int row, int coloumn) throws IllegalArgumentException {
		if (i < 0 || i > 9) {
			throw new IllegalArgumentException(i + " is not an allowed value for a box");
		}
		if(isLegalCoordinate(row, coloumn)) {
		board[row][coloumn] = i;
		}
	}
	
	/*
	 * Sets the value of (row, coloumn) to null.
	 */
	public void removeNumber(int row, int coloumn) {
		if(isLegalCoordinate(row, coloumn)) {
			board[row][coloumn] = null;
		}
	}
	
	/*
	 * Returns the value of (row, coloumn)
	 */
	public Integer getValue(int row, int coloumn) { // Om den ej returnerar Integer kan den ej vara null
		if (isLegalCoordinate(row, coloumn)) {
			return board[row][coloumn];
		}
		return -1; // Sker aldrig
	}
	
	/* 
	 * Returns the region of the specified coordinate as a 3x3 Integer matrix
	 * Uses private help method getRegionFromOrigin().
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
	 * Privat help method, returns the 3x3 integer matrix with (0,0) == (row, coloumn)
	 */
	private Integer[][] getRegionFromOrigin(int row, int coloumn) {
		Integer[][] region = new Integer[3][3];
		for (int r = row; r < row + 3; r++) {
			for (int c = coloumn; c < coloumn + 3; c++) {
				region[r][c] = getValue(r, c);
			}
		}
		return region;
	}
	
	/*
	 * Checks if (row, coloumn) is part of the board, returns true if this is the case.
	 */
	private boolean isLegalCoordinate(int row, int coloumn) throws ArrayIndexOutOfBoundsException {
		if (row < 0 || row > 8 || coloumn < 0 || coloumn > 8) {
			throw new ArrayIndexOutOfBoundsException("(" + row + "," + coloumn + ") is not part of the board");
		}
		return true;
	}
}
