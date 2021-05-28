import static org.junit.Assert.*;

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
	public void arrayGrowsWhenMoreItemsAreAdded() {
		Deque<String> deque = new Deque<String>();
		deque.addLast("first string added");
		deque.addFirst("second string added");
		deque.addFirst("third string added");
		assertTrue(deque.size() == 3);
	}
	
	
	/*@Test
	public void addFirstAddsItemToFront() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("first string added");
		deque.addFirst("second string added");
		assertEquals("second string added", deque.items[0]);
	}*/
	

}
