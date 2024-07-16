package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PenskeTest {
	private Penske createTestSubject() {
		return new Penske();
	}

	@Test
	public void testSetGetCustomers() throws Exception {
		Penske testSubject = createTestSubject();
		List<AuthorityCustomers> customers = new ArrayList<AuthorityCustomers>();
		testSubject.setCustomers(customers);
		assertEquals(customers, testSubject.getCustomers());
	}

	@Test
	public void testEquals() throws Exception {
		Penske testSubject = createTestSubject();
		Penske anotherObject = new Penske();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Penske testSubject = createTestSubject();
		Penske result = new Penske();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Penske testSubject = createTestSubject();
		String result = "Penske(customers=null)";
		assertEquals(result, testSubject.toString());
	}

}
