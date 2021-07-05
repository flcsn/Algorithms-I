import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
	private ArrayList<LineSegment> lineSegments;
	private ArrayList<Point> maximalPoints;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		lineSegments = new ArrayList<>();
		maximalPoints = new ArrayList<>();
		Point[] sortedPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			checkIfNull(points[i]);
			sortedPoints[i] = points[i];
		}
		
		// determine point p
		for (int p = 0; p < points.length; p++) {
			Point origin = points[p];
			
			// for every other point q, sort the points according to the slope made from p
			Arrays.sort(sortedPoints, origin.slopeOrder());
			for (int i = 0; i + 1 < sortedPoints.length; i++) {
				if (sortedPoints[i].compareTo(sortedPoints[i+1]) == 0) throw new IllegalArgumentException("argument contains repeated point");
			}
			
			// compare slope values of adjacent points
			for (int q = 0; q < sortedPoints.length; q++) {
				int count = 1;
				double originSlopeToQ = origin.slopeTo(sortedPoints[q]);
				
				// if origin is the same point as q, disregard loop
				if (originSlopeToQ == Double.NEGATIVE_INFINITY) continue;
				
				// if slope values are equal, continue checking next values until their slope values are no longer equal
				for (int i = q + 1; i < sortedPoints.length; i++) {
					double originSlopeToI = origin.slopeTo(sortedPoints[i]);
					
					// count number of adjacent points with equal slopes from p
					if 		(originSlopeToI == Double.NEGATIVE_INFINITY) 	continue;
					else if (originSlopeToQ == originSlopeToI) 				count++;
					else 													break;
				}
				
				// if count of collinear points from origin >= 3
				// try to add line segment
				if (count >= 3) {
					addLineSegment(sortedPoints, origin, q, q+count-1);
				}
				
				// increment q by the number of equal adjacent points processed, if any
				// minus 1, because it counted itself 
				q += count-1;
			}
			// repeat for N number of points
		}
	}
	
	private void addLineSegment(Point[] sortedPoints, Point origin, int startIndex, int endIndex) {
		// look for minimum and maximum points of the line segment
		Point min = origin;
		Point max = sortedPoints[endIndex];
		
		// swaps points if min point is greater than max
		if (min.compareTo(max) > 0) {
			Point temp = min;
			min = max;
			max = temp;
		}
		
		for (int i = startIndex; i <= endIndex; i++) {
			if (min.compareTo(sortedPoints[i]) > 0) min = sortedPoints[i];
			if (max.compareTo(sortedPoints[i]) < 0) max = sortedPoints[i];
		}
		
		// checks list of maximal points if would-be Line Segment will produce a duplicate line segment
		
		// PROBLEMATIC !! maximal points may be on the array, but line segment between them may not exist (e.g. parallel lines)
		// try: once you examine an origin point, add it to a "blacklist" array wherein sortedPoints will no longer consider those points
		// where every origin point will have been examined against every other point in the sortedPoints array
		// therefore, no need to re-examine old origin points for every new origin points
		// because newOrigin.slopeTo(oldOrigin) == oldOrigin.slopeTo(newOrigin), which was already done in oldOrigin's loop
		// maybe also try: sorting the loops by y first before putting it through the whole loop
		boolean minIsDuplicate = false;
		boolean maxIsDuplicate = false;
		boolean lineSegmentIsDuplicate = false;
		for (int i = 0; i < maximalPoints.size(); i++) {
			Point maximalPoint = maximalPoints.get(i);
			if (maximalPoint.compareTo(min) == 0) minIsDuplicate = true;
			if (maximalPoint.compareTo(max) == 0) maxIsDuplicate = true;
			if (minIsDuplicate && maxIsDuplicate) {
				lineSegmentIsDuplicate = true;
				break;
			}
		}
		
		if (!lineSegmentIsDuplicate) {
			lineSegments.add(new LineSegment(min, max));
			if (!minIsDuplicate) maximalPoints.add(min);
			if (!maxIsDuplicate) maximalPoints.add(max);
		}
	}
	
	private void checkIfNull(Point point) 
	{ if (point == null) throw new IllegalArgumentException(); }
	
	
	// the number of line segments
	public int numberOfSegments() 
	{ return lineSegments.size(); }
	
	// the line segments
	public LineSegment[] segments() { 
		LineSegment[] copy = new LineSegment[numberOfSegments()];
		for (int i = 0; i < numberOfSegments(); i++) copy[i] = lineSegments.get(i);
		return copy; 
	}
}