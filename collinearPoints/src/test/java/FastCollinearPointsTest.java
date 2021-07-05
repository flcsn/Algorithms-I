import static org.junit.Assert.*;

import org.junit.Test;

public class FastCollinearPointsTest {
	
	@Test
	public void nullConstructorParameterThrowsException() {
		Point[] points = null;
		assertThrows(IllegalArgumentException.class, () -> {
			FastCollinearPoints fast = new FastCollinearPoints(points);
		});
	}
	
	@Test
	public void nullPointThrowsException() {
		Point a = new Point(0,0);
		Point b = null;
		Point c = new Point(2,2);
		Point d = new Point(3,3);
		Point[] points = {a, b, c, d};
		assertThrows(IllegalArgumentException.class, () -> {
			FastCollinearPoints fast = new FastCollinearPoints(points);
		});
	}
	
	@Test
	public void fourCollinearPointIncrementNumberOfLineSegments() {
		Point a = new Point(0,0);
		Point b = new Point(1,1);
		Point c = new Point(2,2);
		Point d = new Point(3,3);
		Point[] points = {a, b, c, d};
		FastCollinearPoints fast = new FastCollinearPoints(points);
		assertTrue(fast.numberOfSegments() == 1);
	}
	
	@Test
	public void fourNonCollinearPointsDoesNotIncrementNumberOfLineSegments() {
		Point a = new Point(0,0);
		Point b = new Point(1,6);
		Point c = new Point(7,5);
		Point d = new Point(9,1);
		Point[] points = {a, b, c, d};
		FastCollinearPoints fast = new FastCollinearPoints(points);
		assertTrue(fast.numberOfSegments() == 0);
	}
	
	@Test
	public void fiveHorizontalCollinearPointsAddsOneLineSegment() {
		Point a = new Point(0,1);
		Point b = new Point(1,1);
		Point c = new Point(2,1);
		Point d = new Point(3,1);
		Point e = new Point(4,1);
		Point[] points = {a, b, c, d, e};
		FastCollinearPoints fast = new FastCollinearPoints(points);
		assertTrue(fast.numberOfSegments() == 1);
	}

}
