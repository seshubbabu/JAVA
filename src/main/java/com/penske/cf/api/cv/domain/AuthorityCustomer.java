package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorityCustomer {
	@JsonProperty("customer")
	private List<AuthorityCustomers> customers;
	
}
