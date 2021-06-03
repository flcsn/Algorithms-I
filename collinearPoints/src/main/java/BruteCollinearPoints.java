public class BruteCollinearPoints {
	private LineSegment[] lineSegments;
	private int numberOfLineSegments;
	
   // finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		lineSegments = new LineSegment[points.length/4];
		numberOfLineSegments = 0;
		
		for (int i = 0; i + 4 < points.length; i++) {
			if (points[i].slopeTo(points[i+1]) == points[i+1].slopeTo(points[i+2])
					&& points[i+1].slopeTo(points[i+2]) == points[i+2].slopeTo(points[i+3])) {
				// collinear
				lineSegments[numberOfLineSegments] = new LineSegment(points[i], points[i+3]);
				numberOfLineSegments++;
			}
		}
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return numberOfLineSegments;
	}
	
	// the line segments
	public LineSegment[] segments() {
		return lineSegments;
	}
}