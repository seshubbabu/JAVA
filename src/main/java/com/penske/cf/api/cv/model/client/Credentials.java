package com.penske.cf.api.cv.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "integrationCredentialId", "connectionName", "accessKey", "accessSecret" })
@Data
public class Credentials implements Serializable {
	@JsonProperty("integrationCredentialId")
	private String integrationCredentialId;
	@JsonProperty("connectionName")
	private String connectionName;
	@JsonProperty("accessKey")
	private String accessKey;
	@JsonProperty("accessSecret")
	private String accessSecret;

}
