import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {
	
	@Test
	public void testPushBack() {
		ObjectQueue q = new ObjectQueue();
		q.pushBack(1);
		q.pushBack(2);
		assertEquals(q.back(), 2);
	}

	@Test
	public void testPushFront() {
		ObjectQueue q = new ObjectQueue();
		q.pushFront("Dog");
		q.pushFront("Cat");
		assertEquals(q.front(), "Cat");
	}
	
	@Test
	public void testSize() {
		ObjectQueue q = new ObjectQueue();
		q.pushBack('a');
		q.pushBack('b');
		q.pushBack('c');
		assertEquals(q.size(), 3);
	}
	
	@Test
	public void testNullHead() {
		ObjectQueue q = new ObjectQueue();
		assertNull(q.front());
	}
	
	@Test
	public void testNullTail() {
		ObjectQueue q = new ObjectQueue();
		assertNull(q.back());
	}
	
	@Test
	public void testPopBack() {
		ObjectQueue q = new ObjectQueue();
		q.pushBack('a');
		q.pushBack('b');
		q.pushBack('c');
		assertEquals(q.back(), q.popBack());
	}
	
	@Test
	public void testPopFront() {
		ObjectQueue q = new ObjectQueue();
		q.pushBack('a');
		q.pushBack('b');
		q.pushBack('c');
		assertEquals(q.front(), q.popFront());
	}
	
	@Test
	public void testToArray() {
		ObjectQueue q = new ObjectQueue();
		q.pushBack(1);
		q.pushBack(2);
		Object a = new ObjectBox();
		a = 1;
		Object b = new ObjectBox();
		b = 2;
		Object[] arr = {a, b};
		assertArrayEquals(q.toArray(q), arr);
	}
	
	@Test
	public void testClear() {
		ObjectQueue q = new ObjectQueue();
		q.pushFront(1);
		q.pushFront(2);
		q.clear();
		assertTrue(q.size() == 0);
	}
}
