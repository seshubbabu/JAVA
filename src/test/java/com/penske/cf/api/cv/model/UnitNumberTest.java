package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class UnitNumberTest {
	private UnitNumber createTestSubject() {
		return new UnitNumber();
	}

	@Test
	public void testSetGetDataProvider() throws Exception {
		UnitNumber testSubject = createTestSubject();
		testSubject.setDataProvider("123123");
		assertEquals("123123", testSubject.getDataProvider());
	}
	
	@Test
	public void testSetGetPenske() throws Exception {
		UnitNumber testSubject = createTestSubject();
		testSubject.setPenske("123123");
		assertEquals("123123", testSubject.getPenske());
	}
	
	@Test
	public void testSetGetCustomer() throws Exception {
		UnitNumber testSubject = createTestSubject();
		testSubject.setCustomer("123123");
		assertEquals("123123", testSubject.getCustomer());
	}
	
	@Test
	public void testEquals() throws Exception {
		UnitNumber testSubject = createTestSubject();
		UnitNumber anotherObject = new UnitNumber();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		UnitNumber testSubject = createTestSubject();
		UnitNumber result = new UnitNumber();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		UnitNumber testSubject = createTestSubject();
		String result = "UnitNumber(penske=null, dataProvider=null, customer=null)";
		assertEquals(result, testSubject.toString());
	}
}
