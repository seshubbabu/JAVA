package com.penske.cf.api.cv.service;

public interface ExternalDataProviderInvokerFactory {

	ExternalDataProviderInvokerService getExternalDataProviderService(String providerCode);
}
