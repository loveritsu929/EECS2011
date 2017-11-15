package A1solution;

import java.awt.geom.Point2D;

/**
 * Ellipse implements the PlanarShape interface as per assignment specification
 * 
 * @author andy
 *
 */
public class Ellipse implements PlanarShape {

	/**
	 * center of the ellipse.
	 * 
	 * It is protected (rather than private) so that its subclasses can access it directly (e.g., see the
	 * Circle.toString() method).
	 */
	protected Point2D.Double center;

	/**
	 * horizontal axis of the ellipse
	 */
	private double hAxis;

	/**
	 * vertical axis of the ellipse
	 */
	private double vAxis;

	/**
	 * no-parameter constructor
	 * 
	 * @throws InvalidShapeException
	 *             if radius is negative
	 */
	public Ellipse() throws InvalidShapeException {
		this(new Point2D.Double(0,0), 1,1);
	}

	/**
	 * full-parameter constructor
	 * 
	 * @param c
	 *            circle center
	 * @param a
	 *            horizontal axis
	 * 
	 * @param b
	 *            vertical axis
	 * 
	 * @throws InvalidShapeException
	 *             if any of the axes is negative
	 */
	public Ellipse(Point2D.Double c, double a, double b) throws InvalidShapeException {
		if (c == null || a < 0 || b < 0)
			throw new InvalidShapeException("Cannot instantiate shape with illegal parameters.");
		center = c;
		hAxis = a;
		vAxis = b;

	}

	/**
	 * @return the center
	 */
	public Point2D.Double getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(Point2D.Double center) {
		this.center = center;
	}

	/**
	 * @return the hAxis
	 */
	public double getHorizontalAxis() {
		return hAxis;
	}

	/**
	 * @param hAxis
	 *            the hAxis to set
	 */
	public void setHorizontalAxis(double hAxis) throws InvalidShapeException {
		if (hAxis < 0) {
			throw new InvalidShapeException("Illegal request to set negative horizontal axis: " + hAxis);
		}
		this.hAxis = hAxis;
	}

	/**
	 * @return the vAxis
	 */
	public double getVerticalAxis() {
		return vAxis;
	}

	/**
	 * @param vAxis
	 *            the vAxis to set
	 */
	public void setVerticalAxis(double vAxis) throws InvalidShapeException {
		if (vAxis < 0) {
			throw new InvalidShapeException("Illegal request to set negative vertical axis: " + vAxis);
		}
		this.vAxis = vAxis;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ellipse [Center = " + center + ", Horizontal Axis = " + hAxis + ", Vertical Axis = " + vAxis + "]";
	}

	/* (non-Javadoc)
	 * @see A1sol.PlanarShape#area()
	 */
	@Override
	public double area() {
		return Math.PI * hAxis * vAxis;
	}

	/**
	 * helper function: square
	 * 
	 * @param a
	 *            any double number
	 * @return square of a
	 */
	public static double sq(double a) {
		return a * a;
	}

	/* (non-Javadoc)
	 * @see A1sol.PlanarShape#contains(java.awt.geom.Point2D)
	 */
	@Override
	public boolean contains(Point2D.Double p) {
		return (sq((p.x - center.x) / hAxis) + sq((p.y - center.y) / vAxis) <= 1);
	}

	/**
	 * @param args
	 *            any arguments supplied to main
	 */
	public static void main(String[] args) {
		Ellipse ellipse; // test ellipse
		Point2D.Double p; // test point for contains() method

		try {
			System.out.println("\nHere is an example ellipse: \n");
			ellipse = new Ellipse();
			System.out.println("\t" + ellipse.toString());

			System.out.println("\tArea = " + ellipse.area() + "\n");
			
			p = new Point2D.Double(1, 0);
			System.out.print("\tDoes this ellipse contain " + p + " ?");
			System.out.println((ellipse.contains(p)) ? " Yes." : " No.");
			
			p = new Point2D.Double(0, 1);
			System.out.print("\tDoes this ellipse contain " + p + " ?");
			System.out.println((ellipse.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(1, 1);
			System.out.print("\tDoes this ellipse contain " + p + " ?");
			System.out.println((ellipse.contains(p)) ? " Yes." : " No.");

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("\nHere is an example ellipse: \n");
			ellipse = new Ellipse(new Point2D.Double(2.7, 3.4), 4.01, 5.93);
			System.out.println("\t" + ellipse.toString());

			System.out.println("\tArea = " + ellipse.area());

			p = new Point2D.Double(8.7, 1.6);
			System.out.print("\tDoes this ellipse contain " + p + " ?");
			System.out.println((ellipse.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(2.3, 8.7);
			System.out.print("\tDoes this ellipse contain " + p + " ?");
			System.out.println((ellipse.contains(p)) ? " Yes." : " No.");

			System.out.println("\n\tNow change horizontal axis to -8.3");
			ellipse.setHorizontalAxis(-8.3);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("\nHere is an example ellipse: \n");
			ellipse = new Ellipse(new Point2D.Double(2.7, 3.4), 4.01, 5.93);
			System.out.println("\t" + ellipse.toString());
			System.out.println("\n\tNow change vertical axis to -12.45");
			ellipse.setVerticalAxis(-12.45);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("Attempt to create an ellipse with the following parameters: \n");
			Point2D.Double c = new Point2D.Double(2.7, 3.4);
			double a = -6.87;
			double b = 5.93;
			System.out.println("\tCenter = " + c + ", \n\tHorizontal Axis = " + a + ", \n\tVertical Axis = " + b);
			ellipse = new Ellipse(c, a, b);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}
	}

}