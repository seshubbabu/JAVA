package com.penske.cf.api.cv.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@EnableConfigurationProperties
@Data
@Component
@ConfigurationProperties(prefix = "cf-customer.datasource")
public class DatabaseProperties {
	private String jdbcUrl;
	private String username;
	private String password;

}
