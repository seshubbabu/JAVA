package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class AuthorityTest {

	private Authority createTestSubject() {
		return new Authority();
	}

	@Test
	public void testSetGetCustomer() throws Exception {
		Authority testSubject = createTestSubject();
		AuthorityCustomer result = new AuthorityCustomer();
		testSubject.setCustomer(result);
		assertEquals(result, testSubject.getCustomer());
	}

	@Test
	public void testSetGetPenske() throws Exception {
		Authority testSubject = createTestSubject();
		Penske result = new Penske();
		testSubject.setPenske(result);
		assertEquals(result, testSubject.getPenske());
	}

	@Test
	public void testSetGetProvider() throws Exception {
		Authority testSubject = createTestSubject();
		Provider result = new Provider();
		testSubject.setProvider(result);
		assertEquals(result, testSubject.getProvider());
	}

	@Test
	public void testEquals() throws Exception {
		Authority testSubject = createTestSubject();
		Authority anotherObject = new Authority();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Authority testSubject = createTestSubject();
		Authority result = new Authority();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Authority testSubject = createTestSubject();
		String result = "Authority(penske=null, customer=null, provider=null)";
		assertEquals(result, testSubject.toString());
	}


}