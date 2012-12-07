package com.mbpfefferle.bugj.dao;

import com.mbpfefferle.bugj.config.DbConfig;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses={
    com.mbpfefferle.bugj.dao.Config.class})
@Import({DbConfig.class})
@EnableTransactionManagement
public class Config {

    @Bean
    public DataSource testDataSource() {

        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
            dataSource.setUrl("jdbc:derby:memory:bugjtest;create=true");

            SimpleNamingContextBuilder jndiBuilder
                = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            jndiBuilder.bind("java:jboss/datasources/ExampleDS", dataSource);

            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

