package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ExpectedVINsTest {
	private ExpectedVINs createTestSubject() {
		return new ExpectedVINs();
	}

	@Test
	public void testSetGetVinRecords() throws Exception {
		ExpectedVINs testSubject = createTestSubject();
		List<VinRecord> vinRecords = new ArrayList<VinRecord>();
		testSubject.setVinRecords(vinRecords);
		assertEquals(vinRecords, testSubject.getVinRecords());
	}
	
	@Test
	public void testEquals() throws Exception {
		ExpectedVINs testSubject = createTestSubject();
		ExpectedVINs anotherObject = new ExpectedVINs();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		ExpectedVINs testSubject = createTestSubject();
		ExpectedVINs result = new ExpectedVINs();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		ExpectedVINs testSubject = createTestSubject();
		String result = "ExpectedVINs(vinRecords=null)";
		assertEquals(result, testSubject.toString());
	}

}
