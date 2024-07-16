package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class SimpleMessageResponseTest {
	private static String msg = "test msg";
	private SimpleMessageResponse createTestSubject(String msg) {
		return new SimpleMessageResponse(msg);
	}

	@Test
	public void testEquals() throws Exception {
		SimpleMessageResponse testSubject = createTestSubject(msg);
		SimpleMessageResponse anotherObject = new SimpleMessageResponse(msg);
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		SimpleMessageResponse testSubject = createTestSubject(msg);
		SimpleMessageResponse result = new SimpleMessageResponse(msg);
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		SimpleMessageResponse testSubject = createTestSubject(msg);
		String result = "SimpleMessageResponse(message=test msg)";
		assertEquals(result, testSubject.toString());
	}
}
