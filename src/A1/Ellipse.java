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
import java.awt.geom.Point2D.Double;

public class Ellipse implements PlanarShape{
	protected Point2D.Double center;
	private double hAxis;
	private double vAxis;
	private double area;
	
	
	public Ellipse(){
		this.center = new Point2D.Double(0.0, 0.0);
		this.hAxis = 4.0;
		this.vAxis = 3.0;
	}
	
	public Ellipse(Point2D.Double center, double hAxis, double vAxis) throws InvalidShapeException{
		if(hAxis <= 0 || vAxis <=0)
			throw new InvalidShapeException(" Invalid Axis!");
		
		this.center = center;
		this.hAxis = hAxis;
		this.vAxis = vAxis;
	}
	
	public double gethAxis() {
		return hAxis;
	}
	
    public double getvAxis() {
		return vAxis;
	}
    
    public void setCenter(Point2D.Double center) {
		this.center = center;
	}
    
    public void sethAxis(double hAxis) throws InvalidShapeException{
		if(hAxis <= 0)
			throw new InvalidShapeException(" Invalid hAxis!");
    	this.hAxis = hAxis;
	}
    
    public void setvAxis(double vAxis) throws InvalidShapeException{
    	if(vAxis <= 0)
			throw new InvalidShapeException(" Invalid vAxis!");
    	this.vAxis = vAxis;
	}
    
    public String toString(){
    	String s = "This ellipse has center point " + center.toString() + ", horizontal axis" + gethAxis() + ", and vertical "
    			+ "axis " + getvAxis() + ".";
    	return s;
    }
    
	@Override
	public double area() {
		area = Math.PI * hAxis * vAxis;
		
		return area;
	}

	@Override
	public boolean contains(Point2D.Double point) {
		
		double plugin = Math.pow(((point.getX() - center.getX()) / hAxis), 2) +
				Math.pow(((point.getY() - center.getY()) / vAxis), 2);
		return (plugin <= 1);
	}
	
	public static void main(String[] args) {
		
		System.out.println("Test the constructor: ");
		Ellipse e1 = new Ellipse();
		System.out.println("e1: " + e1.toString());
		Ellipse e2;
		try {
			e2 = new Ellipse(new Point2D.Double(66.0, 88.0), 100.0, 80.0);
			System.out.println("e2: " + e2.toString());
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTest the constructor, try invalid axis: ");
		Ellipse e3;
		try {
			e3 = new Ellipse(new Point2D.Double(66.0, 88.0), -100.0, 10.0);
			System.out.println("e3: " + e3.toString());
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTest the setter: ");
		e1.setCenter(new Point2D.Double(1.0, 1.0));
		try {
			e1.setvAxis(1.222222);
			System.out.println("e1: " + e1.toString());
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nTest the setter, try invalid axis: ");
		try {
			e1.sethAxis(-1.0);
		} catch (InvalidShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTest area() of default ellipse, expect=12PI: ");
		Ellipse ellipse = new Ellipse();
		System.out.println(ellipse.area());
		
		System.out.println("\nTest if default Ellipse contains point(1.0, 1.0), expect=true: ");
		Ellipse e4 = new Ellipse();
		System.out.println(e4.contains(new Point2D.Double(1.0, 1.0)));
		
		System.out.println("\nTest if default Circle contains point(4.0, 3.0), expect=false: ");
		System.out.println(e4.contains(new Point2D.Double(4.0, 3.0)));
		
		
		
	}

}
