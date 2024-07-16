package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class Provider {

	@JsonProperty("customers")
	private List<AuthorityCustomers> customers;
}
