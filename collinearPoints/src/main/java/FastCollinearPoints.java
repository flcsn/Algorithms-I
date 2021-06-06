import java.util.Arrays;

public class FastCollinearPoints {
	private Point[] sortedPoints;
	private LineSegment[] lineSegments;
	private int numberOfLineSegments;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		lineSegments = new LineSegment[points.length];
		sortedPoints = new Point[points.length];
		numberOfLineSegments = 0;
		
		for (int i = 0; i < points.length; i++) {
			checkIfNull(points[i]);
			sortedPoints[i] = points[i];
		}
		
		// determine point p
		for (int p = 0; p < points.length; p++) {
			Point origin = points[p];
			
			// for every other point q, sort the points according to the slope made from p
			Arrays.sort(sortedPoints, origin.slopeOrder());
			
			// compare slope values of adjacent points
			for (int q = 0; q < sortedPoints.length; q++) {
				int count = 1;
				// if origin is the same point as q, disregard loop
				if (origin.slopeTo(sortedPoints[q]) == Double.NEGATIVE_INFINITY) continue;
				
				// if slope values are equal, continue checking next values 
				// until their slope values are no longer equal
				for (int i = q + 1; i < sortedPoints.length; i++) {
					// count number of adjacent points with equal slopes from p
					if (origin.slopeTo(sortedPoints[i]) == Double.NEGATIVE_INFINITY) continue;
					else if (origin.slopeTo(sortedPoints[q]) == origin.slopeTo(sortedPoints[i])) count++;
					else break;
				}
				// if count of collinear points from origin >= 3
				// try to add line segment
				if (count >= 3) {
					
					// look for minimum and maximum points of the line segment
					Point min = origin;
					Point max = sortedPoints[q+count-1];
					
					// swaps points if min point is greater than max
					if (min.compareTo(max) > 0) {
						Point temp = min;
						min = max;
						max = temp;
					}
					
					for (int i = q; i <= q+count-1; i++) {
						if (min.compareTo(sortedPoints[i]) > 0) min = sortedPoints[i];
						if (max.compareTo(sortedPoints[i]) < 0) max = sortedPoints[i];
					}
					
					// checks array for potential duplicate
					LineSegment lineSegmentToAdd = new LineSegment(min, max);
					boolean inArray = false;
					for (int j = 0; j < numberOfLineSegments; j++) {
						if (lineSegments[j].toString().equals(lineSegmentToAdd.toString())) inArray = true;
					}
					
					// adds new line segment if no duplicate found
					if (!inArray) lineSegments[numberOfLineSegments++] = lineSegmentToAdd;
				}
				
				// increment q by the number of equal adjacent points processed, if any
				// minus 1, to account for the "q++" parameter in the for-loop
				q += count-1;
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