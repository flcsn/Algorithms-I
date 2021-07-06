import java.util.Arrays;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class FastCollinearPoints {
	private ArrayList<LineSegment> lineSegments;
	private ArrayList<Point> maxPoints;
	private ArrayList<Point> minPoints;
	private ArrayList<Double> slopes;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("argument is null");
		lineSegments = new ArrayList<>();
		maxPoints = new ArrayList<>();
		minPoints = new ArrayList<>();
		slopes = new ArrayList<>();
		
		Point[] sortedPoints = new Point[points.length];
		Point[] pointsCopy = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			checkIfNull(points[i]);
			sortedPoints[i] = points[i];
			pointsCopy[i] = points[i];
		}
		Arrays.sort(pointsCopy);
		for (int i = 0; i + 1 < pointsCopy.length; i++) {
			if (pointsCopy[i].compareTo(pointsCopy[i+1]) == 0) throw new IllegalArgumentException("argument contains repeated point");
		}
		
		// determine point p
		for (int p = 0; p < pointsCopy.length; p++) {
			Point origin = pointsCopy[p];
			
			// for every other point q, sort the points according to the slope made from p
			Arrays.sort(sortedPoints, origin.slopeOrder());
			
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
		
		double minToMaxSlope = min.slopeTo(max);
		boolean inMaxPointsArray = maxPoints.contains(max);
		boolean inMinPointsArray = minPoints.contains(min);
		boolean inSlopeArray = slopes.contains(minToMaxSlope);
		
		if (!inSlopeArray || !inMaxPointsArray || !inMinPointsArray) {
			lineSegments.add(new LineSegment(min, max));
			if (!inSlopeArray) slopes.add(minToMaxSlope);
			if (!inMaxPointsArray) maxPoints.add(max);
			if (!inMinPointsArray) minPoints.add(min);
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
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int numberOfPoints = Integer.parseInt(in.readLine());
		Point[] points = new Point[numberOfPoints];
		int counter = 0;
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] coordinates = line.split(" ");
			int x = Integer.parseInt(coordinates[1]);
			int y = Integer.parseInt(coordinates[2]);
			System.out.println("Point's coordinates are (" + x + "," + y + ")");
			points[counter] = new Point(x, y);
			counter++;
		}
		System.out.println("Created point array with " + counter + " points.");
		FastCollinearPoints fast = new FastCollinearPoints(points);
		System.out.println(fast.numberOfSegments() + " line segments found.");
	}
}