package com.penske.cf.api.cv.config;

import org.jasypt.encryption.ByteEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEByteEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Saravanakumar Alavandar, Capgemini NA
 */
@Configuration
@Slf4j
public class EncryptionBeanConfigurations {

	@Value("${cf-foundation.pbe.secretKey:''}")
	String secretKey;

    @Bean("binary")
    public ByteEncryptor byteEncryptor() {
        PooledPBEByteEncryptor encryptor = new PooledPBEByteEncryptor();
        SimplePBEConfig encryptConfig = new SimpleStringPBEConfig();
        encryptConfig.setAlgorithm("PBEWithMD5AndDES");
        encryptConfig.setKeyObtentionIterations("1000");
        encryptConfig.setPoolSize("1");
        encryptConfig.setProviderName("SunJCE");
        encryptConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptConfig.setPassword(secretKey);
        encryptor.setConfig(encryptConfig);
        return encryptor;
    }

    @Bean("string")
    public StringEncryptor StringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig encryptConfig = new SimpleStringPBEConfig();

        encryptConfig.setAlgorithm("PBEWithMD5AndDES");
        encryptConfig.setKeyObtentionIterations("1000");
        encryptConfig.setPoolSize("1");
        encryptConfig.setProviderName("SunJCE");
        encryptConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptConfig.setStringOutputType("base64");
        encryptConfig.setPassword(secretKey);
        encryptor.setConfig(encryptConfig);
        return encryptor;
    }
}
