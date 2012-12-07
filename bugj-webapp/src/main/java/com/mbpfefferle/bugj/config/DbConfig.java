package com.mbpfefferle.bugj.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.orm.jpa.JpaTransactionManager;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DbConfig {

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager bean = new JpaTransactionManager();
        bean.setEntityManagerFactory(emf);
        return bean;
    }

}

