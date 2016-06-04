package com.bioqwer.serverApp.config;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * DataBase JavaConfigs for Persistence
 */
@Configuration
@ComponentScan({"com.bioqwer.serverApp.model", "com.bioqwer.serverApp.service"})
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("com.bioqwer.serverApp.repository")
public class DataConfig {

    private static final Logger logger = LogManager.getLogger(DataConfig.class);

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    private static final String PROPERTY_PACKAGES_TO_SCAN = "com.bioqwer.serverApp.model";

    private static final Pattern PATTERN_ENVIROMENT_PARAMETER = Pattern.compile(
            "\\$\\{(.*?)\\}");

    @Resource
    private Environment environment;


    private static String parseFromEnvironment(String parameterValue) {
        Matcher matcher = PATTERN_ENVIROMENT_PARAMETER.matcher(parameterValue);
        if (matcher.find()) {
            String updatedParameterFromEnvironment = parseFromEnvironment(
                    matcher.replaceFirst(matcher.group()));

            return updatedParameterFromEnvironment;
        } else {
            return parameterValue;
        }
    }

    /**
     * Provide load configs for DataBase.
     *
     * @return {@link javax.sql.DataSource} {@link org.springframework.context.annotation.Bean}
     * config.
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(parseFromEnvironment(environment.getRequiredProperty(
                PROPERTY_NAME_DATABASE_DRIVER)));

        dataSource.setJdbcUrl(parseFromEnvironment(environment.getRequiredProperty(
                PROPERTY_NAME_DATABASE_URL)));
        dataSource.setUsername(parseFromEnvironment(environment.getRequiredProperty(
                PROPERTY_NAME_DATABASE_USERNAME)));
        dataSource.setPassword(parseFromEnvironment(environment.getRequiredProperty(
                PROPERTY_NAME_DATABASE_PASSWORD)));

        logger.debug(dataSource.getConfig());
        return dataSource;
    }

    /**
     * {@link org.springframework.orm.jpa.JpaTransactionManager}
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    /**
     * {@link org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);

        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                          environment.getRequiredProperty(
                                  PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                          environment.getRequiredProperty(
                                  PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO,
                          environment.getRequiredProperty(
                                  PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                          environment.getRequiredProperty(
                                  PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                          environment.getRequiredProperty(
                                  PROPERTY_NAME_HIBERNATE_SHOW_SQL));

        logger.debug(jpaProperties.toString());
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

}
