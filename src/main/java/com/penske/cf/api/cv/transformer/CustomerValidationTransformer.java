package com.penske.cf.api.cv.transformer;

import static com.penske.cf.api.cv.util.CommonUtils.constructValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.penske.cf.api.cv.domain.Authority;
import com.penske.cf.api.cv.domain.AuthorityCustomer;
import com.penske.cf.api.cv.domain.AuthorityCustomers;
import com.penske.cf.api.cv.domain.Customer;
import com.penske.cf.api.cv.domain.CustomersWrapper;
import com.penske.cf.api.cv.domain.Devices;
import com.penske.cf.api.cv.domain.MetaData;
import com.penske.cf.api.cv.domain.Penske;
import com.penske.cf.api.cv.domain.Provider;
import com.penske.cf.api.cv.domain.TransformedResponse;
import com.penske.cf.api.cv.domain.Units;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;
import com.penske.cf.api.cv.util.Constants;
import com.penske.cf.api.cv.util.RestAPIConnector;
import com.penske.cf.foundation.api.util.ErrorCodes;

@Component
public class CustomerValidationTransformer {
	@Autowired
	RestAPIConnector restAPIConnector;

	public TransformedResponse transform(JsonObject jsonObject,Map<DataProviderCustIntgtnCfgDetails,List<Map<String,String>>> assetsPoviderData) {
		int counts;
		TransformedResponse transfromedResponse = new TransformedResponse();
		if (null != jsonObject && jsonObject.containsKey("data")) {
			CustomersWrapper data = new CustomersWrapper();
			data.setCustomers(populateCustomerData(jsonObject));
			data.setAuthorities(populateAuthorityData(jsonObject,assetsPoviderData));
			transfromedResponse.setData(data);	
			counts=transfromedResponse.getData().getCustomers().size();
			transfromedResponse.setMetadata(setMetaData(counts));
			return transfromedResponse;
		}
		return transfromedResponse;

	}
	
	private MetaData setMetaData(int counts)
	{
		MetaData metaData = new MetaData();
		metaData.setTotalRecords(counts);		
		return metaData;
	}
	

private List<Authority> populateAuthorityData(JsonObject jsonObject, Map<DataProviderCustIntgtnCfgDetails, List<Map<String, String>>> assetsPoviderData) {
		List<Authority> authorityList = new ArrayList<>();
		List<Authority> providerData = transformProviderdata(assetsPoviderData);
		JsonArray jAuths = jsonObject.getJsonArray("data");
		authorityList = jAuths.stream().map(jItem -> transformAuthoritydata((JsonObject) jItem))
				.filter(Objects::nonNull).collect(Collectors.toList());			
	List<AuthorityCustomers> penskelist=authorityList.stream().filter(penske->penske.getPenske().getCustomers()!=null).flatMap(penske-> penske.getPenske().getCustomers().stream())
		.collect(Collectors.toList());
    List<AuthorityCustomers> customerlist=	authorityList.stream().filter(customer->customer.getCustomer().getCustomers()!=null).flatMap(customer->customer.getCustomer().getCustomers().stream())
			.collect(Collectors.toList());
    authorityList.addAll(providerData);
	List<AuthorityCustomers> providerlist=	authorityList.stream().filter(provider->provider.getProvider().getCustomers()!=null).flatMap(provider-> provider.getProvider().getCustomers().stream())
			.collect(Collectors.toList());
	Authority authObj=new Authority();
	Penske penske=new Penske();
	penske.setCustomers(penskelist);
	AuthorityCustomer customer=new AuthorityCustomer();
	customer.setCustomers(customerlist);
	Provider provider=new Provider();
	provider.setCustomers(providerlist);
	authObj.setPenske(penske);
	authObj.setCustomer(customer);
	authObj.setProvider(provider);
	List<Authority> authlist=new ArrayList<>();
	authlist.add(authObj);
	return authlist;
	
}

	private List<Customer> populateCustomerData(JsonObject jsonObject) {
		JsonArray jUnits = jsonObject.getJsonArray("data");
		List<Customer> customerList;
		customerList = jUnits.stream().map(jItem -> transformCustomerdata((JsonObject) jItem)).filter(Objects::nonNull)
				.collect(Collectors.toList());
		Map<Integer, Customer> map = new HashMap<>();
		customerList.forEach(cust -> {
		if(map.get(cust.getCustomerMasterId()) != null){
		map.get(cust.getCustomerMasterId()).getUnits().addAll(cust.getUnits());
		}else{
		map.put(cust.getCustomerMasterId(), cust);
		}
		});
		return map.values().stream().collect(Collectors.toList());
	}

	private Customer transformCustomerdata(JsonObject jItem) {
		Customer customer = new Customer();
		List<Units> unitsList = new ArrayList<>();
		customer.setCustomerMasterId(jItem.getJsonNumber("customerMasterId").intValue());
		unitsList.add(setUnitData(jItem));
		customer.setUnits(unitsList);
		return customer;
	}

	private Units setUnitData(JsonObject jsonObject) {
		Units unitsObj = new Units();
		if (!StringUtils.isEmpty(jsonObject.get("vin"))) {
			unitsObj.setVin(jsonObject.getString("vin"));
		}

		unitsObj.setUnitId(jsonObject.getJsonNumber("id").longValue());
		unitsObj.setDevices(setAuthDevicesData(jsonObject));
		return unitsObj;
	}

	private List<Authority> transformProviderdata(Map<DataProviderCustIntgtnCfgDetails, List<Map<String, String>>> assetsPoviderData) {
		List<Authority> list = new ArrayList<>();
		Authority authority = new Authority();
		Provider provider = new Provider();
		provider.setCustomers(setAuthProviders(assetsPoviderData));
		authority.setProvider(provider);
		list.add(authority);
		return list;
	}
	
	private Authority transformAuthoritydata(JsonObject jItem) {
		Authority authority = new Authority();
		Penske authoritypenskeData = new Penske();
		AuthorityCustomer authCustomerData = new AuthorityCustomer();
		Provider provider = new Provider();
		AuthorityCustomers authorityCustomers = new AuthorityCustomers();
		
		authorityCustomers.setUnits(setAuthUnitData(jItem));
		if (setCustomerOrPenskeData(jItem)) {
			authoritypenskeData.setCustomers(setPenskeCustomerList(jItem));
			//authority.setPenske(authoritypenskeData);
		} else {
			authCustomerData.setCustomers(setAuthCustomers(jItem));
			//authority.setCustomer(authCustomerData);
		}
		authority.setPenske(authoritypenskeData);
		authority.setCustomer(authCustomerData);
		//provider.setCustomers(setAuthProviders(assetsPoviderData));//TODO THIS NEED TO BE CALLED BY MAKING AN EXTERNAL CALL TO THE PROVIDERS API
		authority.setProvider(provider);
		return authority;
	}

	// TODO THIS NEED TO BE HANDLED BY MAKING AN EXTERNAL CALL TO THE PROVIDERS API
	private List<AuthorityCustomers> setAuthProviders(Map<DataProviderCustIntgtnCfgDetails, List<Map<String, String>>> assetsPoviderData) {		
		List<AuthorityCustomers> authorityProviderCustomerList = new ArrayList<>();
		
		assetsPoviderData.entrySet().stream()
	     .forEach(e -> {
	    	 AuthorityCustomers authorityProviderCustomer = new AuthorityCustomers();
	    	 DataProviderCustIntgtnCfgDetails customerData=e.getKey(); 
	    	 List<Map<String, String>> providerData=e.getValue();
	    	 authorityProviderCustomer.setCustomerMasterId(customerData.getCustomerId());
	    	 authorityProviderCustomer.setCustomerNumber(String.valueOf(customerData.getId()));
	    	 List<Units> units = setUnitsData(providerData);
	    	
	    	 authorityProviderCustomer.setUnits(units);
	    	 authorityProviderCustomerList.add(authorityProviderCustomer);
	     });
		
		return authorityProviderCustomerList;
	}

	private List<Units> setUnitsData(List<Map<String, String>> da) {
        
        List<Units> units = da.stream().map(item -> {
               Units unit = new Units();
          unit.setUnitId(Long.valueOf(item.get("eventId")));
        unit.setUnitNumber(item.get("product")); 
        unit.setVin(item.get("vin")); 
         
        List<Devices> devices= setDevices(item); 
        unit.setDevices(devices);
        return unit;
               }).collect(Collectors.toList());
        Map<Long, List<Units>> mapUnits = units.stream().collect(Collectors.groupingBy(Units::getUnitId));
        List<Units> finalUnits = mapUnits.entrySet().stream().map(entry -> {
               List<Units> listofUnits = entry.getValue();
               Units firstUnit = listofUnits.remove(0);
               listofUnits.stream().forEach( otherunit -> {
                      if(!ObjectUtils.isEmpty(firstUnit.getDevices())) {
                            if(!ObjectUtils.isEmpty(otherunit.getDevices()))
                                   firstUnit.getDevices().addAll(otherunit.getDevices());
                     }
                     else {
                            if(!ObjectUtils.isEmpty(otherunit.getDevices()))
                                   firstUnit.setDevices(otherunit.getDevices());
                     }
                            
               });
               return firstUnit;
        }).collect(Collectors.toList());

        return finalUnits;
  }

	

	private List<Devices> setDevices(Map<String, String> item) {
		List<Devices> devices = new ArrayList<>();
		Devices device= new Devices();
		//device.setDeviceId(Integer.valueOf(item.get("result.id")));
		device.setDeviceSerialNumber(item.get("serial_number"));
		devices.add(device);
		return devices;
	}

	private List<AuthorityCustomers> setAuthCustomers(JsonObject jItem) {
		List<AuthorityCustomers> authorityCustomerCustomers = new ArrayList<>();
		AuthorityCustomers authorityCustomer = new AuthorityCustomers();
		authorityCustomer.setCustomerMasterId(jItem.getJsonNumber("customerMasterId").intValue());
		if (!setCustomerOrPenskeData(jItem)) {
			authorityCustomer.setCustomerNumber(setCustomerumber(jItem));
		}
		authorityCustomer.setUnits(setAuthUnitData(jItem));
		authorityCustomerCustomers.add(authorityCustomer);
		return authorityCustomerCustomers;
	}

	private List<AuthorityCustomers> setPenskeCustomerList(JsonObject jItem) {
		List<AuthorityCustomers> authorityPenskeCustomers = new ArrayList<>();
		AuthorityCustomers customersObj = new AuthorityCustomers();
		customersObj.setCustomerMasterId(jItem.getJsonNumber("customerMasterId").intValue());
		if (setCustomerOrPenskeData(jItem)) {
			customersObj.setCustomerNumber(setCustomerumber(jItem));
		}
		customersObj.setUnits(setAuthUnitData(jItem));
		authorityPenskeCustomers.add(customersObj);
		return authorityPenskeCustomers;
	}

	private List<Units> setAuthUnitData(JsonObject jItem) {
		Units providerUnitsObj = new Units();
		List<Units> custUnitsList = new ArrayList<>();
		providerUnitsObj.setUnitId(jItem.getJsonNumber("id").longValue());
		if (!StringUtils.isEmpty(jItem.get("vin"))) {
			providerUnitsObj.setVin(jItem.getString("vin"));
		}

		if (!StringUtils.isEmpty(jItem.get("penskeCustomerUnitNumber"))) {
			providerUnitsObj.setUnitNumber(jItem.getString("penskeCustomerUnitNumber"));
		}
		providerUnitsObj.setDevices(setAuthDevicesData(jItem));
		custUnitsList.add(providerUnitsObj);
		return custUnitsList;
	}

	private List<Devices> setAuthDevicesData(JsonObject jData) {
		Devices deviceObj = new Devices();
		List<Devices> deviceList = new ArrayList<>();

		if (!StringUtils.isEmpty(jData.get("devices"))) {
			JsonArray jDevices = (JsonArray) jData.get("devices");
			jDevices.forEach(jDevice -> {				
				JsonObject jObject = (JsonObject) jDevice;
				if(!jObject.isEmpty()) {
				String serialNumber = jObject.getString("deviceSerialNumber");
				deviceObj.setDeviceSerialNumber(serialNumber);
				int deviceId = jObject.getJsonNumber("id").intValue();
				deviceObj.setDeviceId(deviceId);
				deviceList.add(deviceObj);
				}
			});
		}		
		return deviceList;
	}

	private boolean setCustomerOrPenskeData(JsonObject jData) {
		boolean flag = false;
		if (!StringUtils.isEmpty(jData.get("penkseUnit"))) {
			JsonObject jObject = (JsonObject) jData.get("penkseUnit");
			// If the flag is Y means this is penske asset
			if (jObject.getString("flag").equalsIgnoreCase("Y")) {
				flag = true;

			}
		}
		return flag;

	}

	private String setCustomerumber(JsonObject jData) {
		if (!StringUtils.isEmpty(jData.get("penkseUnit"))) {
			JsonObject jObject = (JsonObject) jData.get("penkseUnit");			
			return jObject.getString("number",null);
		}
		return null;

	}
	
	}
