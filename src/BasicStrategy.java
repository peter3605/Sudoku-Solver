
public class BasicStrategy extends Strategy{
	
	BasicStrategy(int[][] grid) {
		super(grid);
	}
	
	BasicStrategy(String fileName) {
		super(fileName);
	}
	
	public boolean solve() {
		boolean stop = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					stop = false;
					row = i;
					col = j;
					break;
				}
			}
		}

		if (stop) 
			return true;

		for (int i = 0; i < 10; i++) {
			if (validate(row, col, i)) {
				solution[row][col] = i;
				if (solve())
					return true;
				solution[row][col] = 0;
			}
		}
		return false;
	}
}