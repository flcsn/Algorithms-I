import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private final double[] percolationThresholds;
	private static final double CONFIDENCE_95 = 1.96; 

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials <= 0) {
    		throw new IllegalArgumentException("N or trials must be greater than 0");
    	}
    	
    	percolationThresholds = new double[trials];
    	for (int i = 0; i < trials; i++) {
    		Percolation percolation = new Percolation(n);
    		while (!percolation.percolates()) {
    			int rowToOpen = StdRandom.uniform(1, n+1);
    			int colToOpen = StdRandom.uniform(1, n+1);
    			percolation.open(rowToOpen, colToOpen);
    		}
    		percolationThresholds[i] = (1.0*percolation.numberOfOpenSites())/(1.0*n*n);
    	}
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(percolationThresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(percolationThresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return this.mean() - (CONFIDENCE_95 * this.stddev() / Math.sqrt(percolationThresholds.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return this.mean() + (CONFIDENCE_95 * this.stddev() / Math.sqrt(percolationThresholds.length));
    }

   // test client (see below)
   public static void main(String[] args) {
	   int n = Integer.parseInt(args[0]);
	   int T = Integer.parseInt(args[1]);
	   
	   PercolationStats percolationStats = new PercolationStats(n, T);
	   
	   System.out.println("mean = " + percolationStats.mean());
	   System.out.println("stddev = " + percolationStats.stddev());
	   System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
   }

}