package com.penske.cf.api.cv.service;

import static com.penske.cf.api.cv.util.CommonUtils.constructValidationException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.json.JsonObject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.penske.cf.api.cv.domain.TransformedResponse;
import com.penske.cf.api.cv.model.client.Credentials;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;
import com.penske.cf.api.cv.transformer.CustomerValidationTransformer;
import com.penske.cf.api.cv.util.Constants;
import com.penske.cf.api.cv.util.DefaultEncryptorDecryptor;
import com.penske.cf.api.cv.util.RestAPIConnector;
import com.penske.cf.foundation.api.util.ErrorCodes;

import lombok.extern.slf4j.Slf4j;

/**
 * CustomerValidationService is Service Class where it Defines all the
 * CustomerValidation service methods.
 */
@Service
@Slf4j
public class CustomerValidationService {

	@Autowired
	private CustomerValidationTransformer transformer;


	@Autowired
	RestAPIConnector restAPIConnector;

	@Autowired
	CustomerValidationTransformer customerValidationTransformer;
	
	@Autowired
	private DefaultEncryptorDecryptor decryptor;
	
	@Autowired
	private ExternalDataProviderInvokerFactory externalDataProviderInvokerFactory;
	

	/**
	 * will return the required transformed response 
	 *  @param customerMasterIds,page,size, orderBy
	 * @return TransformedResponse
	 */	
	
	public TransformedResponse getValidatedList(List<String> customerMasterIds,String dataProviderCode, int page, int size,
			Constants.OrderBy orderBy, Constants.SortBy sortBy) throws ParseException {
		
	    List<DataProviderCustIntgtnCfgDetails> customerData = getCustomerDetailsbyProvider(dataProviderCode);
		//filter customer data by requested customer Id's
	    customerData = customerData.stream().filter(cd -> customerMasterIds.contains(String.valueOf(cd.getCustomerId()))).collect(Collectors.toList());
	    if(CollectionUtils.isEmpty(customerData)) {
	    	constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.CUSTOMER_DATA,
					Constants.CUSTOMER_DATA_ERROR);
	    }
	    //remove duplicates if there are any
		Map<Integer,DataProviderCustIntgtnCfgDetails>  customerMapData = customerData.stream().filter(cd -> customerMasterIds.contains(String.valueOf(cd.getCustomerId()))).collect(
		    						Collectors.toMap(DataProviderCustIntgtnCfgDetails::getCustomerId, Function.identity(),
		    							      (existing, replacement) -> existing));
		 //get final list of customers
		customerData = customerMapData.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
		customerData = decryptCustomerPassword(customerData);
		//Added for testing
		//List<DataProviderCustIntgtnCfgDetails> customerData=getCustomers();
		Map<DataProviderCustIntgtnCfgDetails,List<Map<String,String>>> assetsPoviderData=getDataProviderCustomerAssets(customerData,dataProviderCode);
		log.info("data from data provider API is" +assetsPoviderData);
		TransformedResponse transformedResponse = null;
		List<Integer> masterIdsAsIntegerList = customerMasterIds.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		JsonObject json = findUnits(masterIdsAsIntegerList, page, size, orderBy, sortBy);
			transformedResponse=customerValidationTransformer.transform(json,assetsPoviderData);
		if(transformedResponse==null) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.PAYLOAD,
					Constants.ERROR_TRANSFORMATION);
		}
		return transformedResponse;
	}
	
	/**
	 * Call to the external (Unit API) to get the unit details for the list of customerMasterIds
	 * @param customerMasterIds,page,size, orderBy
	 * @return JsonObject
	 */	

	private JsonObject findUnits(List<Integer> customerMasterIds, int page, int size, Constants.OrderBy orderBy,
			Constants.SortBy sortBy) {
				JsonObject json = restAPIConnector.getUnitsByIDsSelection(customerMasterIds, page, size, orderBy, sortBy);
			if (json == null) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.PAYLOAD, Constants.ERROR_FETCH);
		} 
		return json;
	}
	
	private List<DataProviderCustIntgtnCfgDetails> getCustomerDetailsbyProvider(String dataProviderCode) {
		List<DataProviderCustIntgtnCfgDetails> customerData = restAPIConnector.getCustomerDetailsbyProvider(dataProviderCode);
		if (customerData == null) {
			constructValidationException(ErrorCodes.BAD_REQUEST.getCode(), Constants.PAYLOAD,
					Constants.ERROR_DATA_PROVIDER);
		}
		return customerData;
	}
	
	private List<DataProviderCustIntgtnCfgDetails> decryptCustomerPassword(List<DataProviderCustIntgtnCfgDetails> customerData) {
		
		return customerData.stream().map(cd -> {
			String secrect = new String(cd.getCredentials().getAccessSecret());
	        //log.info("Encrypted values {} ", secrect);
	        String decoded = new String(Base64Utils.decodeFromUrlSafeString(secrect));
	        String stringDecrypt = decryptor.decrypt(decoded);
	        cd.getCredentials().setAccessSecret(stringDecrypt);
	        return cd;
		}).collect(Collectors.toList());
		
	}
	
	private Map<DataProviderCustIntgtnCfgDetails,List<Map<String,String>>>  getDataProviderCustomerAssets(List<DataProviderCustIntgtnCfgDetails> customerData, String dataProviderCode) {		
		ExternalDataProviderInvokerService dataProviderInvokerService =  externalDataProviderInvokerFactory.getExternalDataProviderService(dataProviderCode.toLowerCase());
		
		final Map<DataProviderCustIntgtnCfgDetails,List<Map<String,String>>> customerAssets = new HashMap<DataProviderCustIntgtnCfgDetails,List<Map<String,String>>>();
		customerData.forEach( e -> {
			List<Map<String,String>> assets = dataProviderInvokerService.getAssetList(e);
			customerAssets.put(e, assets);
		});
		
		return customerAssets;
		
	}
	private List<DataProviderCustIntgtnCfgDetails> getCustomers() {
		List<DataProviderCustIntgtnCfgDetails> lst = new ArrayList<>();
		DataProviderCustIntgtnCfgDetails us1= new DataProviderCustIntgtnCfgDetails();
		
		Credentials credentials= new Credentials();
		
		credentials.setAccessKey("Penske_Integ");
		credentials.setAccessSecret("PrABFS1gjsEP");
		credentials.setConnectionName("connection 1");
		us1.setCustomerId(616187);
		us1.setCredentials(credentials);
		lst.add(us1);
		
		return lst;
	}
}
