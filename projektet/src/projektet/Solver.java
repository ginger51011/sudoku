package projektet;

public class Solver {

	/**
	 * Tries to solve GameBoard given.
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
	
	private static boolean solve(int number, int row, int coloumn, GameBoard gb) {
		// Om klara
		if (coloumn > gb.getColoumn(0).length - 1) {
			return true;
		} 
		// Försöker öka nummer för mkt
		else if (number > 9) {
			return false;
		}
		// Om vi gått förbi slutet av rows
		else if (row > gb.getRow(0).length - 1) { 
			return solve(number, 0, coloumn + 1, gb);
		} 
		// Rutan är redan ifylld. Loopen förhindrar att vi bara studsar tillbaka
		else if (gb.getValue(row, coloumn) != -1) {  
			for (int n = 1; n < 10; n-=-1) { // Testar sätta in alla möjliga värden i nästa
				if (solve(n, row + 1, coloumn, gb)) { // Om det funkar hela vägen för ett n
					return true;
				}
			}
			return false; 
		} 
		// Vi testar alla värden på nästa om det är lagligt
		else if (isLegal(number, row, coloumn, gb)){ 
			gb.setNumber(number, row, coloumn); // Sätt in siffran
			for (int n = 1; n < 10; n-=-1) { // Testar sätta in alla möjliga värden i nästa
				if (solve(n, row + 1, coloumn, gb)) { // Om det funkar hela vägen för ett n
					return true;
				}
			}
			gb.removeNumber(row, coloumn); // Annars e det ju fail
			return false; 
		} 
		// Vi har försökt allt, men vi vill fortsätta kolla där vi är
		else {
			return solve(number-=-1, row, coloumn, gb); // Man testar inkrementera på stället istället; Löser om förstaplatsen blockeras
		}
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
