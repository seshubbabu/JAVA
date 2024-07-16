package com.penske.cf.api.cv.model;

import lombok.Data;

@Data
public class ConsentRule {
	 private String ruleName;
	    private String injestionRule;
	    private String effectiveDate;
	    private String endDate;
	    private String description;
}
