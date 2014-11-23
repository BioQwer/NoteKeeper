package com.bioqwer.serverApp.example;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"com.bioqwer.serverApp.model", "com.bioqwer.serverApp.service"})
@EnableJpaRepositories("com.bioqwer.serverApp.repository")
@PropertySource("classpath:application-test.properties")
@Profile("test")
@EnableTransactionManagement
public class TestDataConfigTest implements DataConfigTest {

    @Autowired
    private Environment env;

    @Override
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().build();
    }

    @Override
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPersistenceProviderClass(HibernatePersistence.class);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        jpaProperties.put("hibernate.show_sql", false); // avoid double logging
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.hbm2ddl.auto", "create"); // auto initialization schema in database, based on JPA Entity classes

        bean.setPersistenceProviderClass(HibernatePersistence.class);
        bean.setJpaProperties(jpaProperties);
        //bean.setPackagesToScan("com.bioqwer.serverApp.model");

        return bean;
    }

    @Override
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
