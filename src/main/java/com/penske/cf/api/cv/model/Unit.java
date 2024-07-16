package com.penske.cf.api.cv.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "units" })
@NoArgsConstructor
@Data
public class Unit {

	@JsonProperty("unitId")
	public int unitId;
	@JsonProperty("vin")
	public String vin;
	@JsonProperty("unitNumber")
	public UnitNumber unitNumber;
	@JsonProperty("customerNumber")
	public CustomerNumber customerNumber;
	@JsonProperty("exists")
	public Exists exists;
	@JsonProperty("expected")
	public String expected;
	@JsonProperty("consentRule")
	public ConsentRule consentRule;
	@JsonProperty("devices")
	public List<Device> devices;

}
