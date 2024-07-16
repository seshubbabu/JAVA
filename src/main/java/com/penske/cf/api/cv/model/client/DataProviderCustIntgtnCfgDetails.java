package com.penske.cf.api.cv.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.penske.cf.foundation.api.hateoas.NavigableResource;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "providerKey", "customerId", "customerProcessingPriority", "configurationStartDate",
		"configurationEndDate", "activeCustomerAgreementFlag", "integrationState", "parameterConfiguration",
		"credentials" })
@Data
public class DataProviderCustIntgtnCfgDetails extends NavigableResource implements Serializable {

	@JsonProperty("id")
	private int id;

	@JsonProperty("providerKey")
	private String providerKey;

	@JsonProperty("customerId")
	private int customerId;

	@JsonProperty("customerProcessingPriority")
	private int customerProcessingPriority;

//	@JsonFormat(pattern = Constants.DATE_FORMAT)
	@JsonProperty("configurationStartDate")
	private String configurationStartDate;

//	@JsonFormat(pattern = Constants.DATE_FORMAT)
	@JsonProperty("configurationEndDate")
	private String configurationEndDate;

	@JsonProperty("activeCustomerAgreementFlag")
	private String activeCustomerAgreementFlag;

	@JsonProperty("integrationState")
	private int integrationState;

	@JsonProperty("parameterConfiguration")
	private Map parameterConfiguration;

	@JsonProperty("credentials")
	private Credentials credentials;
}
