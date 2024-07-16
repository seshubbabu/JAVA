package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UnitsTest {
	
	private Units createTestSubject() {
		return new Units();
	}

	@Test
	public void testSetGetUnitId() throws Exception {
		Units testSubject = createTestSubject();
		testSubject.setUnitId(123123L);
		assertEquals(123123L, testSubject.getUnitId());
	}
	
	@Test
	public void testSetGetUnitNumber() throws Exception {
		Units testSubject = createTestSubject();
		testSubject.setUnitNumber("123123");
		assertEquals("123123", testSubject.getUnitNumber());
	}
	
	@Test
	public void testSetGetVin() throws Exception {
		Units testSubject = createTestSubject();
		testSubject.setVin("ASD2234234");
		assertEquals("ASD2234234", testSubject.getVin());
	}
	
	@Test
	public void testSetGetDevices() throws Exception {
		Units testSubject = createTestSubject();
		List<Devices> devices = new ArrayList<Devices>();
		testSubject.setDevices(devices);
		assertEquals(devices, testSubject.getDevices());
	}
	
	@Test
	public void testEquals() throws Exception {
		Units testSubject = createTestSubject();
		Units anotherObject = new Units();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Units testSubject = createTestSubject();
		Units result = new Units();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Units testSubject = createTestSubject();
		String result = "Units(unitId=0, unitNumber=null, vin=null, devices=null)";
		assertEquals(result, testSubject.toString());
	}

}
