
public class BacktrackingStrategy extends Strategy{
	BacktrackingStrategy(String fileName) {
		super(fileName);
	}

	BacktrackingStrategy(int[][] grid) {
		super(grid);
	}
	
	/*
	 * Tries every possible value in each position and if it gets to a point where there is no possible solution then
	 * it recursively backtracks
	 */
	public boolean solve() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.solution[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (validate(i, j, k)) {
							this.solution[i][j] = k;
							if (solve())
								return true;
							else 
								this.solution[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return true; 
	}

}
