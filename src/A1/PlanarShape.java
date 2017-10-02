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

public interface PlanarShape {

	public double area();
	
	public boolean contains(Point2D.Double p);
}
