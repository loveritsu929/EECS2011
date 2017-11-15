package A1solution;

import java.awt.geom.Point2D;

/**
 * Circle extends Ellipse as per assignment specification
 * 
 * @author andy
 *
 */
public class Circle extends Ellipse {
	
	
	/**
	 * circle radius
	 */
	private double radius;

	/**
	 * no-parameter constructor. Constructs a unit circle centered at the origin.
	 * 
	 * @throws InvalidShapeException
	 *             if radius is negative
	 */
	public Circle() throws InvalidShapeException {
		radius = 1;
	}

	/**
	 * full-parameter constructor
	 * 
	 * @param c
	 *            circle center
	 * @param r
	 *            circle radius
	 * 
	 * @throws InvalidShapeException
	 *             if radius is negative
	 */
	public Circle(Point2D.Double c, double r) throws InvalidShapeException {
		super(c, r, r);
		radius = r;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param r
	 *            the radius to set
	 * 
	 * @throws InvalidShapeException
	 *             if radius is negative
	 */
	public void setRadius(double r) throws InvalidShapeException {
		if (r < 0) {
			throw new InvalidShapeException("Invalid Circle: radius cannot be set to a negative number.");
		}
		radius = r;
		super.setHorizontalAxis(r);
		super.setVerticalAxis(r);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Circle [Center = " + center + ", Radius = " + radius + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see A1sol.PlanarShape#area()
	 *
	 * As stated in class, the reason we override the Ellipse.area() method is to compute Circle.area() directly from
	 * its radius rather than rely on hAxis and vAxis of its superType Ellipse. With the latter version it would be
	 * possible to modify the area (by casting the circle to an ellipse and then applying Ellipse's setter methods to
	 * change its hAxis or vAxis) without changing the circle's radius. That would break the intended invariant
	 * relationship between area and radius and return an area that is inconsistent with the current circle radius.
	 * 
	 */
	@Override
	public double area() {
		return Math.PI * sq(radius);
	}

	/**
	 * @param c
	 *            parameter circle
	 * 
	 * @return true if and only if instance circle contains parameter circle
	 */
	public boolean contains(Circle c) {
		/*
		 * Return true iff instance circle is at least as large as parameter circle, and the distance between their
		 * centers is no longer than the difference of the two radii. We have avoided direct distance computation, since
		 * it requires taking square-root. Instead, we make the comparison on the square of the distances. The latter
		 * approach uses only arithmetic operations which are simpler, faster, and less error prone than taking
		 * square-root.
		 */
		return (this.radius >= c.radius && sq(c.center.x - this.center.x) + sq(c.center.y - this.center.y) <= sq(this.radius - c.radius));
	}

	/**
	 * @param args
	 *            any arguments supplied to main
	 */
	public static void main(String[] args) {
		Circle shape; // test circle
		Point2D.Double p; // test point for contains() method
		Circle testCircle; // test circle for contains() method

		try {
			System.out.println("\nHere is an example circle: \n");
			shape = new Circle();
			System.out.println("\t" + shape.toString());

			System.out.println("\tArea = " + shape.area() + "\n");

			p = new Point2D.Double(1, 0);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(0, 1);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(1, 1);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No. \n");

			testCircle = new Circle(new Point2D.Double(1, 0), 2.5);
			System.out.print("\tDoes this circle contain " + testCircle.toString() + " ?");
			System.out.println((shape.contains(testCircle)) ? " Yes." : " No.");
			System.out.print("\tIs this circle contained in " + testCircle.toString() + " ?");
			System.out.println((testCircle.contains(shape)) ? " Yes." : " No.");

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("\nHere is an example circle: \n");
			shape = new Circle(new Point2D.Double(2.7, 8.4), 6.24);
			System.out.println("\t" + shape.toString());

			System.out.println("\tArea = " + shape.area() + "\n");

			p = new Point2D.Double(9.7, 4.6);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(4.6, 9.7);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(3.67, -1.24);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			p = new Point2D.Double(-1.24, 3.67);
			System.out.print("\tDoes this circle contain " + p + " ?");
			System.out.println((shape.contains(p)) ? " Yes." : " No.");

			testCircle = new Circle(new Point2D.Double(3, 4), 2);
			System.out.print("\n\tDoes this circle contain " + testCircle.toString() + " ?");
			System.out.println((shape.contains(testCircle)) ? " Yes." : " No.");

			testCircle = new Circle(new Point2D.Double(1, 5), 2);
			System.out.print("\tDoes this circle contain " + testCircle.toString() + " ?");
			System.out.println((shape.contains(testCircle)) ? " Yes." : " No.");

			System.out.println("\n\tNow change radius to -8.3");
			shape.setRadius(-8.3);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("\nHere is an example circle: \n");
			shape = new Circle(new Point2D.Double(2.7, 3.4), 4.01);
			System.out.println("\t" + shape.toString());
			System.out.println("\n\tNow change radius to -12.45");
			shape.setRadius(-12.45);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

		try {
			System.out.println("\n Attempt to create a circle with the following parameters: \n");
			Point2D.Double c = new Point2D.Double(2.7, 3.4);
			double r = -6.87;
			System.out.println("\tCenter = " + c + ", \n\tRadius = " + r);
			shape = new Circle(c, r);

		} catch (InvalidShapeException e) {
			System.out.println("\t" + e + "\n");
		}

	}

}