package com.penske.cf.api.cv.model;

import java.util.List;

import lombok.Data;
@Data
public class AssetTransformedResponse {
	public List<AssetWrapper> data;
    public Metadata metadata;
}
