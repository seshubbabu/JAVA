package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MetadataTest {
	private Metadata createTestSubject() {
		return new Metadata();
	}

	@Test
	public void testSetGetTotalRecords() throws Exception {
		Metadata testSubject = createTestSubject();
		testSubject.setTotalRecords(123);
		assertEquals(123, testSubject.getTotalRecords());
	}
	
	@Test
	public void testEquals() throws Exception {
		Metadata testSubject = createTestSubject();
		Metadata anotherObject = new Metadata();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		Metadata testSubject = createTestSubject();
		Metadata result = new Metadata();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		Metadata testSubject = createTestSubject();
		String result = "Metadata(totalRecords=20)";
		assertEquals(result, testSubject.toString());
	}
}
