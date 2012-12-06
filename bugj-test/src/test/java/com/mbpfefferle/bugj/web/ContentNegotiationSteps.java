package com.mbpfefferle.bugj.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.*;

import com.mbpfefferle.bugj.web.pages.bug.NewBugPage;

import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;

import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.jboss.arquillian.drone.api.annotation.Drone; 
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.openqa.selenium.WebDriver;

public class ContentNegotiationSteps {

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL deploymentUrl;

    @Page
    NewBugPage form;

    HttpResponse response;

    @Given("^A bug has been entered$")
    public void A_bug_has_been_entered() throws Throwable {
            // Express the Regexp above with the code you wish you had
        browser.navigate().to(newBugUrl());
        form.setSynopsis("Something broke");
        form.submit();
    }

    @When("^I GET the URL with a JSON media type$")
    public void I_GET_the_URL_with_a_JSON_media_type() throws Throwable {
        // Express the Regexp above with the code you wish you had
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(bugUrl(1));
        getRequest.addHeader("accept", "application/json");

        response = httpClient.execute(getRequest);
        assertThat(response.getStatusLine().getStatusCode(), is(200));
    }

    @Then("^The response media type should be application/json$")
    public void The_response_media_type_should_be_application_json() throws Throwable {
        // Express the Regexp above with the code you wish you had
        Header[] hs = response.getHeaders("Content-Type");
        assertThat(response.getHeaders("Content-Type")[0].getValue(),
                startsWith("application/json"));
    }

    private String newBugUrl() {
        return deploymentUrl + "app/bugs/new";
    }

    private String bugUrl(int id) {
        return deploymentUrl + "app/bugs/" + id + ".json";
    }

}

