/*package com.penske.cf.api.cv.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import com.penske.cf.api.cv.domain.FilterWrapper;
import com.penske.cf.api.cv.exception.UnavailableAPIException;
import com.penske.cf.api.cv.model.PostUnits;
import com.penske.cf.api.cv.util.Constants.OrderBy;
import com.penske.cf.api.cv.util.Constants.SortBy;
import com.penske.cf.foundation.api.exception.ValidationException;
import com.penske.cf.foundation.api.http.rest.client.HttpResponse;
import com.penske.cf.foundation.api.http.rest.client.HttpRetryClientInvoker;
import com.penske.cf.foundation.api.security.UserPrinciple;

public class RestAPIConnectorTest {

	@Mock
	private HttpRetryClientInvoker httpClientInvoker;

	@InjectMocks
	RestAPIConnector restConnector = new RestAPIConnector();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.httpClientInvoker = mock(HttpRetryClientInvoker.class, CALLS_REAL_METHODS);
		ReflectionTestUtils.setField(restConnector, "httpClientInvoker", httpClientInvoker);
		UserPrinciple user = new UserPrinciple(1L, "name", "username", "email@mail.com", "password", null);
		Authentication auth = new TestingAuthenticationToken(user, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	@Test
	public void testGetUnitsByIDsSelection() throws Exception {
		List<Integer> masterIds = new ArrayList<Integer>();
		JsonObject json = getJsonData();
		HttpResponse response = new HttpResponse();
		response.setResponse(json.toString());
		response.setStatus(HttpStatus.OK.value());

		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(response);

		JsonObject result = restConnector.getUnitsByIDsSelection(masterIds, 1, 1, OrderBy.vin, SortBy.asc);
		assertEquals(json, result);
	}

	@Test
	public void testGetUnitsByIDs() throws Exception {
		Long[] unitIds = new Long[1];
		List<Integer> masterIds = new ArrayList<Integer>();
		JsonObject json = getJsonData();
		HttpResponse response = new HttpResponse();
		response.setResponse(json.toString());
		response.setStatus(HttpStatus.OK.value());

		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(response);

		JsonObject result = restConnector.getUnitsByIDs(unitIds);
		assertEquals(json, result);
	}

	@Test
	public void testGetDataProviders() throws Exception {
		List<Integer> masterIds = new ArrayList<Integer>();
		JsonObject json = getJsonData();
		HttpResponse response = new HttpResponse();
		response.setResponse(json.toString());
		response.setStatus(HttpStatus.OK.value());

		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(response);

		JsonObject result = restConnector.getCustomerDetails("GTB");
		assertEquals(json, result);
	}

	@Test(expected = UnavailableAPIException.class)
	public void testGetUnitsByIDsSelectionException() throws Exception {
		List<Integer> masterIds = new ArrayList<Integer>();
		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(null);

		restConnector.getUnitsByIDsSelection(masterIds, 1, 1, OrderBy.vin, SortBy.asc);
	}

	@Test(expected = UnavailableAPIException.class)
	public void testGetUnitsByIDsException() throws Exception {
		Long[] unitIds = new Long[1];
		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(null);

		restConnector.getUnitsByIDs(unitIds);
	}

	@Test(expected = UnavailableAPIException.class)
	public void testGetDataProvidersException() throws Exception {
		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(null);
		//restConnector.getCustomerDetails("GTB");
	}

	@Test
	public void testPostUnits() throws Exception {
		List<PostUnits> units = new ArrayList<PostUnits>();
		JsonObject json = getJsonData();
		HttpResponse response = new HttpResponse();
		response.setResponse(json.toString());
		response.setStatus(HttpStatus.CREATED.value());

		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(response);

		Integer result = restConnector.postUnits("123", units, "userId");
		assertEquals(201, result);
	}

	@Test(expected = ValidationException.class)
	public void testPostUnitsException() throws Exception {
		List<PostUnits> units = new ArrayList<PostUnits>();
		JsonObject json = getJsonData();
		HttpResponse response = new HttpResponse();
		response.setResponse(json.toString());
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		when(httpClientInvoker.execute(Mockito.any(com.penske.cf.foundation.api.http.rest.client.HttpRequest.class)))
				.thenReturn(response);

		restConnector.postUnits("123", units, "userId");
	}

	private JsonObject getJsonData() {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

		jsonBuilder.add("id", 686845);
		jsonBuilder.add("customerMasterId", 4408477);
		jsonBuilder.add("vin", "11VF813E1AA000056");
		JsonObjectBuilder jsonBuilderNew = Json.createObjectBuilder();
		jsonBuilderNew.add("number", "EPES60021");
		jsonBuilderNew.add("flag", "Y");
		jsonBuilder.add("penkseUnit", jsonBuilderNew);
		jsonBuilder.add("devices", Json.createArrayBuilder()
				.add(Json.createObjectBuilder().add("id", 10247).add("deviceSerialNumber", "17105493")).build());
		JsonObjectBuilder jsonBuilderFirst = Json.createObjectBuilder();
		jsonBuilderFirst.add("data", Json.createArrayBuilder().add(jsonBuilder));
		JsonObject jsonObject = jsonBuilderFirst.build();
		return jsonObject;
	}
}
*/