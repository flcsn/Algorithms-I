
public class Percolation {
	private boolean[][] grid;
	private int[] sites;
	private int[] size;
	private final int N;
	private int openSites;
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0) {
    		throw new IllegalArgumentException("N must be greater than 0");
    	}
    	
    	N = n;
    	openSites = 0;
    	grid = new boolean[N][N];
    	
    	sites = new int[N*N];
    	for (int i = 0; i < sites.length; i++) {
    		// Initializes all top row sites to have the same root
    		if (i < N) {
    			sites[i] = 0;
    		} else {
    			sites[i] = i;
    		}
    	}
    	
    	size = new int[N*N];
    	for (int j = 0; j < size.length; j++) {
    		size[j] = 1;
    	}
    }
    
    private int root(int index) {
    	while (sites[index] != index) {
    		if (sites[index] == 0) {
    			break;
    		}
    		sites[index] = sites[sites[index]];
    		index = sites[index];
    	}
    	
    	return sites[index];
    }
    
    private void union(int site1, int site2) {
    	int site1Root = root(site1);
    	int site2Root = root(site2);
    	
    	if (site1Root == 0 || size[site1Root] > size[site2Root]) {
    		sites[site2Root] = site1Root;
    		size[site1Root] += size[site2Root];
    	} else {
    		sites[site1Root] = site2Root;
    		size[site2Root] += size[site1Root];
    	}
    }
    
    private int convertRowAndColToSiteIndex(int row, int col) {
    	return (N*row) + col;
    }
    
    private boolean isWithinGrid(int row, int col) {
    	if (row < 1 || row > N || col < 1 || col > N) {
    		return false;
    	}
    	return true;
    }
    
    private void connectToSiteAbove(int row, int col) {
    	if (isWithinGrid(row, col+1) && isOpen(row, col+1)) {
    		int thisSite = convertRowAndColToSiteIndex(row, col);
    		int thatSite = convertRowAndColToSiteIndex(row-1, col);
    		union(thisSite, thatSite);
    	}
    	
    }
    
    private void connectToSiteLeft(int row, int col) {
    	if (isWithinGrid(row+1, col) && isOpen(row+1, col)) {
    		int thisSite = convertRowAndColToSiteIndex(row, col);
    		int thatSite = convertRowAndColToSiteIndex(row, col-1);
    		union(thisSite, thatSite);
    	}   	
    }
    
    private void connectToSiteRight(int row, int col) {
    	if (isWithinGrid(row+1, col+2) && isOpen(row+1, col+2)) {
    		int thisSite = convertRowAndColToSiteIndex(row, col);
    		int thatSite = convertRowAndColToSiteIndex(row, col+1);
    		union(thisSite, thatSite);
    	}   	
    }

    private void connectToSiteBelow(int row, int col) {
    	if (isWithinGrid(row+2, col+1) && isOpen(row+2, col+1)) {
    		int thisSite = convertRowAndColToSiteIndex(row, col);
    		int thatSite = convertRowAndColToSiteIndex(row+1, col);
    		union(thisSite, thatSite);
    	}
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Parameters must be between [1, N]");
    	}
    	
    	if (isOpen(row, col)) {
    		return;
    	}
    	
    	grid[row-1][col-1] = true;
    	openSites++;
    	connectToSiteAbove(row-1, col-1);
    	connectToSiteLeft(row-1, col-1);
    	connectToSiteRight(row-1, col-1);
    	connectToSiteBelow(row-1, col-1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {  
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Parameters must be between [1, N]");
    	}
    	
    	return grid[row-1][col-1];
    }
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if (!isWithinGrid(row, col)) {
    		throw new IllegalArgumentException("Parameters must be between [1, N]");
    	}
    	
    	if (!isOpen(row, col)) {
    		return false;
    	}
    	
    	int siteIndex = convertRowAndColToSiteIndex(row-1, col-1);
    	return root(siteIndex) == 0;
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
    	return openSites;
    }
    // does the system percolate?
    public boolean percolates() {
    	if (openSites == 0) {
    		return false;
    	}
    	
    	for (int i = 0; i < N; i++) {
    		int bottomRowSite = convertRowAndColToSiteIndex(N-1, i);
    		if (root(bottomRowSite) == 0) {
    			return true;
    		}
    	}
    	return false;
    	
    }
	// public static void main(String[] args) {
		// TODO Auto-generated method stub


	//}

}
