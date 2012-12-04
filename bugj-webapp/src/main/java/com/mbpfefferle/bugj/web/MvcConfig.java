package com.mbpfefferle.bugj.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={com.mbpfefferle.bugj.web.ComponentScan.class})
public class MvcConfig {

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

