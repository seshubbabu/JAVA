package com.penske.cf.api.cv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.ObjectUtils;

import com.gopenske.cf.foundation.bindings.domain.FeedDataSet;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutor;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutorFactory;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutorFactory.EndPointType;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GeotabDataProviderInvokerService implements ExternalDataProviderInvokerService {

	private HttpGatewayExecutorFactory executorFactory;
	
	public GeotabDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		this.executorFactory = executorFactory;
	}
	
	@Override
	public List<Map<String,String>> getAssetList(DataProviderCustIntgtnCfgDetails customerData) {
		Map<String,String> variables = prepareVariables(customerData);
		Properties props = System.getProperties();
		props.setProperty("https.proxyHost", "web-proxy.penske.com");
		props.setProperty("https.proxyPort", "80");
		HttpGatewayExecutor httpGatewayExecutor = executorFactory.getHttpGatewayExecutor("gtb", EndPointType.ENDPOINTCHAIN);
		FeedDataSet data = httpGatewayExecutor.exchange(variables);
    	List<Map<String,String>> dataSet = data.getData();
    	props.remove("https.proxyHost");
		props.remove("https.proxyPort");
    	return dataSet;
	}
	
	private Map<String,String> prepareVariables(DataProviderCustIntgtnCfgDetails customerData) {
		
		Map<String,String> variables = new HashMap<String,String>();
		
		Map<String,Object> parameterConfig = customerData.getParameterConfiguration();
		if(!ObjectUtils.isEmpty(parameterConfig)) {
			Map<String,String> parameters = (Map<String, String>) parameterConfig.get("metadataExtensions");
			if(!ObjectUtils.isEmpty(parameters))
				variables.put("dbName", parameters.get("dbName"));
		}
		variables.put("accessKey", customerData.getCredentials().getAccessKey());
        variables.put("accessSecret", new String(customerData.getCredentials().getAccessSecret()));
        variables.put("provider", "geotab");
        variables.put("typeName", "Device");
		return variables;
	}
}
