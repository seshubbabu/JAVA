package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AuthorityCustomerTest {
	private AuthorityCustomer createTestSubject() {
		return new AuthorityCustomer();
	}

	@Test
	public void testSetGetCustomers() throws Exception {
		AuthorityCustomer testSubject = createTestSubject();
		List<AuthorityCustomers> customers = new ArrayList<AuthorityCustomers>();
		testSubject.setCustomers(customers);
		assertEquals(customers, testSubject.getCustomers());
	}

	@Test
	public void testEquals() throws Exception {
		AuthorityCustomer testSubject = createTestSubject();
		AuthorityCustomer anotherObject = new AuthorityCustomer();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		AuthorityCustomer testSubject = createTestSubject();
		AuthorityCustomer result = new AuthorityCustomer();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		AuthorityCustomer testSubject = createTestSubject();
		String result = "AuthorityCustomer(customers=null)";
		assertEquals(result, testSubject.toString());
	}

}
