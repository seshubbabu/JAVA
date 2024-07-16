package com.penske.cf.api.cv.domain;

import java.util.List;

import lombok.Data;
@Data
public class Customers {
	
	private int customerMasterId;
	private String customerNumber;
	private List<Units> units;

}
