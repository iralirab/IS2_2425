package es.unican.is2.ListaOrdenada;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaOrdenadaTest {
	ListaOrdenada<Integer> sut;
	
	@BeforeEach
	public void setUp() {
		sut = new ListaOrdenada<>();
	}
	
	@Test
	public void testGet() {
		// [].
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
		
		// [1].
		sut.add(1);
		
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
		assertEquals(1, sut.get(0));
		assertEquals(1, sut.size());
		
		// [1,2,3].
		sut.add(2);
		sut.add(3);
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(3));
		assertEquals(1, sut.get(0));
		assertEquals(2, sut.get(1));
		assertEquals(3, sut.get(2));
		assertEquals(3, sut.size());
	}

	@Test
	public void testAdd() {
		// [].
		assertThrows(NullPointerException.class, () -> sut.add(null));
		
		sut.add(5);
		
		assertEquals(5, sut.get(0));
		assertEquals(1, sut.size());
		sut.remove(0);
		
		// [1,3,4].
		sut.add(1);
		sut.add(3);
		sut.add(4);
		
		sut.add(5);
		assertEquals(5, sut.get(3));
		assertEquals(4, sut.size());
		sut.remove(3);
		
		sut.add(0);
		assertEquals(0, sut.get(0));
		assertEquals(4, sut.size());
		sut.remove(0);

		sut.add(2);
		assertEquals(2, sut.get(1));
		assertEquals(4, sut.size());
		sut.remove(1);

		sut.add(3);
		assertEquals(3, sut.get(1));
		assertEquals(3, sut.get(2));
		assertEquals(4, sut.size());
		sut.remove(2);
	}
	
	@Test
	public void testRemove() {
		// [].
		assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));
		
		// [1].
		sut.add(1);
		
		assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
		assertEquals(1, sut.remove(0));
		assertEquals(0, sut.size());
		
		// [1,2,3].
		sut.add(1);
		sut.add(2);
		sut.add(3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(3));
		assertEquals(1, sut.remove(0));
		assertEquals(2, sut.size());
		assertEquals(2, sut.get(0));
		assertEquals(3, sut.get(1));
		sut.add(1);
		
		assertEquals(2, sut.remove(1));
		assertEquals(2, sut.size());
		assertEquals(1, sut.get(0));
		assertEquals(3, sut.get(1));
		sut.add(2);
		
		assertEquals(3, sut.remove(2));
		assertEquals(2, sut.size());
		assertEquals(1, sut.get(0));
		assertEquals(2, sut.get(1));
	}
}
