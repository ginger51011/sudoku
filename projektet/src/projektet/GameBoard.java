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
	 * Checks if (row, coloumn) is part of the board, returns true if this is the case.
	 */
	private boolean isLegalCoordinate(int row, int coloumn) throws ArrayIndexOutOfBoundsException {
		if (row < 0 || row > 8 || coloumn < 0 || coloumn > 8) {
			throw new ArrayIndexOutOfBoundsException("(" + row + "," + coloumn + ") is not part of the board");
		}
		return true;
	}
}
