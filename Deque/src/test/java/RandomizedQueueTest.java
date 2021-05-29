import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

	@Test
	public void constructorConstructsNewObjectInstance() {
		RandomizedQueue q = new RandomizedQueue();
		assertNotNull(q);
	}
	
	@Test
	public void newObjectInstanceIsEmpty() {
		RandomizedQueue q = new RandomizedQueue();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void returnsNumberOfItemsInArray() {
		RandomizedQueue q = new RandomizedQueue();
		assertTrue(q.size() == 0);
	}
	
	@Test
	public void enqueueAddsItem() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		assertFalse(q.isEmpty());
		assertTrue(q.size() == 1);
	}
	
	@Test
	public void canCallEnqueueMultipleTimes() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		assertTrue(q.size() == 3);
	}
	
	@Test
	public void passingNullIntoEnqueueThrowsException() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		assertThrows(IllegalArgumentException.class, () -> {
			q.enqueue(null);
		});
	}
	
	@Test
	public void sampleReturnsAnItem() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		assertTrue(q.sample() != null);
		assertTrue(q.size() == 3);
	}
	
	@Test
	public void sampleWhenQueueIsEmptyThrowsException() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		assertThrows(NoSuchElementException.class, () -> {
			String string = q.sample();
		});
	}
	
	@Test
	public void dequeueReturnsAndRemovesAnItem() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		String string1 = q.dequeue();
		String string2 = q.dequeue();
		String string3 = q.dequeue();
		assertTrue(string1 != string2);
		assertTrue(string2 != string3);
		assertTrue(string1 != string3);
		assertTrue(q.size() == 0);
	}
	
	@Test
	public void dequeueWhenQueueIsEmptyThrowsException() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		assertThrows(NoSuchElementException.class, () -> {
			String string = q.dequeue();
		});
	}
	
	@Test
	public void iteratorDoesNotIterateOverEmptyQueue() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		Iterator<String> iterator = q.iterator();
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void iteratorIteratesAsManyTimesAsThereAreItems() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		Iterator<String> iterator = q.iterator();
		int count = 0;
		while (iterator.hasNext()) { 
			iterator.next();
			count++;
		}
		assertTrue(count == 3);
	}
	
	@Test
	public void iteratorIteratesOverItemsInQueue() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		q.enqueue("first");
		q.enqueue("second");
		q.enqueue("third");
		Iterator<String> iterator = q.iterator();
		String string1 = iterator.next();
		String string2 = iterator.next();
		String string3 = iterator.next();
		assertTrue(string1 != string2);
		assertTrue(string2 != string3);
		assertTrue(string1 != string3);
	}
	
	@Test
	public void iteratorRemoveMethodThrowsException() {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		Iterator<String> iterator = q.iterator();
		assertThrows(UnsupportedOperationException.class, () -> {
			iterator.remove();
		});
	}
	

}
