package A1solution;

import java.awt.geom.Point2D;

/**
 * The interface for planar shapes intended to be implemented and further extended by specific planar shape patterns.
 * 
 * @author andy
 *
 */
public interface PlanarShape {

	/**
	 * @return area of the planar shape
	 */
	public double area();

	/**
	 * @param p
	 *            a given point in the plane
	 * @return true if and only if instance shape contains parameter point p
	 *
	 */
	public boolean contains(Point2D.Double p);
	
}