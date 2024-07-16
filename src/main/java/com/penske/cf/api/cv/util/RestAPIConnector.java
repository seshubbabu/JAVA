package com.penske.cf.api.cv.util;

import static com.penske.cf.api.cv.util.Constants.CONFIG_ROUTE_UNIT;

import java.io.StringReader;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.penske.cf.api.cv.domain.FilterWrapper;
import com.penske.cf.api.cv.exception.UnavailableAPIException;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;
import com.penske.cf.foundation.api.exception.FieldValidationError;
import com.penske.cf.foundation.api.exception.ValidationException;
import com.penske.cf.foundation.api.http.rest.client.HttpResponse;
import com.penske.cf.foundation.api.http.rest.client.HttpRetryClientInvoker;
import com.penske.cf.foundation.api.security.UserPrinciple;
import com.penske.cf.foundation.api.util.ErrorCodes;

import lombok.extern.slf4j.Slf4j;

/**
 * RestAPIConnector class is used to build and invoke the rest api(s)
 */
@Slf4j
@Component
public class RestAPIConnector {

	@Value(CONFIG_ROUTE_UNIT)
	private String routeUnit;
	
	@Value("${unit-api.get-dataproviders}")
	String ROUTE_DATA_PROVIDER;
	
	@Value("${provider-api.get-provider-customers}")
	private String ROUTE_PROVIDER_CUSTOMERS;
	

	@Autowired
	private HttpRetryClientInvoker httpClientInvoker;

	/*@Autowired
	private CommonUtils commonUtils;*/

	/**
	 * * getUnitsByIDsSelection(List<Integer>, int, int, Constants.OrderBy, Constants.SortBy)
	 * method is used to get the unit details by provided selection option.
	 * 
	 * @param customerMasterIds
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param sortBy
	 * @return JsonObject
	 */
	public JsonObject getUnitsByIDsSelection(List<Integer> customerMasterIds, int page, int size,
			Constants.OrderBy orderBy, Constants.SortBy sortBy) {
		FilterWrapper filterWrapper = new FilterWrapper();
		StringBuilder builder = new StringBuilder(routeUnit + "/search");
		filterWrapper
				.setCustomerMasterIds(customerMasterIds.stream().map(Integer::longValue).collect(Collectors.toList()));
		builder.append("?page=").append(page).append("&size=").append(size);
		String route = builder.toString();		
		return invokeGetAdvanced(route, filterWrapper);
	}

	/**
	 * raiseAPIValidationException(HttpStatusCodeException, String, String) method
	 * is to set the api validation exception.
	 * 
	 * @param e
	 * @param errorMsg1
	 * @param errorMsg2
	 * @return
	 */
	private JsonObject raiseAPIValidationException(HttpStatusCodeException e, String errorMsg1, String errorMsg2) {
		log.debug("Response status code: {}.", e.getStatusCode());
		if (e.getStatusCode().is5xxServerError()) {
			FieldValidationError error = new FieldValidationError("Unit API", errorMsg1);
			throw new ValidationException(ErrorCodes.NOT_FOUND.getCode(), e.getMessage(), errorMsg2, error);
		} else {
			return null;
		}
	}

	/**
	 * getUnitsByIDs(Long[]) method is used to invoke the unit api by unit id.
	 * 
	 * @param unitIDs
	 * @return JsonObject
	 */
	public JsonObject getUnitsByIDs(Long[] unitIDs) {
		String route = routeUnit + "/search";
		FilterWrapper filterWrapper = new FilterWrapper();
		return invokeGetAdvanced(route, filterWrapper);
	}

	/**
	 * invokeGetAdvanced(String, FilterWrapper) method is used to build and invoke
	 * the unit rest api.
	 * 
	 * @param route
	 * @param filterWrapper
	 * @return JsonObject
	 */
	private JsonObject invokeGetAdvanced(String route, FilterWrapper filterWrapper) {
		String errorMsg1 = "Error accessing the Uni API end-point. (" + route + ")";
		String errorMsg2 = "Unavailable Unit API (" + route + ")";
		try {
			log.info("Invoking REST API GET:{}. body: {}", route, filterWrapper);
			HttpResponse response = execute(route, HttpMethod.POST, createHeaders(""), filterWrapper, String.class);		
			if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
				// no data found
				return null;
			}
			if (response.getStatus() == HttpStatus.OK.value()) {
				JsonReader jsonReader = Json.createReader(new StringReader((String) response.getResponse()));				
				return jsonReader.readObject();
			} else {
				log.debug("Response code: {}, message: {}", response.getStatus(), response.getResponse());
				log.error(errorMsg1);
				throw new UnavailableAPIException(errorMsg1);
			}
		} catch (HttpStatusCodeException e) {
			return raiseAPIValidationException(e, errorMsg1, errorMsg2);
		} catch (Exception e) {
			log.error(errorMsg1);
			throw new UnavailableAPIException(errorMsg1);
		}
	}

	private HttpResponse execute(String route, HttpMethod method, Map<String, String> headers, Object payload,
			Class responseType) {
		com.penske.cf.foundation.api.http.rest.client.HttpRequest httpRequest = new com.penske.cf.foundation.api.http.rest.client.HttpRequest(
				route, MediaType.APPLICATION_JSON, headers, responseType, method, payload);
		try {
			return httpClientInvoker.execute(httpRequest);

		} catch (Exception e) {
			log.error("Error while hitting Unit API:{}", e);
			throw new ValidationException(ErrorCodes.BAD_REQUEST.getCode(), e.getMessage(),
					new FieldValidationError("", "Given Url Not valid"));
		}

	}

	public JsonObject getDataProviders() {
		return invokeRestAPI(ROUTE_DATA_PROVIDER, "DATA PROVIDER");
	}

	public List<DataProviderCustIntgtnCfgDetails> getCustomerDetailsbyProvider(String providerCode) {
		try {
			URI uri = getUrlPath(ROUTE_PROVIDER_CUSTOMERS,Collections.singletonMap("providerCode", providerCode));
			TypeReference<List<DataProviderCustIntgtnCfgDetails>> response = new TypeReference<List<DataProviderCustIntgtnCfgDetails>>() {};
			List<DataProviderCustIntgtnCfgDetails> customerData = httpClientInvoker.execute(HttpMethod.GET,uri.toString() , response);
			return customerData;

		} catch (Exception e) {
			log.error("getCustomerDetailsbyProvider >>>>" + e.getMessage());
			throw new UnavailableAPIException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	private JsonObject invokeRestAPI(String route, String domain) {
		String errorMsg1 = "Error accessing the " + domain + " API end-point. (" + route + ")";
		String errorMsg2 = "Unavailable " + domain + " API (" + route + ")";
		try {
			log.debug("Invoking REST API, URL: {}", route);
			
			HttpResponse response = execute(route, HttpMethod.GET, createHeaders(""), null, String.class);				
			
			if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
				// no data found
				return null;
			}
			if (response.getStatus() == HttpStatus.OK.value()) {
				JsonReader jsonReader = Json.createReader(new StringReader((String) response.getResponse()));				
				return jsonReader.readObject();
			} else {
				log.debug("Response code: {}, message: {}", response.getStatus(), response.getResponse());
				log.error(errorMsg1);
				throw new UnavailableAPIException(errorMsg1);
			}
		} catch (HttpStatusCodeException e) {
			return raiseAPIValidationException(e, errorMsg1, errorMsg2);
		} catch (Exception e) {
			log.error(errorMsg1);
			throw new UnavailableAPIException(errorMsg1);
		}
	}

		/**
	 * setBearerTokenToHeader() method is used to set the bearer token.
	 * 
	 * @return HttpHeaders
	 */
	private Map<String, String> createHeaders(String userId) {
		Map<String, String> headerMap = new HashMap<>();
		UserPrinciple p = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		headerMap.put("Authorization", "Bearer " + p.getPassword());
		headerMap.put("X-PNSK-API-USER-ID", userId);
		return headerMap;
	}
	
	private URI getUrlPath(String uri,Map<String, Object> variables) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri);
		if (!ObjectUtils.isEmpty(variables))
			return uriBuilder.buildAndExpand(variables).toUri();

		return uriBuilder.build().toUri();
	}
}