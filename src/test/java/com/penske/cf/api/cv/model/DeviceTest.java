package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeviceTest {
	private Device createTestSubject() {
		return new Device();
	}

	@Test
	public void testSetGetDeviceId() throws Exception {
		Device testSubject = createTestSubject();
		testSubject.setDeviceId("123123");
		assertEquals("123123", testSubject.getDeviceId());
	}
	
	@Test
	public void testSetGetDeviceSerialNumber() throws Exception {
		Device testSubject = createTestSubject();
		DeviceSerialNumber serial = new DeviceSerialNumber();
		testSubject.setDeviceSerialNumber(serial);
		assertEquals(serial, testSubject.getDeviceSerialNumber());
	}
	
	@Test
	public void testSetGetConsentRule2() throws Exception {
		Device testSubject = createTestSubject();
		ConsentRule2 consentRule = new ConsentRule2();
		testSubject.setConsentRule(consentRule);
		assertEquals(consentRule, testSubject.getConsentRule());
	}
	
	@Test
	public void testEquals() throws Exception {
		Device testSubject = createTestSubject();
		Device anotherObject = new Device();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Device testSubject = createTestSubject();
		Device result = new Device();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Device testSubject = createTestSubject();
		String result = "Device(deviceId=null, deviceSerialNumber=null, consentRule=null)";
		assertEquals(result, testSubject.toString());
	}

}
