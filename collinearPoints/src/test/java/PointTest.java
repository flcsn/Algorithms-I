import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class PointTest {

	@Test
	public void createsSlopeBetweenTwoDistinctPoints() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		double slope = point1.slopeTo(point2);
		assertTrue(slope != 0.0);
	}
	
	@Test
	public void slopeOfPointsWithSameYCoordinatesReturnsZero() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 1);
		double slope = point1.slopeTo(point2);
		assertTrue(slope == 0.0);
	}
	
	@Test
	public void slopeOfPointsWithSameXCoordinatesReturnsPositiveInfinity() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 2);
		double slope = point1.slopeTo(point2);
		assertTrue(slope == Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void slopeOfPointsWithSameXYCoordinatesReturnsNegativeInfinity() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 1);
		double slope = point1.slopeTo(point2);
		assertTrue(slope == Double.NEGATIVE_INFINITY);
	}
	
	@Test
	public void point1LessThanPoint2IfY1LessThanY2() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		assertTrue(point1.compareTo(point2) == -1);
	}
	
	@Test
	public void point1GreaterThanPoint2IfY1GreaterThanY2() {
		Point point1 = new Point(1, 3);
		Point point2 = new Point(2, 2);
		assertTrue(point1.compareTo(point2) == 1);
	}
	
	@Test
	public void point1LessThanPoint2IfEqualYCoordinatesButX1LessThanX2() {
		Point point1 = new Point(1, 2);
		Point point2 = new Point(2, 2);
		assertTrue(point1.compareTo(point2) == -1);
	}
	
	@Test
	public void point1GreaterThanPoint2IfEqualYCoordinatesButX1GreaterThanX2() {
		Point point1 = new Point(3, 2);
		Point point2 = new Point(2, 2);
		assertTrue(point1.compareTo(point2) == 1);
	}
	
	@Test
	public void pointsEqualIfXYCoordinatesAreTheSame() {
		Point point1 = new Point(2, 2);
		Point point2 = new Point(2, 2);
		assertTrue(point1.compareTo(point2) == 0);
	}

	@Test
	public void slopeOrderMethodCallCreatesNewComparator() {
		Point point = new Point(1, 1);
		Comparator<Point> comparator = point.slopeOrder();
		assertNotNull(comparator);
	}
}
