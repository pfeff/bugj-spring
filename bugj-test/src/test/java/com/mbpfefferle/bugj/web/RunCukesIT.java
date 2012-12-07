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
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import org.openqa.selenium.WebDriver;

public class RunCukesIT extends Cucumber {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Drone
    WebDriver browser;

    @Deployment(testable=false)
    public static WebArchive createDeployment() {
        MavenDependencyResolver resolver = DependencyResolvers
            .use(MavenDependencyResolver.class)
            .loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap.create(WebArchive.class);

        for (WebArchive w : resolver
                .artifact("com.mbpfefferle.bugj:bugj-webapp:war")
                .resolveAs(WebArchive.class)) {
            war.merge(w);
        }

        return war;
    }

    @Override
    protected void initializeRuntimeOptions() {
        runtimeOptions.featurePaths.add(
                "classpath:com/mbpfefferle/bugj/web");
        runtimeOptions.glue.add("classpath:com/mbpfefferle/bugj/web");
    }
}

