import static org.junit.Assert.*;

import org.junit.Test;

public class BruteCollinearPointsTest {
	
	@Test
	public void nullConstructorParameterThrowsException() {
		Point[] points = null;
		assertThrows(IllegalArgumentException.class, () -> {
			BruteCollinearPoints brute = new BruteCollinearPoints(points);
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
			BruteCollinearPoints brute = new BruteCollinearPoints(points);
		});
	}
	
	@Test
	public void fourCollinearPointIncrementNumberOfLineSegments() {
		Point a = new Point(0,0);
		Point b = new Point(1,1);
		Point c = new Point(2,2);
		Point d = new Point(3,3);
		Point[] points = {a, b, c, d};
		BruteCollinearPoints brute = new BruteCollinearPoints(points);
		assertTrue(brute.numberOfSegments() == 1);
	}
	
	@Test
	public void fourNonCollinearPointsDoesNotIncrementNumberOfLineSegments() {
		Point a = new Point(0,0);
		Point b = new Point(1,6);
		Point c = new Point(7,5);
		Point d = new Point(9,1);
		Point[] points = {a, b, c, d};
		BruteCollinearPoints brute = new BruteCollinearPoints(points);
		assertTrue(brute.numberOfSegments() == 0);
	}

}
