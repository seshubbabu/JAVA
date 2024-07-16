package com.penske.cf.api.cv.model;

import lombok.Data;

@Data
public class Device {
	 private String deviceId;
	    public DeviceSerialNumber deviceSerialNumber;
	    public ConsentRule2 consentRule;
	
}
