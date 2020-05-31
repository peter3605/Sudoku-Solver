

public class Generator {
    int[][] grid; 
    int dim; 
    int sqrtDim;
    int k;
  
    /*
     * Constructor - dim is the dim x dim dimensions of the grid and k is the number of removed values
     */
    Generator(int dim, int k) { 
        this.dim = dim;
        this.k = k;
        sqrtDim = (int)Math.sqrt(dim);
  
        grid = new int[dim][dim];
    } 
  
    /*
     * Method to generate a grid
     */
    public void generateGrid() { 
        // Fill the diagonal of SRdim x SRdim matrices 
        fillDiagonal(); 
  
        // Fill remaining blocks 
        fillRemaining(0, sqrtDim); 
  
        // Remove Randomly K digits to make game 
        removeKDigits(); 
    } 
  
    /*
     * Fill the diagonal with numbers
     */
    private void fillDiagonal() { 
  
        for (int i = 0; i < dim; i = i + sqrtDim) 
            fillBox(i, i); 
    } 
  
    /*
     * Returns false if given 3 x 3 block contains num. 
     */
    private boolean unUsedInBox(int row, int col, int num) { 
        for (int i = 0; i < sqrtDim; i++) {
            for (int j = 0; j <sqrtDim; j++) {
                if (grid[row + i][col + j] == num) 
                    return false; 
            }
        }
        return true; 
    } 
  
    /*
     * Fill a 3 x 3 matrix. 
     */
    private void fillBox(int row, int col) { 
        int num; 
        for (int i=0; i < sqrtDim; i++) { 
            for (int j=0; j < sqrtDim; j++) { 
                do { 
                    num = randomGenerator(dim); 
                } 
                while (!unUsedInBox(row, col, num)); 
  
                grid[row + i][col + j] = num; 
            } 
        } 
    } 
  
    /*
     * Random integer generator 
     */
    private int randomGenerator(int num) { 
        return (int) Math.floor((Math.random() * num + 1)); 
    } 
  
    /*
     * Check if safe to put in cell 
     */
    private boolean checkIfSafe(int i, int j, int num) { 
        return (unUsedInRow(i, num) && 
                unUsedInCol(j, num) && 
                unUsedInBox(i - i % sqrtDim, j - j % sqrtDim, num)); 
    } 
  
    /*
     * Check in the row for existence 
     */
    private boolean unUsedInRow(int i, int num) { 
        for (int j = 0; j < dim; j++) 
           if (grid[i][j] == num) 
                return false; 
        return true; 
    } 
  
    /*
     * Check in the row for existence 
     */
    private boolean unUsedInCol(int j, int num) { 
        for (int i = 0; i < dim; i++) 
            if (grid[i][j] == num) 
                return false; 
        return true; 
    } 
  
    /*
     * A recursive function to fill remaining grid
     */
    private boolean fillRemaining(int i, int j) { 
        if (j >= dim && i < dim - 1) { 
            i = i + 1; 
            j = 0; 
        } 
        if (i >= dim && j >= dim) 
            return true; 
  
        if (i < sqrtDim) { 
            if (j < sqrtDim) 
                j = sqrtDim; 
        } 
        else if (i < dim-sqrtDim) { 
            if (j == (int)(i / sqrtDim) * sqrtDim) 
                j =  j + sqrtDim; 
        } 
        else { 
            if (j == dim - sqrtDim) { 
                i = i + 1; 
                j = 0; 
                if (i >= dim) 
                    return true; 
            } 
        } 
  
        for (int num = 1; num <= dim; num++) { 
            if (checkIfSafe(i, j, num)) { 
                grid[i][j] = num; 
                if (fillRemaining(i, j+1)) 
                    return true; 
                grid[i][j] = 0; 
            } 
        } 
        return false; 
    } 
  
    /*
     * Removes k digits from the grid
     */
    private void removeKDigits() { 
        int count = k; 
        while (count != 0) { 
            int cellId = randomGenerator(dim*dim); 

            // extract coordinates i  and j 
            int i = (cellId / dim); 
            int j = cellId % 9; 
            if (j != 0) 
                j = j - 1; 
  
            if (grid[i][j] != 0) { 
                count--; 
                grid[i][j] = 0; 
            } 
        } 
    } 
  
    /*
     * Print the grid
     */
    public void printGrid() { 
        for (int i = 0; i<dim; i++) { 
            for (int j = 0; j<dim; j++) 
                System.out.print(grid[i][j] + " "); 
            System.out.println(); 
        } 
        System.out.println(); 
    } 
    
    /*
     * Return the grid
     */
    public int[][] getGrid() {
    	return grid;
    }
}
