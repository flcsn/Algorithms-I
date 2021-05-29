import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int numberOfItems;
	private int last;
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	items = (Item[]) new Object[1];
    	numberOfItems = 0;
    	last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return numberOfItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return numberOfItems;
    }

    // add the item
    public void enqueue(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null");
    	items[last++] = item;
    	numberOfItems++;
    	
    	if (last == items.length) grow();
    }
    
    private void grow() {
    	Item[] newArray = (Item[]) new Object[items.length*2];
    	for (int i = 0; i < items.length; i++) { newArray[i] = items[i];}
    	items = newArray;
    }

    // remove and return a random item
    public Item dequeue() {
    	if (this.isEmpty()) throw new NoSuchElementException("queue is empty");
    	int indexToRemove = StdRandom.uniform(items.length);
    	while (items[indexToRemove]== null) indexToRemove = StdRandom.uniform(items.length);
    	Item item = items[indexToRemove];
    	items[indexToRemove] = null;
    	
    	numberOfItems--;
    	return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (this.isEmpty()) throw new NoSuchElementException("queue is empty");
    	Item item = items[StdRandom.uniform(items.length)];
    	while (item == null) item = items[StdRandom.uniform(items.length)];
    	return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    	return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
    	private int counter = 0;
    	private Item[] itemsCopy = (Item[]) new Object[items.length];
    	
    	private RandomizedQueueIterator() {
    		for (int i = 0; i < items.length; i++) { itemsCopy[i] = items[i];}
    	}
    	
    	public boolean hasNext() {
    		if (counter != numberOfItems) return true;
    		else return false;
    	}
    	public void remove() { throw new UnsupportedOperationException(); }
    	public Item next() {
    		int indexToRemove = StdRandom.uniform(itemsCopy.length);
        	while (itemsCopy[indexToRemove]== null) indexToRemove = StdRandom.uniform(itemsCopy.length);
        	Item item = itemsCopy[indexToRemove];
        	itemsCopy[indexToRemove] = null;
        	counter++;
        	return item;
    	}
    }

    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<String> q = new RandomizedQueue<String>();
    	System.out.println(q);
    	System.out.println("Enqueueing in order: first, second, third, fourth...");
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		q.enqueue("fourth");
		System.out.println("The queue has " + q.size() + " elements.");
		System.out.println("");
		System.out.println("Chosen randomly, this queue has the following elements: ");
		Iterator<String> iterator = q.iterator();
		while (iterator.hasNext()) { System.out.print(iterator.next() + " ");}
		System.out.println("");
		System.out.println("Removing...");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println("The queue has " + q.size() + " elements.");
		System.out.println("Removing...");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println("The queue has " + q.size() + " elements.");
    }

}