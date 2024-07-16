/*package com.penske.cf.api.cv.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import com.penske.cf.api.cv.domain.Authority;
import com.penske.cf.api.cv.domain.AuthorityCustomer;
import com.penske.cf.api.cv.domain.AuthorityCustomers;
import com.penske.cf.api.cv.domain.Customer;
import com.penske.cf.api.cv.domain.CustomersWrapper;
import com.penske.cf.api.cv.domain.Devices;
import com.penske.cf.api.cv.domain.Penske;
import com.penske.cf.api.cv.domain.Provider;
import com.penske.cf.api.cv.domain.TransformedResponse;
import com.penske.cf.api.cv.domain.Units;
import com.penske.cf.api.cv.model.PostUnits;
import com.penske.cf.api.cv.model.client.Credentials;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;
import com.penske.cf.api.cv.transformer.CustomerValidationTransformer;
import com.penske.cf.api.cv.util.Constants;
import com.penske.cf.api.cv.util.Constants.OrderBy;
import com.penske.cf.api.cv.util.Constants.SortBy;
import com.penske.cf.api.cv.util.DefaultEncryptorDecryptor;
import com.penske.cf.api.cv.util.RestAPIConnector;
import com.penske.cf.foundation.api.exception.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerValidationServiceTest {

	@InjectMocks
	private CustomerValidationService validationService = new CustomerValidationService();

	@Mock
	RestAPIConnector restAPIConnector;
	
	@Mock
	ExternalDataProviderInvokerService externalDataProvInokerService;
	
	@Mock
	ExternalDataProviderInvokerFactory externalDataProvInokerServiceFactory;

	@Mock
	CustomerValidationTransformer customerValidationTransformer;
	
	@Mock
	DefaultEncryptorDecryptor decryptor;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		customerValidationTransformer = mock(CustomerValidationTransformer.class);
		restAPIConnector = mock(RestAPIConnector.class);
		ReflectionTestUtils.setField(validationService, "customerValidationTransformer", customerValidationTransformer);
		ReflectionTestUtils.setField(validationService, "restAPIConnector", restAPIConnector);
		
	}

	@Test
   	public void testGetValidatedList() throws Exception {
		JsonObject json = getJsonData();
   		List<String> customerMasterIds = new ArrayList<String>();
   		customerMasterIds.add("2875046");
   		customerMasterIds.add("3110754");
   		List<Integer> customerMasterList = getCustomerIdList();
   		
		when(restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc)).thenReturn(json);  
		
		when(restAPIConnector.getCustomerDetailsbyProvider(ArgumentMatchers.any())
	        ).thenReturn(getCusomterProviderData());
		
		when(externalDataProvInokerServiceFactory.getExternalDataProviderService("gtb")).thenReturn(externalDataProvInokerService);
		
		when(decryptor.decrypt(Mockito.anyString())).thenAnswer(new Answer<String>() {
		    @Override
		    public String answer(InvocationOnMock invocation) throws Throwable {
		      Object[] args = invocation.getArguments();
		      return (String) args[0];
		    }
		  });
		
		Mockito.doNothing().when(externalDataProvInokerService).getAssetList(ArgumentMatchers.any());
		
		TransformedResponse mockResponse = mockData();
		when(customerValidationTransformer.transform(json)).thenReturn(mockResponse);
   	    TransformedResponse validatedResponseList=validationService.getValidatedList(customerMasterIds, "GTB", 0, 100, OrderBy.vin, SortBy.asc);
   	    assertNotNull(validatedResponseList);
   	}

	@Test(expected = ValidationException.class)
   	public void testGetValidatedListException() throws Exception {
		JsonObject json = getJsonData();;
   		List<String> customerMasterIds = new ArrayList<String>();
   		customerMasterIds.add("2875046");
   		customerMasterIds.add("3110754");
   		List<Integer> customerMasterList = getCustomerIdList();
   		
		when(restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc)).thenReturn(json);  
		
		when(restAPIConnector.getCustomerDetailsbyProvider(ArgumentMatchers.any())
		        ).thenReturn(getCusomterProviderData());
			
		when(externalDataProvInokerServiceFactory.getExternalDataProviderService("gtb")).thenReturn(externalDataProvInokerService);
		
		when(decryptor.decrypt(Mockito.anyString())).thenAnswer(new Answer<String>() {
		    @Override
		    public String answer(InvocationOnMock invocation) throws Throwable {
		      Object[] args = invocation.getArguments();
		      return (String) args[0];
		    }
		  });
		
		Mockito.doNothing().when(externalDataProvInokerService).getAssetList(ArgumentMatchers.any());
		
		
		when(customerValidationTransformer.transform(json)).thenReturn(null);
   	    validationService.getValidatedList(customerMasterIds, "GTB", 0, 100, OrderBy.vin, SortBy.asc);
   	}

	@Test
	public void testFindUnits() throws Exception {
		JsonObject json = getJsonData();
		List<Integer> customerMasterList = getCustomerIdList();
		when(restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc)).thenReturn(json);
		JsonObject jsonResponse = validationService.findUnits(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc);
		assertNotNull(jsonResponse);

	}

	@Test(expected = ValidationException.class)
	public void testFindUnitsException() throws Exception {
		List<Integer> customerMasterList = getCustomerIdList();
		when(restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc)).thenReturn(null);
		validationService.findUnits(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc);

	}
	
	@Test
	public void testGetUnitsByIDsSelection() throws Exception {

		List<Devices> devList = new ArrayList<>();
		Devices devices = new Devices();
		devices.setDeviceSerialNumber("1235788");
		devList.add(devices);
		JsonObject json = getJsonData();
		List<Integer> customerMasterList = getCustomerIdList();
		when(restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100, Constants.OrderBy.vin,
				Constants.SortBy.asc)).thenReturn(json);
		JsonObject jsonResponse = restAPIConnector.getUnitsByIDsSelection(customerMasterList, 0, 100,
				Constants.OrderBy.vin, Constants.SortBy.asc);
		assertNotNull(jsonResponse);
	}
	
	@Test
	public void testSaveUnits() {
		List<PostUnits> units = new ArrayList<PostUnits>();
		when(restAPIConnector.postUnits("123",units,"userId")).thenReturn(201);
		String result = validationService.saveUnits("123",units,"userId");
		assertEquals("Updated successfully", result);
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
	
	private TransformedResponse mockData() {
		TransformedResponse mockData = new TransformedResponse();
		CustomersWrapper data = new CustomersWrapper();
		List<Customer> customersList = new ArrayList<Customer>();
		List<Authority> authorityList = new ArrayList<Authority>();
		Customer customerData = new Customer();
		Authority authData = new Authority();

		// Setting customer data

		customerData.setCustomerMasterId(2875046);
		customerData.setUnits(setCustomerUnitData());
		customersList.add(customerData);

		// Setting authority data
		authData.setPenske(setPenskeData());
		authData.setCustomer(setAuthCustomerData());
		authData.setProvider(setProviderData());
		authorityList.add(authData);

		data.setCustomers(customersList);
		data.setAuthorities(authorityList);
		mockData.setData(data);
		return mockData;
	}

	private Provider setProviderData() {
		Provider providerObj = new Provider();
		AuthorityCustomers custmersObj = new AuthorityCustomers();
		List<AuthorityCustomers> authCustomersList = new ArrayList<>();
		authCustomersList.add(custmersObj);
		providerObj.setCustomers(authCustomersList);
		return providerObj;

	}

	private AuthorityCustomer setAuthCustomerData() {
		AuthorityCustomer authCustomerObj = new AuthorityCustomer();
		AuthorityCustomers custmersObj = new AuthorityCustomers();
		List<AuthorityCustomers> customersList = new ArrayList<>();
		custmersObj.setCustomerMasterId(2875046);
		custmersObj.setUnits(setAuthUnitData());
		customersList.add(custmersObj);
		authCustomerObj.setCustomers(customersList);
		return authCustomerObj;
	}

	private Penske setPenskeData() {
		Penske penskeObj = new Penske();
		AuthorityCustomers custmersObj = new AuthorityCustomers();
		List<AuthorityCustomers> authCustomersList = new ArrayList<>();
		custmersObj.setCustomerMasterId(2875046);
		custmersObj.setCustomerNumber("672828");
		custmersObj.setUnits(setAuthUnitData());
		authCustomersList.add(custmersObj);
		penskeObj.setCustomers(authCustomersList);
		return penskeObj;
	}

	private List<Units> setCustomerUnitData() {
		List<Units> unitList = new ArrayList<>();
		Units unitObj = new Units();
		unitObj.setUnitId(686846);
		unitObj.setVin("11VF813E2DA000281");
		unitObj.setDevices(setDevicesData());
		unitList.add(unitObj);
		return unitList;
	}

	private List<Units> setAuthUnitData() {
		List<Units> unitList = new ArrayList<>();
		Units unitObj = new Units();
		unitObj.setUnitId(686846);
		unitObj.setDevices(setDevicesData());
		unitList.add(unitObj);
		return unitList;
	}

	private List<Devices> setDevicesData() {
		List<Devices> deviceList = new ArrayList<>();
		Devices devicesObj = new Devices();
		devicesObj.setDeviceId(16423);
		devicesObj.setDeviceSerialNumber("17147785");
		deviceList.add(devicesObj);
		return deviceList;
	}

	public List<Integer> getCustomerIdList() {
		List<Integer> customerMasterIdList = new ArrayList<>();
		customerMasterIdList.add(2875046);
	    customerMasterIdList.add(3110754);
		return customerMasterIdList;
	}

	private List<DataProviderCustIntgtnCfgDetails> getCusomterProviderData() {
		
		List<DataProviderCustIntgtnCfgDetails> data = new ArrayList<DataProviderCustIntgtnCfgDetails>();
		
		DataProviderCustIntgtnCfgDetails details = new DataProviderCustIntgtnCfgDetails();
		Credentials credentials = new Credentials();
		data.add(details);
		
		details.setId(686845);
		details.setCustomerId(2875046);
		details.setProviderKey("gtb");
		details.setCredentials(credentials);
		
		credentials.setConnectionName("connection1");
		credentials.setAccessKey("login@penske.com");
		credentials.setAccessSecret(new String("dWNPR0h0NVhYb1VmYmRndnpDdm4zZHQvM011QnBpUTh0cUZ5a2xXckozalhlUVhqNlVPZWNnPT0AAAAAAAAAAA=="));
		
		return data;
	}
}*/