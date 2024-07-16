package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorityCustomers {
	private int customerMasterId;
	private String customerNumber;
	private List<Units> units;

}
