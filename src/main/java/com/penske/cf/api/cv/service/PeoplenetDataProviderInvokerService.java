package com.penske.cf.api.cv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import com.gopenske.cf.foundation.bindings.domain.FeedDataSet;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutor;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutorFactory;
import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutorFactory.EndPointType;
import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PeoplenetDataProviderInvokerService implements ExternalDataProviderInvokerService {

	private HttpGatewayExecutorFactory executorFactory;
	
	public PeoplenetDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		this.executorFactory = executorFactory;
	}
	
	@Override
	public List<Map<String,String>> getAssetList(DataProviderCustIntgtnCfgDetails customerData) {
		Map<String,String> variables = prepareVariables(customerData);
		//Properties props = System.getProperties();
		//props.setProperty("https.proxyHost", "web-proxy.penske.com");
		//props.setProperty("https.proxyPort", "80");
		HttpGatewayExecutor httpGatewayExecutor = executorFactory.getHttpGatewayExecutor("pln", EndPointType.ENDPOINT);
		FeedDataSet data = httpGatewayExecutor.exchange(variables);
    	List<Map<String,String>> dataSet = data.getData();
    	//props.remove("https.proxyHost");
		//props.remove("https.proxyPort");
    	return dataSet;
	}
	
	private Map<String,String> prepareVariables(DataProviderCustIntgtnCfgDetails customerData) {
		
		Map<String,String> variables = new HashMap<String,String>();
        variables.put("accessSecret", new String(customerData.getCredentials().getAccessSecret()));
        variables.put("cid", String.valueOf(customerData.getCustomerId()));
        return variables;
	}
}
