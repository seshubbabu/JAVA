package com.penske.cf.api.cv.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.penske.cf.api.cv.util.Constants;

import lombok.Data;

@Data
public class VinRecord implements Serializable{

	private static final long serialVersionUID = 2852781371252532429L;
	private String VIN;
	@NotNull
	@Positive
	@Min(1)
	private Long unitId;
	@NotNull
	private Constants.YesOrNo expected;
}