import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int currentNumberOfItems;
	private Node front;
	private Node end;

    // construct an empty deque
    public Deque() {
    	currentNumberOfItems = 0;
    	front = new Node();
    	end = front;
    }
    
    private class Node {
    	Node next;
    	Item item;
    	Node previous;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return front == end;
    }

    // return the number of items on the deque
    public int size() {
    	return currentNumberOfItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null");
    	Node newItem = new Node();
    	newItem.item = item;
    	newItem.next = front;
    	front.previous = newItem;
    	front = newItem;

    	currentNumberOfItems++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null");
    	Node newEnd = new Node();
    	end.item = item;
    	end.next = newEnd;
    	newEnd.previous = end;
    	end = newEnd;
    	
    	currentNumberOfItems++;
    }

    // remove and return the item from the front
    public Item removeFirst() { 
    	if (this.isEmpty()) throw new NoSuchElementException("deque is empty");
    	Item frontItem = front.item;
    	front = front.next;
    	front.previous = null;
    	
    	currentNumberOfItems--;
    	return frontItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (this.isEmpty()) throw new NoSuchElementException("deque is empty");
    	end = end.previous;
    	Item endItem = end.item;
    	end.next = null;
    	
    	currentNumberOfItems--;
    	return endItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
    	private Node current = front;
    	
    	public boolean hasNext() { return current.item != null; }
    	public void remove() { throw new UnsupportedOperationException("cannot call remove");}
    	public Item next() {
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Deque<String> deque = new Deque<String>();
    	System.out.println(deque);
    	System.out.println("Adding in order: third, first, second, fourth...");
		deque.addFirst("first");
		deque.addLast("second");
		deque.addFirst("third");
		deque.addLast("fourth");
		System.out.println("The deque has " + deque.size() + " nodes.");
		System.out.println("");
		System.out.println("From front to end, this deque has the following nodes: ");
		Iterator<String> iterator = deque.iterator();
		while (iterator.hasNext()) { System.out.print(iterator.next() + " ");}
		System.out.println("");
		System.out.println("At front: " + deque.removeFirst());
		System.out.println("At end: " + deque.removeLast());
		System.out.println("The deque now has " + deque.size() + " nodes.");
		System.out.println("Removing...");
		System.out.println("At front: " + deque.removeFirst());
		System.out.println("At end: " + deque.removeLast());
		System.out.println("The deque now has " + deque.size() + " nodes.");
    }

}