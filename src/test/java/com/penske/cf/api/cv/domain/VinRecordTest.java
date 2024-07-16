package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.penske.cf.api.cv.util.Constants;

public class VinRecordTest {
	private VinRecord createTestSubject() {
		return new VinRecord();
	}

	@Test
	public void testSetGetExpected() throws Exception {
		VinRecord testSubject = createTestSubject();
		testSubject.setExpected(Constants.YesOrNo.yes);
		assertEquals(Constants.YesOrNo.yes, testSubject.getExpected());
	}
	
	@Test
	public void testSetGetUnitId() throws Exception {
		VinRecord testSubject = createTestSubject();
		testSubject.setUnitId(123123L);
		assertEquals(123123L, testSubject.getUnitId());
	}
	
	@Test
	public void testSetGetVIN() throws Exception {
		VinRecord testSubject = createTestSubject();
		testSubject.setVIN("ASD2234234");
		assertEquals("ASD2234234", testSubject.getVIN());
	}
	
	@Test
	public void testEquals() throws Exception {
		VinRecord testSubject = createTestSubject();
		VinRecord anotherObject = new VinRecord();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		VinRecord testSubject = createTestSubject();
		VinRecord result = new VinRecord();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		VinRecord testSubject = createTestSubject();
		String result = "VinRecord(VIN=null, unitId=null, expected=null)";
		assertEquals(result, testSubject.toString());
	}
}
