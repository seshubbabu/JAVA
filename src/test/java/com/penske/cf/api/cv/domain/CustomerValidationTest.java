package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CustomerValidationTest {
	private CustomerValidation createTestSubject() {
		return new CustomerValidation();
	}

	@Test
	public void testSetGetCustomerMasterId() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		testSubject.setCustomerMasterId("123");
		assertEquals("123", testSubject.getCustomerMasterId());
	}
	
	@Test
	public void testSetGetUnits() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		Units units = new Units();
		testSubject.setUnits(units);
		assertEquals(units, testSubject.getUnits());
	}
	
	
	@Test
	public void testSetGetVin() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		testSubject.setVin("ASD2234234");
		assertEquals("ASD2234234", testSubject.getVin());
	}
	
	@Test
	public void testSetGetDevice() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		Devices device = new Devices();
		testSubject.setDevice(device);
		assertEquals(device, testSubject.getDevice());
	}
	
	@Test
	public void testEquals() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		CustomerValidation anotherObject = new CustomerValidation();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		CustomerValidation result = new CustomerValidation();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		CustomerValidation testSubject = createTestSubject();
		String result = "CustomerValidation(customerMasterId=null, units=null, vin=null, device=null)";
		assertEquals(result, testSubject.toString());
	}
}
