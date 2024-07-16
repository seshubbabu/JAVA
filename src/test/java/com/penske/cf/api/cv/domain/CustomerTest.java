package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CustomerTest {
	private Customer createTestSubject() {
		return new Customer();
	}

	@Test
	public void testSetGetCustomerMasterId() throws Exception {
		Customer testSubject = createTestSubject();
		testSubject.setCustomerMasterId(1);
		assertEquals(1, testSubject.getCustomerMasterId());
	}
	
	@Test
	public void testSetGetDevices() throws Exception {
		Customer testSubject = createTestSubject();
		Devices device = new Devices();
		testSubject.setDevices(device);
		assertEquals(device, testSubject.getDevices());
	}

	@Test
	public void testSetGetUnits() throws Exception {
		Customer testSubject = createTestSubject();
		List<Units> units = new ArrayList<>();
		testSubject.setUnits(units);
		assertEquals(units, testSubject.getUnits());
	}
	
	@Test
	public void testEquals() throws Exception {
		Customer testSubject = createTestSubject();
		Customer anotherObject = new Customer();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Customer testSubject = createTestSubject();
		Customer result = new Customer();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Customer testSubject = createTestSubject();
		String result = "Customer(customerMasterId=0, units=null, devices=null)";
		assertEquals(result, testSubject.toString());
	}

}
