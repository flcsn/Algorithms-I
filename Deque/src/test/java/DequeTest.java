import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class DequeTest {

	@Test
	public void dequeConstructorConstructsDeque() {
		Deque<String> deque = new Deque<String>();
		assertNotNull(deque);
	}
	
	@Test
	public void newlyConstructedDequeisEmpty() {
		Deque<String> deque = new Deque<String>();
		assertTrue(deque.isEmpty());
	}
	
	@Test
	public void addFirstAddsItem() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("first string added");
		assertFalse(deque.isEmpty());
	}
	
	@Test
	public void addLastAddsItem() {
		Deque<String> deque = new Deque<String>();
		deque.addLast("first string added");
		assertFalse(deque.isEmpty());
	}
	
	@Test
	public void addingNullThrowsIllegalArgumentException() {
		Deque<String> deque = new Deque<String>();
		Assert.assertThrows(IllegalArgumentException.class, () -> {
			deque.addFirst(null);
		});
		Assert.assertThrows(IllegalArgumentException.class, () -> {
			deque.addLast(null);
		});
	}
	
	@Test
	public void removeFirstRemovesFirstItem() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("first string added");
		deque.addFirst("second string added");
		assertTrue(deque.removeFirst().equals("second string added"));
		assertTrue(deque.removeFirst().equals("first string added"));
	}
	
	@Test
	public void removeLastRemovesLastItem() {
		Deque<String> deque = new Deque<String>();
		deque.addLast("first string added");
		deque.addLast("second string added");
		assertTrue(deque.removeLast().equals("second string added"));
		assertTrue(deque.removeLast().equals("first string added"));
	}
	
	@Test
	public void removingWhenDequeIsEmptyThrowsNoSuchElementException() {
		Deque<String> deque = new Deque<String>();
		Assert.assertThrows(NoSuchElementException.class, () -> {
			String item = deque.removeFirst();
		});
		Assert.assertThrows(NoSuchElementException.class, () -> {
			String item = deque.removeLast();
		});
	}
	
	@Test
	public void addingAndRemovingMultipleItemsConsecutively() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("first");
		deque.addLast("second");
		deque.addFirst("third");
		deque.addLast("fourth");
		assertTrue(deque.size() == 4);
		assertTrue(deque.removeLast().equals("fourth"));
		assertTrue(deque.removeFirst().equals("third"));
		assertTrue(deque.removeLast().equals("second"));
		assertTrue(deque.removeFirst().equals("first"));
		assertTrue(deque.size() == 0);
		assertTrue(deque.isEmpty());
	}
	
	@Test
	public void dequeIteratorDoesNotIterateOnEmptyDeque() {
		Deque<String> deque = new Deque<String>();
		Iterator<String> iterator = deque.iterator();
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void dequeIteratorIterates() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("first");
		deque.addLast("second");
		deque.addLast("third");
		Iterator<String> iterator = deque.iterator();
		assertTrue(iterator.next().equals("first"));
		assertTrue(iterator.next().equals("second"));
		assertTrue(iterator.next().equals("third"));
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});
	}
	
	@Test
	public void dequeIteratorRemoveMethodThrowsException() {
		Deque<String> deque = new Deque<String>();
		Iterator<String> iterator = deque.iterator();
		assertThrows(UnsupportedOperationException.class, () -> {
			iterator.remove();
		});
	}
	

}
