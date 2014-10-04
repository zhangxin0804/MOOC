import java.util.Arrays;

/*
Fast algorithm.
	Implement the SLOPE_ORDER comparator in Point. The complicating issue is that the comparator needed to compare 
the slopes that two points q and r make with a third point p, which changes from sort to sort. To do this include a 
public and final (but not static) instance variable SLOPE_ORDER in Point of type Comparator<Point>. This Comparator 
has a compare() method so that compare(q, r) compares the slopes that q and r make with the invoking object p. Implement 
the sorting solution. Watch out for corner cases. Don't worry about 5 or more points on a line segment yet.

Watch out for corner cases. For example, horizontal, vertical, or degenerate situation, etc.
Don't worry about 5 or more points on a line segment yet.

A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force 
solution described above. Given a point p, the following method determines whether p participates in a set of 4 or 
more collinear points.

1. Think of p as the origin.
2. For each other point q, determine the slope it makes with p.
3. Sort the points according to the slopes they makes with p.
4. Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, 
   together with p, are collinear.

Applying this method for each of the N points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.

*/

public class Fast {

	public static void main(String[] args) {
		
		// rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] pointsSet = new Point[N];     // use a Point type array to save all points
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pointsSet[i] = new Point(x, y);
            pointsSet[i].draw();
        }
        Arrays.sort(pointsSet); // sorting as natural order    
        
        /*
        System.out.println("Natural order result is: ");
        for(int i = 0;i<pointsSet.length-1;i++)
        	 StdOut.print(pointsSet[i].toString() + " -> ");
        StdOut.println(pointsSet[pointsSet.length-1].toString());
       */
        
        Point[] sortSlopeArray = new Point[N];     // use a Point type array to save all points
        // When check adjacent points, we can check them in sequential order since it can avoid duplicate colinear points
        // combination. That's the reason why the condition in outer loop is i<N-3 not i<N
        for(int i = 0; i < N - 3; i++){
        	for(int j = i; j < N; j++){
        		sortSlopeArray[j] = pointsSet[j];
        	}
        	Arrays.sort(sortSlopeArray, i+1, N, sortSlopeArray[i].SLOPE_ORDER);
        	Arrays.sort(sortSlopeArray, 0, i, sortSlopeArray[i].SLOPE_ORDER);
        	int start = i+1;
        	int end = i+2;
        	int first = 0;
        	while( end<N ){
        		double equalSlope = sortSlopeArray[i].slopeTo(sortSlopeArray[start]);
        		while( end<N && (equalSlope==sortSlopeArray[i].slopeTo(sortSlopeArray[end])) )
        			end++;
        		
        		
        		if( (end-start)>=3 ){
        			double previousSlope = Double.NEGATIVE_INFINITY;
        			while( first<i ){
        				previousSlope = sortSlopeArray[i].slopeTo(sortSlopeArray[first]);
        				if(previousSlope<equalSlope)
        					first++;
        				else
        					break;
        			}
        			if( previousSlope!=equalSlope ){
        				sortSlopeArray[i].drawTo(sortSlopeArray[end-1]);
        				String output = sortSlopeArray[i].toString() + " -> ";
        				for (int k = start; k < end-1; k++)
        					output += (sortSlopeArray[k].toString() + " -> ");
        				output += sortSlopeArray[end-1].toString();
        				StdOut.println(output);
        			}
        		}
        		start = end;
        		end = end + 1;
        	}
        }
        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();
	}
}
