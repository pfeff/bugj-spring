package com.mbpfefferle.bugj.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.*;

import com.mbpfefferle.bugj.web.pages.bug.NewBugPage;

import java.net.URL;

import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;

import org.jboss.arquillian.drone.api.annotation.Drone; 
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.openqa.selenium.WebDriver;

public class StepDefinitions {

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL deploymentUrl;

    @Page
    NewBugPage form;

    private String newBugUrl() {
        return deploymentUrl + "app/bugs/new";
    }

    @Given("^I'm at the new bug form$")
    public void I_m_at_the_new_bug_form() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //throw new PendingException();
        browser.navigate().to(newBugUrl());
    }

    @When("^I enter text into the fields$")
    public void I_enter_text_into_the_fields() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //throw new PendingException();
        form.setSynopsis("Something broke");
    }

    @When("^I click submit$")
    public void I_click_submit() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //throw new PendingException();
        form.submit();
    }

    @Then("^I should see a confirmation message$")
    public void I_should_see_a_confirmation_message() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //throw new PendingException();
        browser.findElement(
                xpath("//h2[contains(text(), 'Bug')]"));
    }

    @When("^I visit 'hello.jsp'$")
    public void I_visit_hello_jsp() throws Throwable {
        browser.navigate().to(deploymentUrl + "index.jsp");
    }

    @Then("^I should be greeted$")
    public void I_should_be_greeted() throws Throwable {
        assertThat(
            browser.findElement(
                    xpath("//h1[contains(text(), 'Hello World!')]")),
            not(nullValue()));
    }

}

