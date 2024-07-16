package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AssetWrapperTest {
	private AssetWrapper createTestSubject() {
		return new AssetWrapper();
	}

	@Test
	public void testSetGetUnits() throws Exception {
		AssetWrapper testSubject = createTestSubject();
		List<Unit> units = new ArrayList<Unit>();
		testSubject.setUnits(units);
		assertEquals(units, testSubject.getUnits());
	}

	@Test
	public void testEquals() throws Exception {
		AssetWrapper testSubject = createTestSubject();
		AssetWrapper anotherObject = new AssetWrapper();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		AssetWrapper testSubject = createTestSubject();
		AssetWrapper result = new AssetWrapper();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		AssetWrapper testSubject = createTestSubject();
		String result = "AssetWrapper(units=null)";
		assertEquals(result, testSubject.toString());
	}
}
