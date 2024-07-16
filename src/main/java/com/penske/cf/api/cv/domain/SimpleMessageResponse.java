package com.penske.cf.api.cv.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleMessageResponse implements Serializable {
	private static final long serialVersionUID = 4539900135778744981L;
	private String message;

	public SimpleMessageResponse(String message) {
		this.message = message;
	}
}
