package com.mbpfefferle.bugj.dao;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@ComponentScan(basePackageClasses={com.mbpfefferle.bugj.dao.Config.class})
public class Config {

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public DataSource testDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:memory:bugjtest;create=true");
        //dataSource.setUsername("sa");
        //dataSource.setPassword("");

        return dataSource;
    }

    //@Bean
    //public JndiObjectFactoryBean jndiEntityManager() throws Exception {
    //    SimpleNamingContextBuilder jndiBuilder
    //        = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
    //    jndiBuilder.bind("java:jboss/datasources/ExampleDS", testDataSource());

    //    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    //    bean.setJndiName("persistence/com.mbpfefferle.bugj.jpa");

    //    return bean;
    //}

    //@Bean
    //public EntityManagerFactory entityManagerFactory() throws Exception {
    //    EntityManagerFactory factory = (EntityManagerFactory)jndiEntityManager().getObject();
    //    System.out.println(factory);
    //    return factory;
    //}

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        SimpleNamingContextBuilder jndiBuilder
            = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        jndiBuilder.bind("java:jboss/datasources/ExampleDS", testDataSource());

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("com.mbpfefferle.bugj.jpa");
        bean.setLoadTimeWeaver(loadTimeWeaver());
        return bean;
    }

}

