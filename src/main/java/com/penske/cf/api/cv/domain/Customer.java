package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

	@JsonProperty("customerMasterId")
	private int customerMasterId;

	@JsonProperty("units")
	private List<Units> units;

	@JsonProperty("devices")
	private Devices devices;
	

}
