package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MetaDataTest {
	private MetaData createTestSubject() {
		return new MetaData();
	}

	@Test
	public void testSetGetTotalRecords() throws Exception {
		MetaData testSubject = createTestSubject();
		testSubject.setTotalRecords(123);
		assertEquals(123, testSubject.getTotalRecords());
	}
	
	@Test
	public void testEquals() throws Exception {
		MetaData testSubject = createTestSubject();
		MetaData anotherObject = new MetaData();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		MetaData testSubject = createTestSubject();
		MetaData result = new MetaData();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		MetaData testSubject = createTestSubject();
		String result = "MetaData(totalRecords=0)";
		assertEquals(result, testSubject.toString());
	}
}
