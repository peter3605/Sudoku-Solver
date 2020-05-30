
public class BasicStrategy extends Strategy{
	
	BasicStrategy(int[][] grid) {
		super(grid);
	}
	
	BasicStrategy(String fileName) {
		super(fileName);
	}
	
	public boolean solve() {
	    for (int i = 0; i < 9; i++){
	        for (int j = 0; j < 9; j++){
	            if (solution[i][j] != 0) {
	                continue;
	            }
	 
	            for (int k = 1; k <= 9; k++) {
	                solution[i][j] = k;
	                if(validate(i, j) && solve()) {
	                    return true;
	                }
	                solution[i][j] = 0;
	            }
	            return false;
	        }
	    }
	    return true;
	}
	
}