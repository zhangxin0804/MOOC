
public class Percolation {

	private WeightedQuickUnionUF percolateSites;  // as Sites data structure
	private WeightedQuickUnionUF percolateSites2;
	
	private int numSites;                         // number of Sites
	private boolean[] siteStates;                 // mark open or blocked
	
	/*
	 Write the open() method and the Percolation() constructor. The open() method should do three things. 
	 First, it should validate the indices of the site that it receives. Second, it should somehow mark the 
	 site as open. Third, it should perform some sequence of WeightedQuickUnionUF operations that links the 
	 site in question to its open neighbors. The constructor and instance variables should facilitate the 
	 open() method's ability to do its job.
	*/
	
	public Percolation(int N){  							        // create N-by-N grid, with all sites blocked


		if (N <= 0 ) throw new IllegalArgumentException("N index are out of bounds");
		
		numSites = N * N;   		 						        // Virtual top site and Virtual bottom site not included
		percolateSites = new WeightedQuickUnionUF( numSites+2 );    // index=0 is virtual top and index = N*N+1 is virtual bottom
		
		percolateSites2 = new WeightedQuickUnionUF( numSites + 1 );
		
		siteStates = new boolean[numSites+2];  						// siteStates = false when initialized
		siteStates[0] = true;                                       // virtual top is open
		siteStates[numSites+1] = true;                              // virtual bottom is open
	}
	
	public void open(int i, int j){  						        // open site (row i, column j) if it is not already
		
		validateIndices(i,j);
		int N = (int)Math.sqrt(numSites);
		
		if(isOpen(i,j)==false){
			int siteIndex = xyTo1D(i,j);
			int second = siteIndex;
			siteStates[siteIndex] = true;
			
			if(N==1){
				percolateSites.union(siteIndex, 0);
				percolateSites.union(siteIndex, numSites+1);
				percolateSites2.union(siteIndex,0);
			}else{
				if(i==1){
					percolateSites.union(siteIndex, 0);          // top row and i==1, union new site to virtual top
					percolateSites2.union(siteIndex,0);
				}else if(i==N)
					percolateSites.union(siteIndex, numSites+1);        // bottom row and i==n, Uunion new site to virtual bottom
			}
			
			if( i!=1 && isOpen(i-1,j)==true ){
				int first = xyTo1D(i-1,j);
				percolateSites.union(first, second);	
				percolateSites2.union(first, second);
			}
			if( i!=N && isOpen(i+1,j)==true ){
				int first = xyTo1D(i+1,j);
				percolateSites.union(first, second);
				percolateSites2.union(first, second);
			}
			if( j!=1 && isOpen(i,j-1)==true ){
				int first = xyTo1D(i,j-1);
				percolateSites.union(first, second);
				percolateSites2.union(first, second);
			}
			if( j!=N &&  isOpen(i,j+1)==true ){
				int first = xyTo1D(i,j+1);
				percolateSites.union(first, second);
				percolateSites2.union(first, second);
			}
		}
	}
	/*
	 Test the open() method and the Percolation() constructor. These tests should be in main(). 
	 An example of a simple test is to call open(1, 1) and open(1, 2), and then to ensure that the 
	 two corresponding entries are connected (using .connected() in WeightedQuickUnionUF).
	 */
	
	public boolean isOpen(int i, int j){  // is site (row i, column j) open?
		
		validateIndices(i,j);
		
		int siteIndex = 0;
		boolean isOpen = false;
		    
		siteIndex = xyTo1D(i,j);
		if( siteStates[siteIndex]==false ) isOpen = false;
		else isOpen = true;
			
		return isOpen;
	}
	
	public boolean isFull(int i, int j){  // is site (row i, column j) full?
		
		boolean isFull = false;
		int siteIndex = 0;
		validateIndices(i,j);
		
		if(isOpen(i,j)==true){
			siteIndex = xyTo1D(i,j);
			if( percolateSites.connected(siteIndex, 0)==true ){
				if( percolateSites2.connected(siteIndex, 0)==true)
					isFull = true;
				else
					isFull = false;
			}
		}
		return isFull;
	}
	
	
	public boolean percolates(){   // does the system percolate?
		
		boolean isPercolates = false;
		if( percolateSites.connected(0, numSites+1)==true )
			isPercolates = true;
		else
			isPercolates = false;
		return isPercolates;
	}
	
	
	public static void main(String[] args) {

		Percolation test = new Percolation(4);
		
		test.open(1, 1);test.open(1, 2);test.open(3, 2);test.open(2, 2);test.open(4, 1);test.open(4, 2);
		if(test.percolates()==true)
			StdOut.println("Percolates");
		else
			StdOut.println("Not Percolates");	
	}
	
/*
 You will need to come up with a scheme for uniquely mapping 2D coordinates to 1D coordinates. 
 We recommend writing a private method with a signature along the lines of int xyTo1D(int, int) 
 that performs this conversion. You will need to utilize the percolation grid size when writing 
 this method. Writing such a private method (instead of copying and pasting a conversion formula 
 multiple times throughout your code) will greatly improve the readability and maintainability of 
 your code. In general, we encourage you to write such modules wherever possible. Directly test 
 this method using the main() function of Percolation.
 */
	private int xyTo1D(int row, int column){
		int N = (int)Math.sqrt(numSites);
		int oneDimensional = (row-1)*N + column;
		return oneDimensional;
	}
	
/*
 Since each method is supposed to throw an exception for invalid indices, you should write a private 
 method which performs this validation process.
 */
	private boolean validateIndices(int row, int column){
		
		int N = (int)Math.sqrt(numSites);
		
		if (row <= 0 || row > N) throw new IndexOutOfBoundsException("row index out of bounds");
		if (column <= 0 || column > N) throw new IndexOutOfBoundsException("column index out of bounds");
		return true;
	}
}
