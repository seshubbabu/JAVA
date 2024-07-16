package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UnitTest {
	private Unit createTestSubject() {
		return new Unit();
	}

	@Test
	public void testSetGetUnitId() throws Exception {
		Unit testSubject = createTestSubject();
		testSubject.setUnitId(123123);
		assertEquals(123123, testSubject.getUnitId());
	}
	
	@Test
	public void testSetGetVin() throws Exception {
		Unit testSubject = createTestSubject();
		testSubject.setVin("ASD2234234");
		assertEquals("ASD2234234", testSubject.getVin());
	}
	
	@Test
	public void testSetGetUnitNumber() throws Exception {
		Unit testSubject = createTestSubject();
		UnitNumber unit = new UnitNumber();
		testSubject.setUnitNumber(unit);
		assertEquals(unit, testSubject.getUnitNumber());
	}
	
	@Test
	public void testSetGetCustomerNumber() throws Exception {
		Unit testSubject = createTestSubject();
		CustomerNumber customer = new CustomerNumber();
		testSubject.setCustomerNumber(customer);
		assertEquals(customer, testSubject.getCustomerNumber());
	}
	
	@Test
	public void testSetGetExists() throws Exception {
		Unit testSubject = createTestSubject();
		Exists exists = new Exists();
		testSubject.setExists(exists);
		assertEquals(exists, testSubject.getExists());
	}
	
	@Test
	public void testSetGetExpected() throws Exception {
		Unit testSubject = createTestSubject();
		testSubject.setExpected("test");
		assertEquals("test", testSubject.getExpected());
	}
	
	@Test
	public void testSetGetConsentRule() throws Exception {
		Unit testSubject = createTestSubject();
		ConsentRule consentRule = new ConsentRule();
		testSubject.setConsentRule(consentRule);
		assertEquals(consentRule, testSubject.getConsentRule());
	}
	
	@Test
	public void testSetGetDevices() throws Exception {
		Unit testSubject = createTestSubject();
		List<Device> devices = new ArrayList<Device>();
		testSubject.setDevices(devices);
		assertEquals(devices, testSubject.getDevices());
	}
	
	@Test
	public void testEquals() throws Exception {
		Unit testSubject = createTestSubject();
		Unit anotherObject = new Unit();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Unit testSubject = createTestSubject();
		Unit result = new Unit();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Unit testSubject = createTestSubject();
		String result = "Unit(unitId=0, vin=null, unitNumber=null, customerNumber=null, exists=null, expected=null, consentRule=null, devices=null)";
		assertEquals(result, testSubject.toString());
	}
}
