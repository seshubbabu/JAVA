package com.penske.cf.api.cv.domain;

import lombok.Data;

@Data
public class TransformedResponse {
	
	private CustomersWrapper data;
	private MetaData metadata;
}
