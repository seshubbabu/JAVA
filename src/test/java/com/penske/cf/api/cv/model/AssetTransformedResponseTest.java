package com.penske.cf.api.cv.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AssetTransformedResponseTest {
	private AssetTransformedResponse createTestSubject() {
		return new AssetTransformedResponse();
	}

	@Test
	public void testSetGetData() throws Exception {
		AssetTransformedResponse testSubject = createTestSubject();
		List<AssetWrapper> data = new ArrayList<AssetWrapper>();
		testSubject.setData(data);
		assertEquals(data, testSubject.getData());
	}
	
	@Test
	public void testSetGetMetadata() throws Exception {
		AssetTransformedResponse testSubject = createTestSubject();
		Metadata metadata = new Metadata();
		testSubject.setMetadata(metadata);
		assertEquals(metadata, testSubject.getMetadata());
	}
	
	@Test
	public void testEquals() throws Exception {
		AssetTransformedResponse testSubject = createTestSubject();
		AssetTransformedResponse anotherObject = new AssetTransformedResponse();
		assertTrue(testSubject.equals(anotherObject));
	}

	@Test
	public void testHashCode() throws Exception {
		AssetTransformedResponse testSubject = createTestSubject();
		AssetTransformedResponse result = new AssetTransformedResponse();
		assertEquals(result.hashCode(),testSubject.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		AssetTransformedResponse testSubject = createTestSubject();
		String result = "AssetTransformedResponse(data=null, metadata=null)";
		assertEquals(result, testSubject.toString());
	}
}
