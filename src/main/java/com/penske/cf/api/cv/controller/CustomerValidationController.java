package com.penske.cf.api.cv.controller;

import static com.penske.cf.api.cv.util.CommonUtils.constructValidationException;
import static com.penske.cf.api.cv.util.Constants.CONFIG_DEFAULT_PAGE;
import static com.penske.cf.api.cv.util.Constants.CONFIG_DEFAULT_PAGE_SIZE;
import static com.penske.cf.api.cv.util.Constants.HEADER_USERID;
import static com.penske.cf.api.cv.util.Constants.HEADER_USERNAME;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.penske.cf.api.cv.domain.TransformedResponse;
import com.penske.cf.api.cv.service.CustomerValidationService;
import com.penske.cf.api.cv.util.Constants;
import com.penske.cf.api.cv.util.Constants.OrderBy;
import com.penske.cf.api.cv.util.Constants.SortBy;
import com.penske.cf.api.cv.util.RestAPIConnector;
import com.penske.cf.foundation.api.util.ErrorCodes;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * CustomerValidationController Class is a RestController where it Defines all
 * the CustomerValidation API End points.
 *
 */
@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*", exposedHeaders = "Location", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@Slf4j
public class CustomerValidationController {

	@Autowired
	private CustomerValidationService validationService;

	@Autowired
	RestAPIConnector restAPIConnector;

	@Setter
	private int maxNumOfMasterIds = 10;

	/**
	 * Retrieve a list of vehicle unit assets, comparing identifiers from the
	 * context of Penske, the Customer, and the Data Provider.
	 * 
	 * @param customerMasterIds (list of customerIds)
	 * @return TransformedResponse
	 * @throws ParseException
	 */

	@GetMapping(path = Constants.BASE_ROUTE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransformedResponse getExaminerData(@RequestParam("customerMasterIds") String customerMasterIds,
			@RequestParam("dataProviderCode") String dataProviderCode,
			@RequestParam(required = false, defaultValue = CONFIG_DEFAULT_PAGE) int page,
			@RequestParam(required = false, defaultValue = CONFIG_DEFAULT_PAGE_SIZE) int size,
			@RequestParam(required = false) OrderBy orderBy, @RequestParam(required = false) SortBy sortBy)
			throws ParseException {
		TransformedResponse transformedResponse = null;

		List<String> customerMasterIdlist = validateCustomerMasterIds(customerMasterIds);

		transformedResponse = validationService.getValidatedList(customerMasterIdlist, dataProviderCode, page, size,
				orderBy, sortBy);

		return transformedResponse;
	}

	/**
	 * validateCustomerMasterIdValues(List<String>) method is used to validate the
	 * customerMasterIds are numeric
	 * 
	 *
	 * @return
	 */
	private void validateCustomerMasterIdValues(List<String> customerMasterIds) {
		Pattern p = Pattern.compile("[^0-9]");
		for (String customerMasterId : customerMasterIds) {
			Matcher m = p.matcher(customerMasterId);
			if (m.find()) {
				constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.MASTER_ID,
						Constants.INVALID_MASTERID_CHAR + customerMasterId);
			}
		}

	}

	/**
	 * validateCustomerMasterIds(String) method is used to validate the
	 * customerMasterIds
	 * 
	 * @param customerMasterId
	 * @return List<String>
	 */
	private List<String> validateCustomerMasterIds(String customerMasterId) {
		if (StringUtils.isEmpty(customerMasterId)) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.MASTER_ID,
					Constants.MASTER_ID_EMPTY + String.valueOf(customerMasterId));
		}
		List<String> customerMasterIds = Stream.of(customerMasterId.split(",")).collect(Collectors.toList());
		validateCustomerMasterIdValues(customerMasterIds);

		if (customerMasterIds == null || customerMasterIds.size() == 0) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.MASTER_ID,
					Constants.INVALID_MASTER_ID + String.valueOf(customerMasterIds));
		}
		if (customerMasterIds.size() > maxNumOfMasterIds) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.MASTER_ID,
					Constants.MAX_MASTERIDS + customerMasterIds.size());
		}
		List<String> masterIdsWithOutDuplicates = customerMasterIds.stream().distinct().collect(Collectors.toList());
		return masterIdsWithOutDuplicates;

	}

	@PostMapping(path = Constants.ENROLLMENT_VALIDATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> validate(@PathVariable("projectId") String projectId) {

		return null;
	}
}
