package com.penske.cf.api.cv.util;

public class Constants {

	// Headers
	public static final String HEADER_USERID = "X-PNSK-API-USER-ID";
	public static final String HEADER_USERNAME = "X-PNSK-API-USER-NAME";

	// Customers routes
	public static final String BASE_ROUTE = "/v1/assets/comparison";
	public static final String MOCK_ROUTE = "/v1/assets/mock/comparison";
	public static final String ALL_CUSTOMERS_ROUTE = "/customers";
	public static final String ALL_CUSTOMERS_BY_ID = "/enrollment/customer/projects/{projectId}/assets";
	public static final String ALL_ASSETS_COMPARISON = BASE_ROUTE + "/{projectId}/categorized";
	public static final String ALL_ASSETS_FINALYZED = BASE_ROUTE + "/{projectId}/finalized";
	// Default page and Page size properties
	public static final String CONFIG_DEFAULT_PAGE = "${cf-foundation.navigation-links.default-page}";
	public static final String CONFIG_DEFAULT_PAGE_SIZE = "${cf-foundation.navigation-links.default-page-size}";
	public static final String EXPORT_ROUTE = BASE_ROUTE+"/export";
	public static final String ENROLLMENT_VALIDATION = "/v1/enrollment/customers/projects/{projectId}/enrollmentValidation";

	// Error Constants:
	public static final String PLATFORM_EXCEPTION_CODE = "PlatformRuntimeException Occurred:";
	public static final String EXCEPTION_CODE = "Exception Occurred:";
	public static final String VALIDATION_CODE = "Validation Exception Occurred:";
	public static final String INVALID_MSG = "field is having invalid characters";
	public static final String BLANK_MSG = "field is blank or missing";
	public static final String ERROR_TRANSFORMATION = "error occured while fetching/transforming the data ";
	public static final String PAYLOAD = "payload";
	public static final String CUSTOMER_DATA = "customer data";
	public static final String CUSTOMER_DATA_ERROR = "customer data does not exist with the given customerId's";
	public static final String CONSTRAINT_VIOLATION = "Constraint Violation";
	public static final String INVALID_MASTERID_CHAR = "Customer MasterId contains invalid charecter :";
	public static final String MASTER_ID = "masterId";
	public static final String MASTER_IDs = "masterIds";
	public static final String MASTER_ID_EMPTY = "'masterIds' in the query parameter should have values.";
	public static final String INVALID_MASTER_ID = "'masterIds' is a required query parameter.";
	public static final String MAX_MASTERIDS = "exceeding the allowable number of MasterIDs in the request which is 10 and the provided MasterIDs are :";
	public static final String ERROR_FETCH = "error occured while fetching the Unit data ";
	public static final String ERROR_DATA_PROVIDER= "error occured while fetching the data providers ";
	public static final String CONFIG_ROUTE_UNIT = "${unit-api.by-vin-route}";
	public static final String TIME_FORMAT = "dd-MM-yyyy";
	public static final String MATCH_FLAG_MATCHED = "Matched";
	public static final String MATCH_FLAG_PENSKE_DB = "In Penske DB";
	public static final String MATCH_FLAG_NOT_PENSKE_DB = "Not In Penske DB";
	public static final String INVALID_PROVIDER_CODE = "invalid data provider code :";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String CUSTOMER_CONFIG="/customerConfigurations";

	public static enum VINMatch {
		inPenskeDB, notInPenskeDB, matched
	}

	public static enum Expect {
		yes, no, all
	}

	public static enum SortBy {
		asc, desc
	}

	public static enum OrderBy {
		vin, penskeUnitNum, deviceSerialNumber
	}

	public static enum YesOrNo {
		yes, no
	}

	public static enum Selection {
		vin, unitNumber, deviceSerialNumber
	}

	// Don't initiate this class.
	private Constants() {

	}
}
