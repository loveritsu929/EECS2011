/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 3
 * 
 * Student Full Name:     Chenxing Zheng
 * Student eecs account:  cxing95
 * Student ID number:     214634901 
 **********************************************************/
package A1;

import java.awt.geom.Point2D;

public class Circle extends Ellipse {

	protected Point2D.Double center;
	private double radius;
	
	public Circle(){
		this.center = new Point2D.Double(0.0, 0.0);
		this.radius = 1;
	}
	
	public Circle(Point2D.Double p, double radius) throws InvalidShapeException{
		if(radius <= 0 )
			throw new InvalidShapeException(" Invalid Radius!");
		
		this.center = p;
		this.radius = radius;
	}
	
	public Point2D.Double getCenter() {
		return center;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setCenter(Point2D.Double center) {
		this.center = center;
	}
	
	public void setRadius(double radius) throws InvalidShapeException {
		if(radius <= 0 )
			throw new InvalidShapeException(" Invalid Radius!");
		this.radius = radius;
	}
	
	public String toString(){
		String s = "This circle has center point " + center.toString() + ", and radius " + getRadius() + ".";
		return s;
	}
	
	@Override
	public double area(){
		return Math.PI * radius *radius;
	}
	
	@Override
	public boolean contains(Point2D.Double p){
		
		double plugin = Math.pow(p.getX() - center.getX() , 2) + Math.pow(p.getY() - center.getY() , 2) ;
		return (plugin <= Math.pow(radius, 2));
	}
	
	public boolean contains(Circle c){
		if( c.getRadius() > this.getRadius())
			return false;
		double centerDistance = Math.pow(c.getCenter().getX() - center.getX() , 2) + Math.pow(c.getCenter().getY() - center.getY() , 2);
		
		return centerDistance <= Math.pow(this.getRadius() - c.getRadius(), 2);
	}
	
	public static void main(String[] args){
		
		System.out.println("Test the constructor: ");
		Circle c1 = new Circle();
		System.out.println("c1: " + c1.toString());
		Circle c2;
		try {
			c2 = new Circle(new Point2D.Double(66.0, 88.0), 100.0);
			System.out.println("c2: " + c2.toString());
		} catch (InvalidShapeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\nTest the constructor, try invalid radius: ");
		Circle c3;
		try {
			c3 = new Circle(new Point2D.Double(66.0, 88.0), -100.0);
			System.out.println("c3: " + c3.toString());
		} catch (InvalidShapeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\nTest the setter: ");
		c1.setCenter(new Point2D.Double(1.0, 1.0));
		try {
			c1.setRadius(10.0);
			System.out.println("c1: " + c1.toString());
		} catch (InvalidShapeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\nTest the setter, try invalid radius: ");
		try {
			c1.setRadius(-1);
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTest area() of circle with radius 4, expect=16PI");
		Circle circle = new Circle();
		try {
			circle.setRadius(4.0);
			System.out.println(circle.area());
		} catch (InvalidShapeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\nTest if default Circle contains point(4.0/5.0, 3.0/5.0), expect=true: ");
		Circle c4 = new Circle();
		System.out.println(c4.contains(new Point2D.Double(4.0/5.0, 3.0/5.0)));
		
		System.out.println("\nTest if default Circle contains point(4.0/5.0, 3.0/5.0 + 0.001), expect=false: ");
		System.out.println(c4.contains(new Point2D.Double(4.0/5.0, 3.0/5.0 + 0.001)));
		
		System.out.println("\nTest if Circle((0, 0), 10) contains circle((3,4), 5), expect=true: ");
		try {
			Circle c5 = new Circle(new Point2D.Double(0.0,0.0), 10);
			Circle c6 = new Circle(new Point2D.Double(3.0,4.0), 5);
			System.out.println(c5.contains(c6));
			
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTest if Circle((0, 0), 10) contains circle((3,4), 5 + 0.001), expect=false: ");
		try {
			Circle c5 = new Circle(new Point2D.Double(0.0,0.0), 10);
			Circle c6 = new Circle(new Point2D.Double(3.0,4.0), 5+0.001);
			System.out.println(c5.contains(c6));
			
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
