package com.penske.cf.api.cv.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TransformedResponseTest {
	private TransformedResponse createTestSubject() {
		return new TransformedResponse();
	}

	@Test
	public void testSetGetData() throws Exception {
		TransformedResponse testSubject = createTestSubject();
		CustomersWrapper data = new CustomersWrapper();
		testSubject.setData(data);
		assertEquals(data, testSubject.getData());
	}
	
	@Test
	public void testSetGetMetadata() throws Exception {
		TransformedResponse testSubject = createTestSubject();
		MetaData metadata = new MetaData();
		testSubject.setMetadata(metadata);
		assertEquals(metadata, testSubject.getMetadata());
	}
	
	@Test
	public void testEquals() throws Exception {
		TransformedResponse testSubject = createTestSubject();
		TransformedResponse anotherObject = new TransformedResponse();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		TransformedResponse testSubject = createTestSubject();
		TransformedResponse result = new TransformedResponse();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		TransformedResponse testSubject = createTestSubject();
		String result = "TransformedResponse(data=null, metadata=null)";
		assertEquals(result, testSubject.toString());
	}
}
