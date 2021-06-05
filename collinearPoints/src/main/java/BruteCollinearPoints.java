public class BruteCollinearPoints {
	private LineSegment[] lineSegments;
	private int numberOfLineSegments;
	
   // finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		lineSegments = new LineSegment[points.length];
		numberOfLineSegments = 0;
		
		for (int i = 0; i + 4 <= points.length; i++) {
			checkIfNull(points[i]);
			for (int j = i + 1; j + 3 <= points.length; j++) {
				checkIfNull(points[j]);
				for (int k = j + 1; k + 2 <= points.length; k++) {
					checkIfNull(points[k]);
					if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
						for (int l = k + 1; l + 1 <= points.length; l++) {
							checkIfNull(points[l]);
							if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
								lineSegments[numberOfLineSegments++] = new LineSegment(points[i], points[l]);
							}
						}
					}

				}
			}
		}
	}
	
	private void checkIfNull(Point point) {
		if (point == null) throw new IllegalArgumentException();
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