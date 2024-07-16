package com.penske.cf.api.cv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@JsonPropertyOrder({ "penske", "customer","provider"})
@Data
public class Authority {

@JsonProperty("penske")
private Penske penske;
@JsonProperty("customer")
private AuthorityCustomer customer;
@JsonProperty("provider")
private Provider provider;

}
