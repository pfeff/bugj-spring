package com.mbpfefferle.bugj.web;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.web.BugsResource;
import com.mbpfefferle.bugj.web.ComponentScan;
import com.mbpfefferle.bugj.web.Initializer;
import com.mbpfefferle.bugj.web.MvcConfig;
import com.mbpfefferle.bugj.web.pages.bug.NewBugPage;

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

        WebArchive war = ShrinkWrap.create(WebArchive.class)
            .addClasses(ComponentScan.class, Initializer.class, MvcConfig.class)
            .addClasses(BugsResource.class, Bug.class)
            .addAsLibraries(resolver
                    .artifact("org.springframework:spring-webmvc")
                    .artifact("cglib:cglib")
                    .resolveAsFiles())
            .addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
            .addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/templates/bug/new.jsp"), "WEB-INF/templates/bug/new.jsp")
            .addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/templates/bug/show.jsp"), "WEB-INF/templates/bug/show.jsp")
            .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
            ;
        //System.out.println(war.toString(true));
        return war;
    }

    @Override
    protected void initializeRuntimeOptions() {
        runtimeOptions.featurePaths.add(
                "classpath:com/mbpfefferle/bugj/web");
        runtimeOptions.glue.add("classpath:com/mbpfefferle/bugj/web");
    }
}

