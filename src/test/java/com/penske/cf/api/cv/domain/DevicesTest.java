package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DevicesTest {
	private Devices createTestSubject() {
		return new Devices();
	}

	@Test
	public void testSetGetDeviceId() throws Exception {
		Devices testSubject = createTestSubject();
		testSubject.setDeviceId(123123);
		assertEquals(123123, testSubject.getDeviceId());
	}
	
	@Test
	public void testSetGetDeviceSerialNumber() throws Exception {
		Devices testSubject = createTestSubject();
		testSubject.setDeviceSerialNumber("12345678");
		assertEquals("12345678", testSubject.getDeviceSerialNumber());
	}
	
	@Test
	public void testEquals() throws Exception {
		Devices testSubject = createTestSubject();
		Devices anotherObject = new Devices();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Devices testSubject = createTestSubject();
		Devices result = new Devices();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Devices testSubject = createTestSubject();
		String result = "Devices(deviceId=0, deviceSerialNumber=null)";
		assertEquals(result, testSubject.toString());
	}

}
