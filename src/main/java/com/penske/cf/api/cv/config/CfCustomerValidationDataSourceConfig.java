package com.penske.cf.api.cv.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.penske.cf.api.customer.dao" }, entityManagerFactoryRef = "cfCustomerEntityManager", transactionManagerRef = "cfCustomertransactionManager")
@EnableTransactionManagement
@Profile(value = {"team3-acceptance", "acceptance","staging","prod"})
public class CfCustomerValidationDataSourceConfig {
	
	@Autowired
	private DatabaseProperties databaseProperties;

	@Bean(name = "cfCustomerDatasource")	
	public DataSource cfCustomerdataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(databaseProperties.getJdbcUrl());
		dataSourceBuilder.username(databaseProperties.getUsername());
		dataSourceBuilder.password(databaseProperties.getPassword());
		return dataSourceBuilder.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean cfCustomerEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(cfCustomerdataSource());
		em.setPackagesToScan("com.penske.cf.api.customer.model");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean

	public PlatformTransactionManager cfCustomertransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(cfCustomerEntityManager().getObject());
		return transactionManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}

}
