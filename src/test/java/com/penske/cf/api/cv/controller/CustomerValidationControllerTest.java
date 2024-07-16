package com.penske.cf.api.cv.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
import com.penske.cf.api.cv.service.CustomerValidationService;
import com.penske.cf.api.cv.util.Constants;
import com.penske.cf.api.cv.util.RestAPIConnector;
import com.penske.cf.foundation.api.exception.ValidationException;

@ExtendWith(SpringExtension.class)
public class CustomerValidationControllerTest {
	
	@InjectMocks
	CustomerValidationController validationController = new CustomerValidationController();
	
	@Mock
	CustomerValidationService validationService;
	
	@Mock
	RestAPIConnector restAPIConnector;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		validationService = mock(CustomerValidationService.class);
		restAPIConnector = mock(RestAPIConnector.class);
		ReflectionTestUtils.setField(validationController, "validationService", validationService);
		ReflectionTestUtils.setField(validationController, "restAPIConnector", restAPIConnector);
		
	}
	 
	@Test
	@Ignore // TODO: PLEASE FIX THIS TEST
	public void testGetValidatedAsset() throws ParseException {
		TransformedResponse mockResponse = mockData();
		 String customerMasterIds="2875046,3110754";		
		 List<String> customerMasterList = getCustomerIdList();
		 JsonObject json = getJsonData();	
		 when(validationService.getValidatedList(customerMasterList,"GTB",0,100,
		  Constants.OrderBy.vin, Constants.SortBy.asc)).thenReturn(mockResponse);
		 TransformedResponse transformedResponse=validationController.getExaminerData(customerMasterIds,"GTB",0,100,
					Constants.OrderBy.vin, Constants.SortBy.asc);
	     assertNotNull(transformedResponse);
	     assertEquals(mockResponse,transformedResponse);
	}	
	
	@Test(expected = ValidationException.class)
	@Ignore // TODO: PLEASE FIX THIS TEST
	public void testGetValidatedAssetException() throws ParseException {
		 String customerMasterIds="2875046,3110754";		
		 List<String> customerMasterList = getCustomerIdList();
		 JsonObject json = getJsonData();	
		 when(validationService.getValidatedList(customerMasterList,"GTB",0,100,
		  Constants.OrderBy.vin, Constants.SortBy.asc)).thenReturn(null);
		 
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
		jsonBuilder.add("dataProviderCode", "GTB");
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
		 Authority authData=new Authority();
		 
		 //Setting customer data
		 
		 customerData.setCustomerMasterId(2875046);
		 customerData.setUnits(setCustomerUnitData());
		 customersList.add(customerData);
		 
		 //Setting authority data
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
		Provider providerObj=new Provider();
		AuthorityCustomers custmersObj=new AuthorityCustomers();		
		List<AuthorityCustomers> authCustomersList=new ArrayList<>();
		authCustomersList.add(custmersObj);
		providerObj.setCustomers(authCustomersList);
		return providerObj;
		
	}

	private AuthorityCustomer setAuthCustomerData() {
		AuthorityCustomer authCustomerObj=new AuthorityCustomer();		
		AuthorityCustomers custmersObj=new AuthorityCustomers();	
		List<AuthorityCustomers> customersList=new ArrayList<>();
		custmersObj.setCustomerMasterId(2875046);
		custmersObj.setUnits(setAuthUnitData());
		customersList.add(custmersObj);	
		authCustomerObj.setCustomers(customersList);
		return authCustomerObj;
	}
	
	private Penske setPenskeData() {		
		Penske penskeObj=new Penske();		
		AuthorityCustomers custmersObj=new AuthorityCustomers();		
		List<AuthorityCustomers> authCustomersList=new ArrayList<>();	
		custmersObj.setCustomerMasterId(3110754);
		custmersObj.setCustomerNumber("672828");
		custmersObj.setUnits(setAuthUnitData());
		authCustomersList.add(custmersObj);
		penskeObj.setCustomers(authCustomersList);		
		return penskeObj;
	}

	private List<Units> setCustomerUnitData() {
		List<Units> unitList=new ArrayList<>();
		Units unitObj=new Units();
		unitObj.setUnitId(686846);
		unitObj.setVin("11VF813E2DA000281");
		unitObj.setDevices(setDevicesData());
		unitList.add(unitObj);
		return unitList;
	}
	
	private List<Units> setAuthUnitData() {
		List<Units> unitList=new ArrayList<>();
		Units unitObj=new Units();
		unitObj.setUnitId(686846);		
		unitObj.setDevices(setDevicesData());
		unitList.add(unitObj);
		return unitList;
	}
	private List<Devices> setDevicesData() {
		List<Devices> deviceList=new ArrayList<>();
		Devices devicesObj=new Devices();
		devicesObj.setDeviceId(16423);
		devicesObj.setDeviceSerialNumber("17147785");
		deviceList.add(devicesObj);
		return deviceList;
	}

	public List<String> getCustomerIdList()
	{
		List<String> customerMasterIdList=new ArrayList<>();
		customerMasterIdList.add("2875046");
		customerMasterIdList.add("3110754");
		return customerMasterIdList;
	}

}
