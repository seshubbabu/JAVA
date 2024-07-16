package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ExistsTest {
	private Exists createTestSubject() {
		return new Exists();
	}

	@Test
	public void testSetGetDataProvider() throws Exception {
		Exists testSubject = createTestSubject();
		testSubject.setDataProvider(true);
		assertEquals(true, testSubject.isDataProvider());
	}
	
	@Test
	public void testSetGetPenske() throws Exception {
		Exists testSubject = createTestSubject();
		testSubject.setPenske(true);
		assertEquals(true, testSubject.isPenske());
	}
	
	@Test
	public void testSetGetCustomer() throws Exception {
		Exists testSubject = createTestSubject();
		testSubject.setCustomer(true);
		assertEquals(true, testSubject.isCustomer());
	}
	
	@Test
	public void testEquals() throws Exception {
		Exists testSubject = createTestSubject();
		Exists anotherObject = new Exists();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Exists testSubject = createTestSubject();
		Exists result = new Exists();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Exists testSubject = createTestSubject();
		String result = "Exists(penske=false, dataProvider=false, customer=false)";
		assertEquals(result, testSubject.toString());
	}
}
