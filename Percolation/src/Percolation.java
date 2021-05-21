
public class Percolation {
	private int[][] grid;
	private int topSite;
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	int count = 0;
    	topSite = n*n+1;
    	
    	grid = new int[n][n];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (i == 1) {
    				grid[i][j] = topSite;
    			} else {
    				grid[i][j] = count*-1;
    			}	
    			count++;
    		}
    	}
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if (grid[row][col] < 0) {
    		grid[row][col] *= -1;
    	}	
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	return grid[row][col] >= 0;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	return grid[row][col] == topSite;
    }
    // returns the number of open sites
    public int numberOfOpenSites()

    // does the system percolate?
    public boolean percolates()

	//public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}
