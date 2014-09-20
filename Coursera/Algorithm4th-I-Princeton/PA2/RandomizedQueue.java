import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is 
 * chosen uniformly at random from items in the data structure.
 */
/*
 * Since the input is not stable, we cannot declare a fixed size of queue so 
 * we consider resizing array to implement this data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	public RandomizedQueue(){   // construct an empty randomized queue with length = 1
		tail = 0;
		numItems = 0;
		randomQueueArray = (Item[]) new Object[1];
	}
	public boolean isEmpty(){   // is the queue empty?
		boolean isEmpty = true;
		if(numItems!=0)
			isEmpty = false;
		return isEmpty;
	}
	public int size(){         // return the number of items on the queue
		return numItems;
	}
	public void enqueue(Item item){  // add the item to the tail position
		
		if(item==null)
			throw new NullPointerException("Cannot add a null item.");
		
		if(numItems==randomQueueArray.length){
			resize( 2*randomQueueArray.length );
		}
		randomQueueArray[tail++] = item;
		numItems+=1;
	}
	/*
	 * Since the order of RandomizedQueue is not important, it is not necessary to shift array to keep the order.
	 * We can just put the tail element into the position which was randomly choosen to be removed.
	 */
	public Item dequeue(){        // delete and return a random item
		
		if(isEmpty()==true)
			throw new NoSuchElementException ("No such element to dequeue.");
		
		int randomDequeue = StdRandom.uniform(tail);
		Item dequeueItem = randomQueueArray[randomDequeue];
		randomQueueArray[randomDequeue] = randomQueueArray[--tail];
		randomQueueArray[tail] = null;
		numItems--;
		if(numItems>0 && numItems==randomQueueArray.length/4)
			resize( randomQueueArray.length/2 );
		
		return dequeueItem;
	}
	public Item sample(){         // return (but do not delete) a random item
		
		if(isEmpty()==true)
			throw new NoSuchElementException ("No such element to dequeue.");
		
		int randomSample = StdRandom.uniform(tail);
		Item dequeueItem = randomQueueArray[randomSample];
		return dequeueItem;

	}
	public Iterator<Item> iterator(){  // return an independent iterator over items in random order
		
		return new RandomArrayIterator();
	}
	
	
	private int numItems;
	private Item[] randomQueueArray;
	private int tail;
	
	private class RandomArrayIterator implements Iterator<Item>{
		int[] orderArr;
		int current;
		public RandomArrayIterator(){
			current = 0;
			orderArr = new int[numItems];
			for(int i = 0; i<numItems; i++)
				orderArr[i] = i;
			StdRandom.shuffle(orderArr);
		}
		
		public boolean hasNext() {
			return current<numItems;
		}

		public Item next() {
			
			if( current>=numItems ) throw new NoSuchElementException();
				
			Item item = randomQueueArray[orderArr[current++]];
			
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException ("Cannot call remove() method of iterator.");
		}
	}
	
	private void resize(int capacity){
		Item[] temp = (Item[]) new Object[capacity];
		for(int i=0; i<numItems;i++){
			temp[i] = randomQueueArray[i];
		}
		randomQueueArray = temp;
	}
	
	public static void main(String[] args) {
	

	}
}
