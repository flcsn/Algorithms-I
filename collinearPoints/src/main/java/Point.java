import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
    	else if (that.x == this.x) return Double.POSITIVE_INFINITY;
    	else if (that.y == this.y) return 0.0;
    	return 1.0 * (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.y < that.y) return -1;
    	else if (this.y > that.y) return 1;
    	
    	if (this.x < that.x) return -1;
    	else if (this.x > that.x) return 1;
    	return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
    	
    	return new bySlopeOrder();
    }
    
    private class bySlopeOrder implements Comparator<Point> {
    	
    	public int compare(Point p1, Point p2) { 
    		if (slopeTo(p1) < slopeTo(p2)) return -1;
    		else if (slopeTo(p1) > slopeTo(p2)) return 1;
    		return 0;
    	}
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	for (int i = 0; i < 5; i++) {
    		System.out.println("Iteration number " + (i + 1));
    		Point point1 = new Point(StdRandom.uniform(5), StdRandom.uniform(5));
    		Point point2 = new Point(StdRandom.uniform(5), StdRandom.uniform(5));
    		System.out.println(point1);
    		System.out.println(point2);
    		int comparison = point1.compareTo(point2);
    		if (comparison == 0) System.out.println("Points are equal");
    		else if (comparison == 1) System.out.println("Point 1 > Point 2");
    		else if (comparison == -1) System.out.println("Point 2 > Point 1");
    		
    		Point point3 = new Point(StdRandom.uniform(5), StdRandom.uniform(5));
    		Point point4 = new Point(StdRandom.uniform(5), StdRandom.uniform(5));
    		System.out.println("Sorting array...");
    		
    		Point[] pointArray = {point1, point2, point3, point4};
    		Arrays.sort(pointArray, point1.slopeOrder());
    		
    		for (Point point : pointArray) {
    			System.out.println(point);
    		}
    	}
    	
    }
}