/*************************************************************************
 * Name:  Xin Zhang
 * Email: zhangxin0804@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

// immutable data type Point
public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class SlopeOrder implements Comparator<Point>{
    	public int compare(Point p1, Point p2){
    		
    		double slopeOfPoint1 = slopeTo(p1);
    		double slopeOfPoint2 = slopeTo(p2);
    		
    		if( slopeOfPoint1<slopeOfPoint2 )
    			return -1;
    		else if( slopeOfPoint1>slopeOfPoint2 )
    			return 1;
    		else
    			return 0;
    	}
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
       
  
    	// Treat the slope of a horizontal line segment as positive zero;
    	if( this.x!=that.x && this.y==that.y ){
    		return +0.0;
    	}else if( this.x==that.x && this.y!=that.y ){  // treat the slope of a vertical line segment as positive infinity;
    		return Double.POSITIVE_INFINITY;
    	}else if( this.x==that.x && this.y==that.y ){
    		return Double.NEGATIVE_INFINITY;  
    	}else{   		// treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
    		double slope;
    		slope = 1.0*( that.y - this.y )/( that.x - this.x );
    		return slope;
    	}

    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        
    	if( this.y < that.y )
    		return -1;
    	else if( this.y > that.y )
    		return 1;
    	else{
    		if( this.x > that.x )
    			return 1;
    		else if( this.x < that.x )
    			return -1;
    		else
    			return 0;
    	}
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
