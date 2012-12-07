package com.mbpfefferle.bugj.web;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={com.mbpfefferle.bugj.web.ComponentScan.class})
public class MvcConfig {

    @Bean
    public ViewResolver jspResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setOrder(1);

        resolver.setMediaTypes(
                ImmutableMap.of(
                    "xml", "application/xml",
                    "json", "application/json",
                    "atom", "application/atom+xml",
                    "html", "text/html"));

        resolver.setViewResolvers(ImmutableList.of(jspResolver()));
        resolver.setDefaultViews(ImmutableList.of(jsonView()));

        return resolver;
    }

    public View jsonView() {
        return new MappingJacksonJsonView();
    }

    @Bean
    public JndiObjectFactoryBean entityManagerFactory() {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("persistence/com.mbpfefferle.bugj.jpa");

        //Properties prop = new Properties();
        //prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        //prop.setProperty("hibernate.show_sql", "true");
        //prop.setProperty("hibernate.format_sql", "true");
        //prop.setProperty("hibernate.use_sql_comments", "true");

        //bean.setJpaProperties(prop);
        return bean;
    }
}

