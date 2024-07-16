package com.penske.cf.api.cv.service;

import java.util.List;
import java.util.Map;

import com.penske.cf.api.cv.model.client.DataProviderCustIntgtnCfgDetails;

public interface ExternalDataProviderInvokerService {

	public List<Map<String,String>>  getAssetList(DataProviderCustIntgtnCfgDetails customerData);
}
