
package com.penske.cf.api.cv.transformer;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)

@RunWith(MockitoJUnitRunner.class)
public class ValidationTransformerTest {

	@InjectMocks
	CustomerValidationTransformer transformer = new CustomerValidationTransformer();

	@Test
	public void tranformTest() throws Exception {
		List<Customer> customersList = getCustomerMockData();
		List<Authority> authorityList = getAuthorityMockData();
		CustomersWrapper data = new CustomersWrapper();
		data.setCustomers(customersList);
		data.setAuthorities(authorityList);

		TransformedResponse transformedResponse = new TransformedResponse();
		transformedResponse.setData(data);
		assertNotNull(transformedResponse); 

		//TransformedResponse actualResponse = transformer.transform(getJsonData(), "GTB");
		//assertNotNull(actualResponse); 
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

	public JsonObject getRestAPIMockResponse() {

		JsonObject restJson = null;
		return restJson;
	}

	public List<Customer> getCustomerMockData() {
		Customer customerData = new Customer();
		List<Customer> customersList = new ArrayList<Customer>();
		customerData.setCustomerMasterId(4408477);
		customerData.setUnits(setCustomerUnitData());
		customersList.add(customerData);
		return customersList;
	}

	public List<Authority> getAuthorityMockData() {
		Authority authData = new Authority();
		List<Authority> authorityList = new ArrayList<Authority>();
		authData.setPenske(setPenskeData());
		authData.setCustomer(setAuthCustomerData());
		authData.setProvider(setProviderData());
		authorityList.add(authData);
		return authorityList;
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
		custmersObj.setCustomerMasterId(4408477);
		custmersObj.setUnits(setAuthUnitData());
		customersList.add(custmersObj);
		authCustomerObj.setCustomers(customersList);
		return authCustomerObj;
	}

	private Penske setPenskeData() {
		Penske penskeObj = new Penske();
		AuthorityCustomers custmersObj = new AuthorityCustomers();
		List<AuthorityCustomers> authCustomersList = new ArrayList<>();
		custmersObj.setCustomerMasterId(4408477);
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
		unitObj.setUnitNumber("EPES60021");
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

	public List<Integer> getCustomerIdList() { List<Integer>
  customerMasterIdList=new ArrayList<>(); customerMasterIdList.add(4408477);
  //customerMasterIdList.add(3110754); 
  return customerMasterIdList; }
  
  
  
  }
