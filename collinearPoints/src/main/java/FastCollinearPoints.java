import java.util.Arrays;

public class FastCollinearPoints {
	private LineSegment[] lineSegments;
	private int numberOfLineSegments;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		lineSegments = new LineSegment[points.length];
		numberOfLineSegments = 0;
		
		// determine point p
		for (int p = 0; p < points.length; p++) {
			Point origin = points[p];
			checkIfNull(origin);
			
			// for every other point q, sort the points according to the slope made from p
			Arrays.sort(points, origin.slopeOrder());
			
			// compare slope values of adjacent points
			for (int q = p + 1; q < points.length; q++) {
				checkIfNull(points[q]);	
				int count = 0;
				// if slope values are equal, continue checking next values 
				// until their slope values are no longer equal
				for (int i = q + 1; origin.slopeTo(points[q]) == origin.slopeTo(points[i]); i++) {
					// count number of adjacent points with equal slopes from p
					checkIfNull(points[i]);	
					count++;
				}
				// if count of adjacent equal slopes >= 3
				// return a line segment from p to the last adjacent point with an equal slope
				if (count >= 3) lineSegments[numberOfLineSegments++] = new LineSegment(origin, points[q+count]);
				
				// increment q by the number of equal adjacent points processed, if any
				// minus 1, to account for the "q++" parameter in the for-loop
				if (count > 0) q += count-1;
			}
			// repeat for N number of points
		}
	}
	
	private void checkIfNull(Point point) {
		if (point == null) throw new IllegalArgumentException();
	}
	
	
	// the number of line segments
	public int numberOfSegments() 
	{ return numberOfLineSegments; }
	
	// the line segments
	public LineSegment[] segments()             
	{ return lineSegments; }
}