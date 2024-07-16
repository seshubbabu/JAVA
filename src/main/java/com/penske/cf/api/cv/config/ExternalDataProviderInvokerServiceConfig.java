package com.penske.cf.api.cv.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gopenske.cf.foundation.bindings.http.HttpGatewayExecutorFactory;
import com.penske.cf.api.cv.service.ExternalDataProviderInvokerFactory;
import com.penske.cf.api.cv.service.ExternalDataProviderInvokerService;
import com.penske.cf.api.cv.service.GeotabDataProviderInvokerService;
import com.penske.cf.api.cv.service.PeoplenetDataProviderInvokerService;
import com.penske.cf.api.cv.service.RandMcnallyDataProviderInvokerService;
import com.penske.cf.api.cv.service.SamsaraDataProviderInvokerService;
import com.penske.cf.api.cv.service.XRSDataProviderInvokerService;

@Configuration
public class ExternalDataProviderInvokerServiceConfig {

	@Bean("externalDataProviderInvokerFactory")
	public FactoryBean serviceLocatorFactoryBean() {
	    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
	    factoryBean.setServiceLocatorInterface(ExternalDataProviderInvokerFactory.class);
	    return factoryBean;
	}
	  

	@Bean("gtb")
	public ExternalDataProviderInvokerService geotabExternalDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		GeotabDataProviderInvokerService service = new GeotabDataProviderInvokerService(executorFactory);
	    return service;
	}
	
	@Bean("ran")
	public ExternalDataProviderInvokerService randMcnallyExternalDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		RandMcnallyDataProviderInvokerService service = new RandMcnallyDataProviderInvokerService(executorFactory);
	    return service;
	}

	@Bean("pln")
	public ExternalDataProviderInvokerService peoplenetExternalDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		PeoplenetDataProviderInvokerService service = new PeoplenetDataProviderInvokerService(executorFactory);
	    return service;
	}
	
	@Bean("xrs")
	public ExternalDataProviderInvokerService xrsExternalDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		XRSDataProviderInvokerService service = new XRSDataProviderInvokerService(executorFactory);
	    return service;
	}
	
	@Bean("sam")
	public ExternalDataProviderInvokerService samsaraExternalDataProviderInvokerService(HttpGatewayExecutorFactory  executorFactory) {
		SamsaraDataProviderInvokerService service = new SamsaraDataProviderInvokerService(executorFactory);
	    return service;
	}
}
