import java.util.ArrayList;

public class BruteCollinearPoints {
	private ArrayList<LineSegment> lineSegments;
	
   // finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		checkIfNull(points[0]);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				checkIfNull(points[j]);
				if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("argument contains duplicate point");
			}
		}
		lineSegments = new ArrayList<>();
		for (int i = 0; i + 3 < points.length; i++) {
			for (int j = i + 1; j + 2 < points.length; j++) {
				for (int k = j + 1; k + 1 < points.length; k++) {
					if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
						for (int l = k + 1; l < points.length; l++) {
							if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
								Point[] collinear = {points[i], points[j], points[k], points[l]};
								addLineSegment(collinear);
							}
						}
					}

				}
			}
		}
	}
	
	private void addLineSegment(Point[] collinear) {
		Point min = collinear[0];
		Point max = collinear[collinear.length-1];
		for (int i = 0; i < collinear.length; i++) {
			if (min.compareTo(collinear[i]) > 0) min = collinear[i];
			if (max.compareTo(collinear[i]) < 0) max = collinear[i];
		}
		LineSegment toAdd = new LineSegment(min, max);
		if (lineSegments.contains(toAdd)) return;			// supposed to work but LineSegments does not implement overriden equals method
		lineSegments.add(toAdd);
	}
	
	private void checkIfNull(Point point) {
		if (point == null) throw new IllegalArgumentException();
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return lineSegments.size();
	}
	
	// the line segments
	public LineSegment[] segments() {
		LineSegment[] copy = new LineSegment[numberOfSegments()];
		for (int i = 0; i < numberOfSegments(); i++) {
			copy[i] = lineSegments.get(i);
		}
		return copy;
	}
}