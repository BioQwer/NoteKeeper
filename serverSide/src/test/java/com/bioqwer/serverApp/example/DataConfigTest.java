package com.bioqwer.serverApp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

public interface DataConfigTest {
    @Bean
    public DataSource dataSource();

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory();

    @Bean
    public JpaTransactionManager transactionManager();

}
