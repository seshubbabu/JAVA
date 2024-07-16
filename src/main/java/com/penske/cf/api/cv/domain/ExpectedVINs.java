package com.penske.cf.api.cv.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ExpectedVINs implements Serializable {

	private static final long serialVersionUID = -5409667088126006495L;
	@NotEmpty
	@NotNull
	@Valid
	private List<VinRecord> vinRecords;

}
