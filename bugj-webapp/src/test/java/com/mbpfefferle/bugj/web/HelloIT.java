package com.mbpfefferle.bugj.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

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
    WebDriver browser;

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
        browser.navigate().to(deploymentUrl + "index.jsp");
        assertThat(
            browser.findElement(
                    By.xpath("//h1[contains(text(), 'Hello World!')]")),
            not(nullValue()));
    }
}

