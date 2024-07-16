package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProviderTest {
	private Provider createTestSubject() {
		return new Provider();
	}

	@Test
	public void testSetGetCustomers() throws Exception {
		Provider testSubject = createTestSubject();
		List<AuthorityCustomers> customers = new ArrayList<AuthorityCustomers>();
		testSubject.setCustomers(customers);
		assertEquals(customers, testSubject.getCustomers());
	}

	@Test
	public void testEquals() throws Exception {
		Provider testSubject = createTestSubject();
		Provider anotherObject = new Provider();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Provider testSubject = createTestSubject();
		Provider result = new Provider();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Provider testSubject = createTestSubject();
		String result = "Provider(customers=null)";
		assertEquals(result, testSubject.toString());
	}
}
