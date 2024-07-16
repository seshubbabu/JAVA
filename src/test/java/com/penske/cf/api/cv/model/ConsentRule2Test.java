package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ConsentRule2Test {
	private ConsentRule2 createTestSubject() {
		return new ConsentRule2();
	}

	@Test
	public void testSetGetDescription() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		testSubject.setDescription("test desc");
		assertEquals("test desc", testSubject.getDescription());
	}
	
	@Test
	public void testSetGetEffectiveDate() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		testSubject.setEffectiveDate("10/10/2020");
		assertEquals("10/10/2020", testSubject.getEffectiveDate());
	}
	
	@Test
	public void testSetGetEndDate() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		testSubject.setEndDate("10/10/2020");
		assertEquals("10/10/2020", testSubject.getEndDate());
	}
	
	@Test
	public void testSetGetInjestionRule() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		testSubject.setInjestionRule("test rule");
		assertEquals("test rule", testSubject.getInjestionRule());
	}
	
	@Test
	public void testSetGetRuleName() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		testSubject.setRuleName("test rule name");
		assertEquals("test rule name", testSubject.getRuleName());
	}

	@Test
	public void testEquals() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		ConsentRule2 anotherObject = new ConsentRule2();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		ConsentRule2 result = new ConsentRule2();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		ConsentRule2 testSubject = createTestSubject();
		String result = "ConsentRule2(ruleName=null, injestionRule=null, effectiveDate=null, endDate=null, description=null)";
		assertEquals(result, testSubject.toString());
	}
}
