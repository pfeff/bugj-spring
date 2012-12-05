package com.mbpfefferle.bugj.web.bugs;

import static com.mbpfefferle.bugj.Deployments.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.web.BugsResource;
import com.mbpfefferle.bugj.web.ComponentScan;
import com.mbpfefferle.bugj.web.Initializer;
import com.mbpfefferle.bugj.web.MvcConfig;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NewBugIT {

    @Deployment
    public static WebArchive createDeployment() {
        return bugWar();
    }

    @Test
    public void testSanity() {
        assertThat(true, is(true));
    }
}

