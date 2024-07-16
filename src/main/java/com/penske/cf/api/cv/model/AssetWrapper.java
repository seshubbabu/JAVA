package com.penske.cf.api.cv.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "units" })
@Data
@NoArgsConstructor
public class AssetWrapper {
	public List<Unit> units;
	
}
