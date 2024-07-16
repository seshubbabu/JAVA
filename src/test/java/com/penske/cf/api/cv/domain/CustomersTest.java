package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CustomersTest {
	private Customers createTestSubject() {
		return new Customers();
	}

	@Test
	public void testSetGetCustomerMasterId() throws Exception {
		Customers testSubject = createTestSubject();
		testSubject.setCustomerMasterId(1);
		assertEquals(1, testSubject.getCustomerMasterId());
	}
	
	@Test
	public void testSetGetCustomerNumber() throws Exception {
		Customers testSubject = createTestSubject();
		testSubject.setCustomerNumber("123123123");
		assertEquals("123123123", testSubject.getCustomerNumber());
	}

	@Test
	public void testSetGetUnits() throws Exception {
		Customers testSubject = createTestSubject();
		List<Units> units = new ArrayList<>();
		testSubject.setUnits(units);
		assertEquals(units, testSubject.getUnits());
	}
	
	@Test
	public void testEquals() throws Exception {
		Customers testSubject = createTestSubject();
		Customers anotherObject = new Customers();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Customers testSubject = createTestSubject();
		Customers result = new Customers();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Customers testSubject = createTestSubject();
		String result = "Customers(customerMasterId=0, customerNumber=null, units=null)";
		assertEquals(result, testSubject.toString());
	}

}
