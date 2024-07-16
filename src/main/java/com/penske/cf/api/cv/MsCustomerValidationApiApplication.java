package com.penske.cf.api.cv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.penske.cf.api.cv", "com.penske.cf.foundation.api","com.gopenske.cf.foundation" })
@EnableConfigurationProperties
@EnableAutoConfiguration
public class MsCustomerValidationApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsCustomerValidationApiApplication.class, args);
    }
}