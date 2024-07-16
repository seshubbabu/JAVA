package com.penske.cf.api.cv.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.penske.cf.foundation.api.exception.FieldValidationError;
import com.penske.cf.foundation.api.exception.ValidationException;

public class CommonUtilsTest {


	@Test(expected = ValidationException.class)
	public void testConstructValidationExceptionWithFieldValidationError() throws Exception {
		FieldValidationError error = new FieldValidationError("VIN", "field is having invalid characters");
		CommonUtils
		.constructValidationException(error);
	}
	
	@Test(expected = ValidationException.class)
	public void testConstructValidationExceptionWithParams() throws Exception {
		CommonUtils
		.constructValidationException("400", "payload",
				"error occured while fetching/transforming the data ");
		
	}
	
	@Test
	public void testisNull() {
		assertEquals("test", CommonUtils.isNull(null,"test"));
	}
	
	@Test
	public void testisNullWithParams() {
		assertEquals("one", CommonUtils.isNull("one","test"));
	}
	
	@Test
	public void testIsNumeric() {
		assertEquals(false, CommonUtils.isNumeric(null));
		assertEquals(false, CommonUtils.isNumeric("a123"));
		assertEquals(true, CommonUtils.isNumeric("123"));
	}
	
	@Test(expected = ValidationException.class)
	public void testValidateHeaders() {
		CommonUtils.validateHeaders("");
	}
}
