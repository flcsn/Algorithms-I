import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Item[] items;
	private int currentNumberOfItems;

    // construct an empty deque
    public Deque() {
    	this.items = (Item[]) new Object[1];
    	currentNumberOfItems = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return currentNumberOfItems == 0;
    }

    // return the number of items on the deque
    public int size() {
    	return currentNumberOfItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	items[0] = item;
    	currentNumberOfItems++;
    	if (currentNumberOfItems == items.length) grow();
    }

    // add the item to the back
    public void addLast(Item item) {
    	items[currentNumberOfItems] = item;
    	currentNumberOfItems++;
    	if (currentNumberOfItems == items.length) grow();
    }
    
    private void grow() {
    	Item[] newArray = (Item[]) new Object[items.length*2];
    	for (int i = 0; i < items.length; i++) newArray[i] = items[i];
    	items = newArray;
    }

    // remove and return the item from the front
    //public Item removeFirst()

    // remove and return the item from the back
    //public Item removeLast()

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	return new StackIterator();
    }
    
    private class StackIterator implements Iterator<Item> {
    	private int j = 0;
    	
    	public boolean hasNext() {return j != items.length;}
    	public void remove() {;}
    	public Item next() {return items[j++];}
    }

    // unit testing (required)
    //public static void main(String[] args)

}