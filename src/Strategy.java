import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public abstract class Strategy {
	int[][] grid = new int[9][9];
	int[][] solution = new int[9][9];
	
	/*
	 * Constructor to load grid from a gile
	 */
	Strategy(String fileName) {
		try {
			readFile(fileName);
			this.solution = copyGrid(this.grid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Constructor to load grid from matrix
	 */
	Strategy(int[][] grid) {
		this.grid = grid;
		this.solution = copyGrid(this.grid);
	}
	
	/*
	 * The method needed to be implemented by all strategies that solves the sudoku grid
	 */
	public abstract boolean solve();
	
	/*
	 * Returns a deep copy of a grid
	 */
	private int[][] copyGrid(int[][] grid) {
		int[][] newGrid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		return newGrid;
	}
	
	/*
	 * Validates the entire solution grid
	 */
	public boolean validate() {
		for (int i = 0; i < 9; i++) {
			boolean[] rows = {false, false, false, false, false, false, false, false, false};
			boolean[] columns = {false, false, false, false, false, false, false, false, false};
			for (int j = 0; j < 9; j++) {
				// Row
				if (rows[solution[i][j] - 1])
					return false;
				rows[solution[i][j] - 1] = true;
				
				//Column
				if (columns[solution[j][i] - 1])
					return false;
				columns[solution[j][i] - 1] = true;
			}
		}
		return true;
	}
	
	/*
	 * Validates a specific position on the solution grid
	 */
	public boolean validate(int row, int col) {
		HashSet<Integer> set = new HashSet<>();
		 
	    for (int k = 0; k < 9; k++) {
	        if (set.contains(this.solution[row][k])) {
	            return false;
	        }
	        if (this.solution[row][k] != 0) {
	            set.add(this.solution[row][k]);
	        }
	 
	    }
	    set.clear();
	 
	    for (int k = 0; k < 9; k++) { 
	        if (set.contains(this.solution[k][col])) {
	            return false;
	        }
	        if (this.solution[k][col] != 0) {
	            set.add(this.solution[k][col]);
	        }
	    }
	    set.clear();
	 
	    int x = row /3 * 3;
	    int y = col / 3 * 3;
	    for (int i = x; i < x + 3; i++) { 
	        for (int j = y; j < y + 3; j++){
	            if (set.contains(this.solution[i][j])) {
	                return false;
	            }
	            if (this.solution[i][j] != 0) {
	                set.add(this.solution[i][j]);
	            }
	        }
	    }
	    set.clear();
	 
	    return true;
	}
	
	/*
	 * Reads in a sudoku board from a file and stores it in this.grid
	 */
	private void readFile(String fileName) throws Exception{
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		int i = 0;
		int j = 0;
		while(true) {
			j = 0;
			char[] line = br.readLine().toCharArray();
			for (int x = 0; x < line.length; x++) {
				grid[i][j] = Integer.valueOf(String.valueOf(line[x]));
				j++;
				if (j == 9) {
					i++;
					break;
				}
			}
			if (i == 9)
				break;
		}
	}
	
	/*
	 * Prints the original grid
	 */
	void printGrid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
	
	/*
	 * Prints the solution
	 */
	void printSolution() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(solution[i][j]);
			}
			System.out.println();
		}
	}
}
