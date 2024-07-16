package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FilterWrapperTest {
	private FilterWrapper createTestSubject() {
		return new FilterWrapper();
	}

	@Test
	public void testSetGetCustomerMasterId() throws Exception {
		FilterWrapper testSubject = createTestSubject();
		List<Long> masterIds = new ArrayList<Long>();
		testSubject.setCustomerMasterIds(masterIds);
		assertEquals(masterIds, testSubject.getCustomerMasterIds());
	}
	
	@Test
	public void testEquals() throws Exception {
		FilterWrapper testSubject = createTestSubject();
		FilterWrapper anotherObject = new FilterWrapper();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		FilterWrapper testSubject = createTestSubject();
		FilterWrapper result = new FilterWrapper();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		FilterWrapper testSubject = createTestSubject();
		String result = "FilterWrapper(customerMasterIds=null)";
		assertEquals(result, testSubject.toString());
	}
}
