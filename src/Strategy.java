import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class Strategy {
	int[][] grid = new int[9][9];
	int[][] solution = new int[9][9];
	
	Strategy(String fileName) {
		try {
			readFile(fileName);
			this.solution = grid;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Strategy(int[][] grid) {
		this.grid = grid;
		this.solution = grid;
	}
	
	public abstract boolean solve();
	
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
	
	public boolean validate(int row, int col, int val) {
		boolean check = true;
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == val) 
				check = false;
		}
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == val)
				check = false;
		}
		
		row = row - row % 3;
		col = col - col % 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[row + 1][col + j] == val)
					check = false;
			}
		}
		return check;
	}
	
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
	
	void printGrid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
	
	void printSolution() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(solution[i][j]);
			}
			System.out.println();
		}
	}
}
