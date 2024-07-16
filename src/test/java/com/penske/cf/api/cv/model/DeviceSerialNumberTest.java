package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeviceSerialNumberTest {
	private DeviceSerialNumber createTestSubject() {
		return new DeviceSerialNumber();
	}

	@Test
	public void testSetGetDataProvider() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		testSubject.setDataProvider("123123");
		assertEquals("123123", testSubject.getDataProvider());
	}
	
	@Test
	public void testSetGetPenske() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		testSubject.setPenske("123123");
		assertEquals("123123", testSubject.getPenske());
	}
	
	@Test
	public void testSetGetCustomer() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		testSubject.setCustomer("123123");
		assertEquals("123123", testSubject.getCustomer());
	}
	
	@Test
	public void testEquals() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		DeviceSerialNumber anotherObject = new DeviceSerialNumber();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		DeviceSerialNumber result = new DeviceSerialNumber();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		DeviceSerialNumber testSubject = createTestSubject();
		String result = "DeviceSerialNumber(penske=null, dataProvider=null, customer=null)";
		assertEquals(result, testSubject.toString());
	}
}
