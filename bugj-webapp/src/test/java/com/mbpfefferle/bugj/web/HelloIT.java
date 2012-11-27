package com.mbpfefferle.bugj.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URL;

import com.thoughtworks.selenium.DefaultSelenium;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloIT {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Drone
    DefaultSelenium browser;

    @ArquillianResource
    URL deploymentUrl;

    @Deployment(testable=false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(HelloResource.class)
            .addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
            //.setWebXML("WEB-INF/web.xml");
            ;
    }

    @Test
    public void testSanity() {
        assertThat(true, is(true));
    }

    @Test
    public void shouldIncludeIndexJsp() {
        browser.open(deploymentUrl + "index.jsp");
        assertThat("Should say hello world",
                browser.isElementPresent("xpath=//h1[contains(text(), 'Hello World!')]"),
                is(true));
    }
}

