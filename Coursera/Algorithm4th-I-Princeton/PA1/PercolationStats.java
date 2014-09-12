
public class PercolationStats {

	private int Ttimes;
	private double[] uArray;

	
	public PercolationStats(int N, int T){      // perform T independent computational experiments on an N-by-N grid	
		
		if (N <= 0 || T <= 0 ) throw new IllegalArgumentException("N or T index are out of bounds");
		
		int Nsize;
		Nsize = N; 
		Ttimes = T;

		uArray = new double[Ttimes];
		
		int repeat;
		for( repeat = 0; repeat < T; repeat = repeat+1 ){
			// special cases, if N = 1 or if T = 1 
			Percolation percolationInstance = new Percolation(Nsize);
			
			int openNum = 0;
			int row = 0;
			int column = 0;
			row = StdRandom.uniform(1, Nsize+1);
			column = StdRandom.uniform(1, Nsize+1);
			
			while( percolationInstance.percolates()==false ){
				
				if( percolationInstance.isOpen(row, column) == true ){
					row = StdRandom.uniform(1, Nsize+1);
					column = StdRandom.uniform(1, Nsize+1);
					continue;
				}else{
					percolationInstance.open(row, column);
					openNum = openNum + 1;
					row = StdRandom.uniform(1, Nsize+1);
					column = StdRandom.uniform(1, Nsize+1);
				}
			}
			uArray[repeat] = 1.0*openNum/(Nsize*Nsize);
		}
	}
	public double mean(){                      // sample mean of percolation threshold 
		return StdStats.mean(uArray);
	}
	 public double stddev(){                   // sample standard deviation of percolation threshold	
		
	/*
	 What should stddev() return if T equals 1? The sample standard deviation is undefined. 
	 We recommend returning Double.NaN.
	 */
		 if(Ttimes==1)  
			 return Double.NaN;
		 else
			 return StdStats.stddev(uArray);
	 }
	 public double confidenceLo(){             // returns lower bound of the 95% confidence interval
		 return ( mean() - 1.96*stddev()/Math.sqrt(Ttimes) );
	 }
	 public double confidenceHi(){             // returns upper bound of the 95% confidence interval
		 return ( mean() + 1.96*stddev()/Math.sqrt(Ttimes) );
	 }

		
	public static void main(String[] args) {
		
		int N = 0;
		int T = 0;
		
		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		
		PercolationStats percolateStatInstance = new PercolationStats(N,T);
		StdOut.println("mean	= " + percolateStatInstance.mean());
		StdOut.println("stddev	= " + percolateStatInstance.stddev());
		StdOut.println("95% confidence interval	 = " + percolateStatInstance.confidenceLo() + ", " + percolateStatInstance.confidenceHi());
		
	} 
}
