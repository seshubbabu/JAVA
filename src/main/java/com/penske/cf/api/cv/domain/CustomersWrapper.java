package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "customers" })
@Data
@NoArgsConstructor
public class CustomersWrapper {
	
	private List<Customer> customers;
	 private List<Authority> authorities;
	
}
