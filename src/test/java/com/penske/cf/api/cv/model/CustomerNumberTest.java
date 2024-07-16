package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CustomerNumberTest {
	private CustomerNumber createTestSubject() {
		return new CustomerNumber();
	}

	@Test
	public void testSetGetDataProvider() throws Exception {
		CustomerNumber testSubject = createTestSubject();
		testSubject.setDataProvider("123123");
		assertEquals("123123", testSubject.getDataProvider());
	}
	
	@Test
	public void testSetGetPenske() throws Exception {
		CustomerNumber testSubject = createTestSubject();
		testSubject.setPenske("123123");
		assertEquals("123123", testSubject.getPenske());
	}
	
	@Test
	public void testEquals() throws Exception {
		CustomerNumber testSubject = createTestSubject();
		CustomerNumber anotherObject = new CustomerNumber();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		CustomerNumber testSubject = createTestSubject();
		CustomerNumber result = new CustomerNumber();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		CustomerNumber testSubject = createTestSubject();
		String result = "CustomerNumber(penske=null, dataProvider=null)";
		assertEquals(result, testSubject.toString());
	}
}
