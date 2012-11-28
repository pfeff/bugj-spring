package com.mbpfefferle.bugj.web;

import cucumber.runtime.arquillian.junit.Cucumber;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone; 

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.openqa.selenium.WebDriver;

public class RunCukesIT extends Cucumber {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Drone
    WebDriver browser;

    @Deployment(testable=false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(HelloResource.class)
            .addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
            //.setWebXML("WEB-INF/web.xml");
            ;
    }

    @Override
    protected void initializeRuntimeOptions() {
        runtimeOptions.featurePaths.add(
                "classpath:com/mbpfefferle/bugj/web");
        runtimeOptions.glue.add("classpath:com/mbpfefferle/bugj/web");
    }
}

