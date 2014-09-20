import java.util.NoSuchElementException;


public class Subset {

	public static void main(String[] args) {
		
		int k = Integer.parseInt(args[0]);
		int total;
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		
		while(!StdIn.isEmpty()){
			test.enqueue(StdIn.readString());
		}
		if(k<0||k>test.size())
			throw new IndexOutOfBoundsException ("Invalid value of k");
		// ctrl+z in windows will terminate the user interactive input
		
		total = test.size();
	
		for(int i=0;i<total-k;i++){
			test.dequeue();
		}
		for(String s: test)
		  StdOut.println(s);
		 
	}
}
