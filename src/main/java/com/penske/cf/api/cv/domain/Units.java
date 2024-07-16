package com.penske.cf.api.cv.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Units {
	 private long unitId;
	 private String unitNumber;
	 private String vin;
	 private List<Devices> devices;
}
