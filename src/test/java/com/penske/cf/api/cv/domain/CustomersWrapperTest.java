package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CustomersWrapperTest {
	private CustomersWrapper createTestSubject() {
		return new CustomersWrapper();
	}

	@Test
	public void testSetGetAuthorities() throws Exception {
		CustomersWrapper testSubject = createTestSubject();
		List<Authority> authorities = new ArrayList<Authority>();
		testSubject.setAuthorities(authorities);
		assertEquals(authorities, testSubject.getAuthorities());
	}
	
	@Test
	public void testSetGetCustomers() throws Exception {
		CustomersWrapper testSubject = createTestSubject();
		List<Customer> customers = new ArrayList<Customer>();
		testSubject.setCustomers(customers);
		assertEquals(customers, testSubject.getCustomers());
	}

	@Test
	public void testEquals() throws Exception {
		CustomersWrapper testSubject = createTestSubject();
		CustomersWrapper anotherObject = new CustomersWrapper();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		CustomersWrapper testSubject = createTestSubject();
		CustomersWrapper result = new CustomersWrapper();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		CustomersWrapper testSubject = createTestSubject();
		String result = "CustomersWrapper(customers=null, authorities=null)";
		assertEquals(result, testSubject.toString());
	}

}
