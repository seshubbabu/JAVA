package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AuthorityCustomersTest {
	private AuthorityCustomers createTestSubject() {
		return new AuthorityCustomers();
	}

	@Test
	public void testSetGetCustomerMasterId() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		testSubject.setCustomerMasterId(1);
		assertEquals(1, testSubject.getCustomerMasterId());
	}
	
	@Test
	public void testSetGetCustomerNumber() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		testSubject.setCustomerNumber("123123123");
		assertEquals("123123123", testSubject.getCustomerNumber());
	}

	@Test
	public void testSetGetUnits() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		List<Units> units = new ArrayList<>();
		testSubject.setUnits(units);
		assertEquals(units, testSubject.getUnits());
	}
	
	@Test
	public void testEquals() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		AuthorityCustomers anotherObject = new AuthorityCustomers();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		AuthorityCustomers result = new AuthorityCustomers();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		AuthorityCustomers testSubject = createTestSubject();
		String result = "AuthorityCustomers(customerMasterId=0, customerNumber=null, units=null)";
		assertEquals(result, testSubject.toString());
	}

}
