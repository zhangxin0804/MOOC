import java.util.Arrays;


public class Brute {

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
        
        // Print each line segment as an ordered sequence of its constituent points, separated by " -> "
         Arrays.sort(pointsSet); // sorting as natural order      
        
        // The order of growth of the running time of your program should be N4 in the worst case and it should 
        // use space proportional to N.
        for( int i = 0; i < N; i++)
        	for( int j = i + 1; j < N; j++)
        		for( int m = j+1; m < N; m++)
        			for( int n = m+1; n < N; n++)
        			{
        				if( ( pointsSet[i].slopeTo(pointsSet[j]) == pointsSet[i].slopeTo(pointsSet[m]) ) &&
        						( pointsSet[i].slopeTo(pointsSet[j])==pointsSet[i].slopeTo(pointsSet[n])) ){
        					
        					StdOut.println( pointsSet[i].toString() + " -> " + 
        									pointsSet[j].toString() + " -> " + 
        									pointsSet[m].toString() + " -> " + 
        									pointsSet[n].toString()
        								   );
        					pointsSet[i].drawTo(pointsSet[n]);
        					
        				}
        			}
   
        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();
	}

}
