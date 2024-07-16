package com.penske.cf.api.cv.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "customerMasterId", "units" })
@Data
@NoArgsConstructor
public class CustomerValidation {
	@JsonProperty("customerMasterId")
	private String customerMasterId;
	
	@JsonProperty("units")
	private Units units;
	
	@JsonProperty("vin")
	private String vin;

	private Devices device;


}
