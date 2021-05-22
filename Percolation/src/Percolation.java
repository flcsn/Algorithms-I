
public class Percolation {
	private int[][] grid;
	private int gridSideLength;
	private int topSite;
	private int openSites;
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) throws Exception{
    	if (n <= 0) {
    		throw new IllegalArgumentException("N must be greater than 0.");
    	}
    	
    	int count = 1;
    	gridSideLength = n;
    	topSite = n*n+1;
    	openSites = 0;
    	
    	grid = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (i == 0) {
    				grid[i][j] = topSite*-1;
    			} else {
    				grid[i][j] = count*-1;
    			}	
    			count++;
    		}
    	}
    }
    
    private boolean isWithinGrid(int row, int col) {
    	if (row < 0 || row >= gridSideLength 
    			|| col < 0 || col >= gridSideLength) {
    		return false;
    	}
    	return true;
    }
    
    private void connectToAdjacentOpenSites(int row, int col) throws Exception {
    	// Connects to adjacent sites by taking the value from the sites above and/or to its left (prioritized in that order) if open
    	// Passes its value to the sites to its right and below if open, and recursively calls itself on the open sites
    	if (isWithinGrid(row, col-1) && isOpen(row, col-1)) {
        	grid[row][col-1] = grid[row][col];
    	if (isWithinGrid(row-1, col) && isOpen(row-1, col)) {
    		grid[row][col] = grid[row-1][col];
    	}

    	}
    	if (isWithinGrid(row, col+1) && isOpen(row, col+1)) {
    		if (grid[row][col+1] != grid[row][col]) {
        		grid[row][col+1] = grid[row][col];
        		connectToAdjacentOpenSites(row, col+1);
    		}
    	}
    	if (isWithinGrid(row+1, col) && isOpen(row+1, col)) {
    		if (grid[row+1][col] != grid[row][col]) {
        		grid[row+1][col] = grid[row][col];
        		connectToAdjacentOpenSites(row+1, col);
    		}
    	}
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws Exception {
    	row -= 1;
    	col -= 1;
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Variable row or col is not a value between 1 and gridLength.");
    	}
    	
    	if (grid[row][col] < 0) {
    		grid[row][col] *= -1;
    		openSites++;
    	}	
    	
    	connectToAdjacentOpenSites(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) throws Exception {
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Variable row or col is not a value between 1 and gridLength.");
    	}
    	
    	return grid[row][col] >= 0;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) throws Exception {
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Variable row or col is not a value between 1 and gridLength.");
    	}
    	
    	return grid[row][col] == topSite;
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
    	return openSites;
    }
    // does the system percolate?
    public boolean percolates() {
    	for (int i = 0; i < gridSideLength; i++) {
    		if (grid[gridSideLength-1][i] == topSite) {
    			return true;
    		}
    	}
    	return false;
    }
	//public static void main(String[] args) {
		// TODO Auto-generated method stub


	//}

}
