package com.penske.cf.api.cv.util;
import org.springframework.util.StringUtils;

import com.penske.cf.foundation.api.exception.FieldValidationError;
import com.penske.cf.foundation.api.exception.ValidationException;
import com.penske.cf.foundation.api.util.ErrorCodes;

public interface CommonUtils {
	org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommonUtils.class);
	
	static void constructValidationException(FieldValidationError error) {
		throw new ValidationException(ErrorCodes.BAD_REQUEST.getCode(), null, Constants.CONSTRAINT_VIOLATION, error);
	}
	static void constructValidationException(String code, String field, String message) {
		FieldValidationError error = new FieldValidationError(field, message);
		throw new ValidationException(code, message, error);
	}
	public static String isNull(String s, String defaultValue) {
        return (null == s) ? defaultValue : s;
    }

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Long.parseLong(strNum);
		} catch (NumberFormatException nfe) {
			log.debug(nfe.getMessage());
			return false;
		}
		return true;
	}

	public static void validateHeaders(String userId) {
		if (StringUtils.isEmpty(userId)) {
			String msg = "Please provide valid UserId token as part of header";
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), "userID", msg);
		}
	}
}
