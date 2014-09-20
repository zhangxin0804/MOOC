import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

	public Deque() {         // construct an empty deque
		first = null;
		last = null;
		numItems = 0;
	}
	public boolean isEmpty(){   // is the deque empty?
		
		boolean isEmpty = false;
		if(numItems==0||first==null||last==null)
			isEmpty = true;
		return isEmpty;
	}
	public int size(){     // return the number of items on the deque
		return numItems;
	}
	public void addFirst(Item item){   // insert the item at the front
		
		if(item==null)
			throw new NullPointerException("Cannot add a null item.");	
			
		Node tempFirst = first;
		first = new Node(item);
		numItems+=1;
		first.next = tempFirst;
		if(numItems==1)
			last = first;
		else
			tempFirst.prev = first;
	}
	public void addLast(Item item){    // insert the item at the end
		
		if(item==null)
			throw new NullPointerException("Cannot add a null item.");
		
		Node tempLast = last;
		last = new Node(item);
		numItems+=1;
		last.prev = tempLast;
		if(numItems==1)
			first = last;
		else
			tempLast.next = last;
	}
	
	public Item removeFirst(){     // delete and return the item at the front
		
		if( isEmpty()==true )
			throw new NoSuchElementException ("No such element to remove.");
		
		Item frontItem = null;
		frontItem = first.data;
		numItems-=1;
		if(numItems==0){
			first = null;
			last = null;
		}else{
			first = first.next;
			first.prev = null;
		}
		return frontItem;
	}
	
	public Item removeLast(){      // delete and return the item at the end
		
		if( isEmpty()==true )
			throw new NoSuchElementException ("No such element to remove.");
		
		Item lastItem = null;
		lastItem = last.data;
		numItems-=1;
		if(numItems==0){
			first = null;
			last = null;
		}else{
			last = last.prev;
			last.next = null;
		}
		return lastItem;
	}
	
	public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
		return new ListIterator();
	}
	
	
	private Node first;
	private Node last;
	private int  numItems;
	
	private class Node{
		Node next;
		Node prev;
		Item data;
		
		Node(Item temp){
			next = null;
			prev = null;
			data = temp;
		}
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current;
		
		ListIterator(){
			current = first;
		}
		
		public boolean hasNext() {
			return current!=null;
		}

		public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next; 
            return item;
		}

		public void remove() {
			throw new UnsupportedOperationException ("Cannot call remove() method of iterator.");
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<String> test = new Deque<String>();
		test.addFirst("a");test.addFirst("b");test.addFirst("c");test.addFirst("d");test.addFirst("e");
		test.addFirst("f");test.addFirst("g");test.addFirst("h");test.addFirst("i");test.addFirst("j");
		
		Iterator<String> iter = test.iterator();
		while(iter.hasNext()){
			String temp = iter.next();
			StdOut.println(temp);
		}
	}

}
